package es.ucm.fdi.edd.ui.commands;

import java.util.Observable;
import java.util.Observer;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import es.ucm.fdi.edd.ui.connection.ConnectionManager;
import es.ucm.fdi.edd.ui.views.EDDThreadsTreeView;
import es.ucm.fdi.edd.ui.views.EDDTreeView;
import es.ucm.fdi.edd.ui.views.EDDebugView;
import es.ucm.fdi.edd.ui.views.EddSequenceView;

/**
 * {@link DisconnectComand} which corresponds to "disconnect" action, subscribes
 * to listen {@link ConnectionManager} state changes and sets its sate by
 * calling <code>setBaseEnabled</code>. At the beginning, its sate is disabled:
 * <code>setBaseEnabled(false)</code>.
 */
public class StopDebugServer extends AbstractHandler implements Observer {
	
	/**
	 * 
	 */
	public StopDebugServer() {
		super();
		ConnectionManager.getInstance().addObserver(this);
        setBaseEnabled(false); // by default
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		try {
			IWorkbenchWindow activeWorkbenchWindow = HandlerUtil.getActiveWorkbenchWindow(event);
			Shell shell = activeWorkbenchWindow.getShell();
			IViewPart part = activeWorkbenchWindow.getActivePage().findView(EDDebugView.VIEW_ID);
			if (part instanceof EDDebugView) {
				EDDebugView view = (EDDebugView)part;
				boolean result = view.stopServer();
				if (result) {
					ConnectionManager.getInstance().disconnect();
					view.refresh();
				} else {
					MessageDialog.openError(shell, "EDD - Error", "EDD server cannot be correctly disconnected...");
				}
				cleanEDDTreeView(activeWorkbenchWindow);
				
				part = activeWorkbenchWindow.getActivePage().findView(EddSequenceView.VIEW_ID);
				if (part instanceof EddSequenceView) {
					((EddSequenceView) part).initDiagram(null, null);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	private void cleanEDDTreeView(IWorkbenchWindow activeWorkbenchWindow) {
		IViewPart part = activeWorkbenchWindow.getActivePage().findView(EDDTreeView.ID);
		if (part instanceof EDDTreeView) {
			EDDTreeView view = (EDDTreeView)part;
			view.cleanInput();
		}
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		ConnectionManager connectionManager = (ConnectionManager) o;
		setBaseEnabled(connectionManager.isConnected());
	}
}