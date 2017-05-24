package es.ucm.fdi.edd.ui.commands;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

import es.ucm.fdi.edd.core.erlang.concurrent.model.ErlangMessage;
import es.ucm.fdi.edd.core.erlang.concurrent.model.ErlangThread;
import es.ucm.fdi.edd.ui.connection.ConnectionManager;
import es.ucm.fdi.edd.ui.views.EDDebugView;
import es.ucm.fdi.edd.ui.views.EddSequenceView;

public class StartConcurrentDebugServer extends AbstractHandler implements Observer {

	private static final String WS_EDD = "edd";
	
	/**
	 * 
	 */
	public StartConcurrentDebugServer() {
		super();
		ConnectionManager.getInstance().addObserver(this);
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		try {
			this.setBaseEnabled(false);
			IWorkbenchWindow activeWorkbenchWindow = HandlerUtil.getActiveWorkbenchWindow(event);
			Shell shell = activeWorkbenchWindow.getShell();
			IViewPart part = activeWorkbenchWindow.getActivePage().findView(EDDebugView.VIEW_ID);
			if (part instanceof EDDebugView) {
				EDDebugView view = (EDDebugView)part;
				EddSequenceView seqView = this.getSecuenceView(activeWorkbenchWindow);
				if (seqView != null) {
					String buggyCall = view.getBuggyCall();
					IFile debugFile = view.getDebugFile();
					if (debugFile != null && debugFile.isAccessible() && buggyCall != null && !buggyCall.isEmpty()) {
						createEDDFolder(debugFile);
						try {
							List<ErlangThread> threads = new LinkedList<ErlangThread>();
							List<ErlangMessage> messages = new LinkedList<ErlangMessage>();
							view.startConcurrent(threads, messages);
							seqView.initDiagram(threads, messages);
							ConnectionManager.getInstance().connect();
						} catch (Exception e) {
							MessageDialog.openError(shell, "EDD - Error", "Error in the process of obtain the diagram info");
							e.printStackTrace();
						}
					} else {
						MessageDialog.openError(shell, "EDD - Error", "You must provide the buggy call and the location of the erlang source file before to start debugging.");
					}
				}
			}
		} finally {
			this.setBaseEnabled(true);
		}
		return null;
	}	

	/**
	 * Obtiene la vista del diagrama de sequencia, activandolo en caso de que no este
	 * @param activeWorkbenchWindow
	 * @return
	 */
	private EddSequenceView getSecuenceView(IWorkbenchWindow activeWorkbenchWindow) {
		IViewPart part = activeWorkbenchWindow.getActivePage().findView(EddSequenceView.VIEW_ID);
		if (part == null) {
			try {
				activeWorkbenchWindow.getActivePage().showView(EddSequenceView.VIEW_ID);
				part = activeWorkbenchWindow.getActivePage().findView(EddSequenceView.VIEW_ID);
			} catch (PartInitException e) {
				return null;
			}
		}
		if (part instanceof EddSequenceView) {
			return (EddSequenceView) part;
		} else {
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
    public void update(Observable o, Object arg) {
		ConnectionManager connectionManager = (ConnectionManager) o;
        setBaseEnabled(!connectionManager.isConnected());
	}
	
	/**
	 * Create the working directory.
	 */
	private void createEDDFolder(IFile iFile) {
		if (iFile == null || !iFile.exists())
			return;
		
		try {
			IProject project = iFile.getProject();
			IFolder folder = project.getFolder(WS_EDD);
			if (!folder.exists()) {
				folder.create(IResource.NONE, true, new NullProgressMonitor());
			}
			else {
				folder.refreshLocal(IResource.DEPTH_INFINITE, null);
			}
		}
		catch (CoreException e) {
			e.printStackTrace();
		}
	}
}
