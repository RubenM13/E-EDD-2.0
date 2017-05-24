package es.ucm.fdi.edd.ui.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.handlers.HandlerUtil;

import es.ucm.fdi.edd.ui.views.GraphvizView;

public class LinkSelectionHandler extends AbstractHandler {

	public LinkSelectionHandler() {
		super();
	}
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {  
	    IViewPart part = HandlerUtil.getActiveWorkbenchWindow(event).getActivePage().findView(GraphvizView.VIEW_ID);
		if (part instanceof GraphvizView) {
			Command command = event.getCommand(); 
		    boolean value = HandlerUtil.toggleCommandState(command);
		    
			GraphvizView view = (GraphvizView)part;
			view.setLinkedActive(!value);
		}

		return null;
	}

}
