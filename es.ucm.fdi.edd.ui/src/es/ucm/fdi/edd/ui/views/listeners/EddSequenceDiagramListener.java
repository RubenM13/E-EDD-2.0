package es.ucm.fdi.edd.ui.views.listeners;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import es.ucm.fdi.edd.core.erlang.concurrent.model.ErlangInfo;
import es.ucm.fdi.edd.core.erlang.concurrent.model.ErlangMessage;
import es.ucm.fdi.edd.core.erlang.concurrent.model.ErlangThread;
import es.ucm.fdi.edd.ui.dialogs.QuestionDialog;
import es.ucm.fdi.edd.ui.views.model.SeqDiagramElementInfo;

public class EddSequenceDiagramListener implements MouseListener, MouseMoveListener {

	// Mapa localización - entidad
	private List<SeqDiagramElementInfo<ErlangThread>> threads;

	// Altura de la fuente usada en el esquema
	private int height = 25;

	// Altura en la que termina el apartado de los threads
	private int threadsHeight = 25;

	// Mapa de mensajes agrupados por localización
	private Map<Integer, SeqDiagramElementInfo<ErlangMessage>> messagesLocMap;

	private Shell shell;

	private boolean lock;

	private QuestionDialog window;
	
	public EddSequenceDiagramListener(Canvas canvas) {
		this.threads = new LinkedList<SeqDiagramElementInfo<ErlangThread>>();
		this.messagesLocMap = new HashMap<Integer, SeqDiagramElementInfo<ErlangMessage>>();
		this.shell = canvas.getShell();
		this.lock = false;
	}

	@Override
	public void mouseUp(MouseEvent e) {
	}

	@Override
	public void mouseDown(MouseEvent e) {
	}

	@Override
	public void mouseDoubleClick(MouseEvent e) {
		// Se comprueba si se ha dado click en un thread
		if (e.y > 5 && e.y < this.threadsHeight) {
			ErlangThread info = this.checkThreads(e.x);
			if (info != null) {
				showThread(info);
//				String message = "Creation thread: " + info.getCreator();
//				MessageDialog.openInformation(this.shell, "Thread " + info.getId(), message);
			}
		}
		// Se comprueba si se ha dado click en un mensaje
		if (e.y > this.threadsHeight) {
			ErlangMessage info = this.checkMessages(e.x, e.y);
			if (info != null) {
				this.showMessage(info);
			}
		}
	}

	@Override
	public void mouseMove(MouseEvent e) {
		if (e.getSource() instanceof Canvas) {
			ErlangInfo info = null;
			if (e.y > 5 && e.y < this.threadsHeight) {
				info = this.checkThreads(e.x);			
			}
			if (e.y > this.threadsHeight) {
				info = this.checkMessages(e.x, e.y);
			}

			if (info != null) {
				((Canvas) e.getSource()).setCursor(new Cursor(e.display, SWT.CURSOR_HAND));
			} else {
				((Canvas) e.getSource()).setCursor(new Cursor(e.display, SWT.CURSOR_ARROW));
			}
		}
	}

	/** 
	 * Comprueba si se ha hecho click sobre un cuadro
	 * @param x
	 */
	private ErlangThread checkThreads(int x) {
		ErlangThread info = null;
		Boolean found = false;
		Iterator<SeqDiagramElementInfo<ErlangThread>> iterator = threads.iterator();
		while (iterator.hasNext() && !found) {
			SeqDiagramElementInfo<ErlangThread> thread = iterator.next();
			if (thread.contains(x, this.height - 1)) {
				found = true;
				info = thread.getErlangInfo();
			}
		}
		return info;
	}

	/**
	 * Comprueba si se ha hecho click sobre un mensaje
	 * @param x
	 * @param y
	 */
	private ErlangMessage checkMessages(int x, int y) {
		ErlangMessage info = null;
		Boolean found = false;
		Iterator<SeqDiagramElementInfo<ErlangMessage>> iterator = this.getMessages(y).iterator();
		while (iterator.hasNext() && !found) {
			SeqDiagramElementInfo<ErlangMessage> thread = iterator.next();
			if (thread.contains(x, y)) {
				found = true;
				info = thread.getErlangInfo();
			}
		}
		return info;
	}

	/**
	 * Obtiene todos los mensajes que interseccionan con la coordenada y pasada
	 * @param y
	 * @return
	 */
	private List<SeqDiagramElementInfo<ErlangMessage>> getMessages(int y) {
		List<SeqDiagramElementInfo<ErlangMessage>> messages = new LinkedList<SeqDiagramElementInfo<ErlangMessage>>();
		for (int i = 0; i < this.height; i ++) {
			SeqDiagramElementInfo<ErlangMessage> aux = this.messagesLocMap.get(y - i);
			if (aux != null) {
				messages.add(aux);
			}
		}
		return messages;
	}

	/**
	 * 
	 * @param thread
	 */
	public void addThread(SeqDiagramElementInfo<ErlangThread> thread) {
		this.threads.add(thread);
	}

	/**
	 * 
	 * @param message
	 */
	public void addMessage(SeqDiagramElementInfo<ErlangMessage> message) {
		this.messagesLocMap.put(message.getY(), message);
	}

	private void showMessage(ErlangMessage info) {
		if (this.lock()) {
			window = new QuestionDialog(null, info, this.getTextMessage(info));
		    window.setBlockOnOpen(true);
		    int i = window.open();
		    window = null;
		    this.lock = false;
		    System.out.println(i);
		}
	}

	private void showThread(ErlangThread thread) {
		String text = "This Process was constructed by the ";
		if (thread.getCreatorMessage() != null) {
			text+= "Process " + thread.getCreatorMessage().getSender() + " \n";
		} else {
			text+= "User\n";
		}
		
//		text+= "\t" + this.getTextMessage(thread.getCreatorMessage()) + "\n";

		text+= "Sent Messages:\n";
		for(ErlangMessage message : thread.getSentMessages()) {
			text += "\t" + this.getTextMessage(message) + "\n";
		}

		text+= "Get Messages:\n";
		for(ErlangMessage message : thread.getGetMessages()) {
			text += "\t" + this.getTextMessage(message) + "\n";
		}

		MessageDialog.openInformation(this.shell, "Process " + thread.getId(), text);
	}

	private String getTextMessage(ErlangMessage message) {
		String text = "";
		switch(message.getType()) {
			case NEW:
				text = "New Process " + message.getReciver();
				break;
			case GET:
				text = "Get " + message.getInfo();
				break;
			case SEND:
				text = "Send " + message.getInfo();
				break;
		}
		return text;
	}

	private synchronized boolean lock() {
		if (this.lock) {
			return false;
		} else {
			this.lock =true;
			return true;
		}
	}

	/**
	 * Indica la altura de la fuente utilizada en el esquema
	 * @param height
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Indica la altura que diferencia la zona de mensajes de la zona de los cuadros de threads
	 * @param height
	 */
	public void setThreadsHeight(int height) {
		this.threadsHeight = height;
	}

	public void reset() {
		if (window != null) {
			this.window.close();
		}
		this.threads.clear();
		this.messagesLocMap.clear();
	}
}
