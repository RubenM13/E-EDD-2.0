package es.ucm.fdi.edd.ui.views;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IPath;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.ui.part.ViewPart;

import es.ucm.fdi.edd.core.erlang.concurrent.model.ErlangInfo;
import es.ucm.fdi.edd.core.erlang.concurrent.model.ErlangMessage;
import es.ucm.fdi.edd.core.erlang.concurrent.model.ErlangThread;
import es.ucm.fdi.edd.ui.views.listeners.EddSequenceDiagramListener;
import es.ucm.fdi.edd.ui.views.model.SeqDiagramElementInfo;

public class EddSequenceView extends ViewPart implements IToolBarAction {

	public final static String VIEW_ID = "es.ucm.fdi.edd.ui.views.EddSequenceView";

	private Canvas canvas;
	private EddSequenceDiagramListener mouseListener;
	private Map<String, Integer> processLineLoc;

	// Altura que ocupan los caracteres
	private int charHeight;

	// Fuente utilizada
	private Font font;
	private boolean sizeChange = true;

	// Información a mostrar en el diagrama de sequencia
	private List<ErlangThread> threads;
	private List<ErlangMessage> messages;

	private int despX = 0;
	private int despY = 0;

	public EddSequenceView() {
		super();
		this.processLineLoc = new HashMap<String, Integer>();
		this.threads = ErlangInfo.getEntities();
		this.messages = ErlangInfo.getErlangMessages();
	}

	/**
	 * Se encarga de inicializzar la visualización de la vista
	 */
	@Override
	public void createPartControl(Composite parent) {
		canvas = new Canvas(parent, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		canvas.addControlListener(new ControlAdapter() { /* resize listener. */
			public void controlResized(ControlEvent event) {
				canvas.setBounds(parent.getClientArea());
				sizeChange = true;
			}
		});
		canvas.addPaintListener(new PaintListener() { /* paint listener. */
			public void paintControl(final PaintEvent event) {
				if (threads != null && messages != null) {
					if (sizeChange) {
						loadHeight(event.gc);
						checkScrolls(event.gc);
					}
					mouseListener.reset();
					drawnThreads(event.gc, despX, despY);
					drawMessages(event.gc, despY);
				}
			}
		});

		this.canvas.getHorizontalBar().addSelectionListener(
				new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						sizeChange = true;
						canvas.redraw();
					}
				});
		this.canvas.getVerticalBar().addSelectionListener(
				new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						sizeChange = true;
						canvas.redraw();
					}
				});

		this.mouseListener = new EddSequenceDiagramListener(this.canvas);
		this.canvas.addMouseListener(this.mouseListener);
		this.canvas.addMouseMoveListener(this.mouseListener);
	}

	private void checkScrolls(GC gc) {
		int seqHeight = Math.round(2 * this.charHeight + 10
				+ (messages.size() + 4) * this.charHeight);
		int totalWidth = 5 + 5 * gc.getFontMetrics().getAverageCharWidth()
				* (this.threads.size() - 1);
		for (ErlangThread thread : this.threads) {
			totalWidth += 4 + this.getStringPixelLength(gc,
					thread.getFullName());
		}
		this.despX = this.checkScroll(totalWidth,
				this.canvas.getBounds().width - 30,
				this.canvas.getHorizontalBar());
		this.despY = this.checkScroll(seqHeight,
				this.canvas.getBounds().height, this.canvas.getVerticalBar());
	}

	private int checkScroll(int real, int max, ScrollBar scroll) {
		if (real > max) {
			scroll.setEnabled(true);
			scroll.setMaximum(real);
			scroll.setMinimum(max);
			return max - scroll.getSelection();
		} else {
			scroll.setEnabled(false);
			return 0;
		}
	}

	private void drawnThreads(GC gc, int despX, int despY) {
		int start = 5;
		int gap = gc.getFontMetrics().getAverageCharWidth();
		int seqHeight = Math.round(2 * this.charHeight + 10
				+ (messages.size() + 2) * this.charHeight);
		for (ErlangThread thread : this.threads) {
			// Se dibuja el cuadro indicador del thread
			gc.setLineStyle(SWT.LINE_SOLID);
			int width = 4 + this.getStringPixelLength(gc, thread.getFullName());
			Rectangle rectangle = new Rectangle(start + despX, 5 + despY,
					width, 2 * this.charHeight + 4);
			gc.drawRectangle(rectangle);
			gc.drawText(
					thread.getId(),
					start
							+ (width - this.getStringPixelLength(gc,
									thread.getId())) / 2 + despX, 6 + despY);
			gc.drawText(thread.getFullName(), start + 2 + despX, 6
					+ this.charHeight + despY);
			this.mouseListener
					.addThread(new SeqDiagramElementInfo<ErlangThread>(
							rectangle, thread));

			// Se dibuja la linea de operativa del Thread
			gc.setLineStyle(SWT.LINE_DASH);
			int x = (width / 2) + start;
			this.processLineLoc.put(thread.getId(), x + despX);
			gc.drawLine(x + despX, 2 * this.charHeight + 10 + despY, x + despX,
					seqHeight);
			start += width + 5 * gap;
		}
	}

	private void drawMessages(GC gc, int despY) {
		int i = 1;
		for (ErlangMessage message : this.messages) {
			this.drawMessage(gc, message, despY, i);
			i++;
		}
	}

	/**
	 * coloca el mensaje en el diagrama de secuencia a mostrar.
	 * 
	 * @param gc
	 * @param message
	 */
	private void drawMessage(GC gc, ErlangMessage message, int despY, int i) {
		String text = this.getTextMessage(gc, message);
		int start = this.processLineLoc.get(message.getSender());
		int end = this.processLineLoc.get(message.getReciver());
		int y = Math.round(2 * this.charHeight + 10 + this.charHeight * i)
				+ despY;

		int messageStart = start + 2;
		if (start == end) {
			gc.setLineStyle(SWT.LINE_SOLID);
			int[] aux = { end, y + 3, end + 3, y, end, y - 3, end - 3, y, end,
					y + 3 };
			gc.drawPolygon(aux);
		} else {
			gc.drawLine(start, y, end, y);
			gc.setLineStyle(SWT.LINE_SOLID);
			int dir = -1;
			if (start > end) {
				dir = 1;
				messageStart = start
						- (2 + this.getStringPixelLength(gc, text));
			}
			int[] aux = { end, y, end + 3 * dir, y - 3, end + 3 * dir, y + 3 };
			gc.drawPolygon(aux);
		}

		gc.setLineWidth(1);
		gc.setForeground(new Color(Display.getCurrent(), 0, 0, 0));
		Rectangle rectangle = new Rectangle(messageStart, y - this.charHeight,
				this.getStringPixelLength(gc, text), this.charHeight);
		gc.drawText(text, messageStart, y - this.charHeight, true);
		this.mouseListener.addMessage(new SeqDiagramElementInfo<ErlangMessage>(
				rectangle, message));
	}

	/**
	 * Obtiene el texto del mensaje a mostrar según el tipo de mensaje que sea.
	 * Tambien pone el color usado según el tipo.
	 * 
	 * @param gc
	 * @param message
	 * @return
	 */
	private String getTextMessage(GC gc, ErlangMessage message) {
		String text = "";
		gc.setLineWidth(2);
		switch (message.getType()) {
		case NEW:
			gc.setForeground(new Color(Display.getCurrent(), 125, 0, 0));
			text = "New " + message.getReciver();
			gc.setLineStyle(SWT.LINE_SOLID);
			break;
		case GET:
			gc.setForeground(new Color(Display.getCurrent(), 0, 125, 0));
			text = "Get " + message.getInfo();
			gc.setLineStyle(SWT.LINE_DOT);
			break;
		case SEND:
			gc.setForeground(new Color(Display.getCurrent(), 0, 0, 125));
			text = "Send " + message.getInfo();
			gc.setLineStyle(SWT.LINE_DOT);
			break;
		}
		return text;
	}

	/**
	 * Obtiene los pixeles que abarca el texto pasado
	 * 
	 * @param gc
	 * @param message
	 * @return
	 */
	private int getStringPixelLength(GC gc, String message) {
		int result = 0;
		int length = message.length();
		for (int i = 0; i < length; i++) {
			result += gc.getAdvanceWidth(message.charAt(i));
		}
		return result;
	}

	/**
	 * Obtiene la altura de la fuente actual para calcular las areas de
	 * selección y la proporción altura tiempo.
	 * 
	 * @param gc
	 */
	private void loadHeight(GC gc) {
		if (this.font == null) {
			this.font = gc.getFont();
		}
		gc.setFont(this.font);
		this.charHeight = gc.getFontMetrics().getHeight();
		this.mouseListener.setHeight(this.charHeight);
		this.mouseListener.setThreadsHeight(2 * this.charHeight + 9
				+ this.despY);
		this.sizeChange = false;
	}

	@Override
	public void setFocus() {
		canvas.setFocus();
	}

	/**
	 * Muestra el diagrama de sequencia definido por los elementos que se pasan
	 * por parametro
	 * 
	 * @param threads
	 * @param messages
	 */
	public void initDiagram(List<ErlangThread> threads, List<ErlangMessage> messages) {
		this.threads = threads;
		this.messages = messages;
		this.canvas.redraw();
	}

	public void saveImage(IPath path) {
		int height, width;
		ScrollBar scroll = this.canvas.getHorizontalBar();
		if (scroll.getEnabled()) {
			width = scroll.getMaximum();
		} else {
			width = this.canvas.getSize().x;
		}
		scroll = this.canvas.getVerticalBar();
		if (scroll.getEnabled()) {
			height = scroll.getMaximum();
		} else {
			height = this.canvas.getSize().y;
		}
		Rectangle rectangle = new Rectangle(0, 0, width, height);
		Image drawable = new Image(this.canvas.getDisplay(), rectangle);
		GC gc = new GC(drawable);
		gc.setFont(this.font);

		this.drawnThreads(gc, 0, 0);
		this.drawMessages(gc, 0);

		ImageLoader loader = new ImageLoader();
		loader.data = new ImageData[] { drawable.getImageData() };
		loader.save(path.toString(), SWT.IMAGE_PNG);
		drawable.dispose();
		gc.dispose();
	}

	// ToolBar Actions //

	public void zoomIn() {
		this.zoom(1);
	}

	public void zoomOut() {
		this.zoom(-1);
	}

	public void showOriginal() {
		FontData data = this.font.getFontData()[0];
		this.font = new Font(this.font.getDevice(), data.getName(), 9,
				data.getStyle());
		this.sizeChange = true;
		this.canvas.redraw();
	}

	/**
	 * Operativa de cambio del tamaño de la letra del diagrama
	 * 
	 * @param intOut
	 *            Indicador del tipo de cambio del tamaño
	 */
	private void zoom(int intOut) {
		FontData data = this.font.getFontData()[0];
		if (data.getHeight() + intOut != 0) {
			this.font = new Font(this.font.getDevice(), data.getName(),
					data.getHeight() + intOut, data.getStyle());
			this.sizeChange = true;
			this.canvas.redraw();
		}
	}

	@Override
	public void fitCanvas() {
	}

	@Override
	public void loadImage(String filename) {
	}

	@Override
	public ImageData getImageData() {
		return null;
	}

	@Override
	public void setImageData(ImageData dest) {
	}
}
