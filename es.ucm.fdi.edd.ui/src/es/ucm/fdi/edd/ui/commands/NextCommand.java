package es.ucm.fdi.edd.ui.commands;

import java.util.Observable;
import java.util.Observer;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.handlers.HandlerUtil;

import es.ucm.fdi.edd.ui.connection.ConnectionManager;
import es.ucm.fdi.edd.ui.views.EDDebugView;

public class NextCommand extends AbstractHandler implements Observer {
	
	public NextCommand() {
		super();
		ConnectionManager.getInstance().addObserver(this);
		setBaseEnabled(false); // by default
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
//		MessageDialog.openInformation(HandlerUtil.getActiveWorkbenchWindow(event).getShell(), "Next", "Pressed next button");
		IViewPart part = HandlerUtil.getActiveWorkbenchWindow(event).getActivePage().findView(EDDebugView.VIEW_ID);
		if (part instanceof EDDebugView) {
			EDDebugView view = (EDDebugView)part;
			if (view.isQuestionPanelVisible()) {
				Integer index = view.getIndex();
				Integer total = view.getTotal();
				if (index >= 0 && index < total ) {
					view.updateSelection(index + 1);
				} else {
					view.updateSelection(total);
				}
			}
		}
		
		return null;
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