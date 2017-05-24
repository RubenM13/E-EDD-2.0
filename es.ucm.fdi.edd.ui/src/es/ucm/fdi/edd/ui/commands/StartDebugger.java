package es.ucm.fdi.edd.ui.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.handlers.HandlerUtil;

import es.ucm.fdi.edd.ui.views.EDDebugView;

public class StartDebugger extends AbstractHandler {
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Shell shell = HandlerUtil.getActiveWorkbenchWindow(event).getShell();
		MessageDialog.openInformation(shell, "Restart server", "The server will be restarted...");
		
		IViewPart part = HandlerUtil.getActiveWorkbenchWindow(event).getActivePage().findView(EDDebugView.VIEW_ID);
		if (part instanceof EDDebugView) {
			EDDebugView view = (EDDebugView)part;
			String buggyCall = view.getBuggyCall();
			IFile debugFile = view.getDebugFile();
			if (debugFile != null && debugFile.isAccessible() && buggyCall != null && !buggyCall.isEmpty()) {
				view.startServer();
			}
			else {
				MessageDialog.openError(shell, "EDD - Error", "You must provide the buggy call and the location of the erlang source file before to start debugging.");
			}
		}
		
		return null;
	}
}