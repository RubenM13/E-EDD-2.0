package es.ucm.fdi.edd.ui.views.listeners;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IMarkSelection;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import es.ucm.fdi.edd.ui.views.EDDTreeView;
import es.ucm.fdi.edd.ui.views.EDDebugView;
import es.ucm.fdi.emf.model.ed2.Node;

/**
 * Listener that updates the {@link ViewPart} with the currently selected element of the workbench.
 */
public class EDDViewSelectionListener implements ISelectionListener {
	
	/** The view part to update. */
	private ViewPart view;
	
	/**
	 * Creates a new instance of {@link EDDViewSelectionListener}.
	 *
	 * @param view
	 *            the {@link ViewPart}.
	 */
	public EDDViewSelectionListener(ViewPart view) {
		this.view = view;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.ISelectionListener#selectionChanged(org.eclipse.ui.IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void selectionChanged(IWorkbenchPart sourcepart, ISelection selection) {
		// we ignore our own selections
		if (sourcepart != view) {
			showSelection(sourcepart, selection);
		}
	}
	
	/**
	 * Shows the given selection in this view.
	 */
	public void showSelection(IWorkbenchPart sourcepart, ISelection selection) {
		if (view instanceof EDDebugView) {
			EDDebugView edDebugView = (EDDebugView) view;
//			edDebugView.setContentDescription(sourcepart.getClass().getSimpleName() + " (" + selection.getClass().getSimpleName() + " - " + selection +")");
		}
		if (view instanceof EDDTreeView) {
			EDDTreeView eddTreeView = (EDDTreeView) view;
			view.getContentDescription();
//			eddTreeView.setContentDescription(sourcepart.getClass().getSimpleName() + " (" + selection.getClass().getSimpleName() + " - " + selection +")");
		}
//		view.setContentDescription(sourcepart.getTitle() + " (" + selection.getClass().getName() + ")");
		
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection ss = (IStructuredSelection) selection;
			showItems(ss.toArray());
		}
		if (selection instanceof ITextSelection) {
			ITextSelection ts  = (ITextSelection) selection;
			showText(ts.getText());
		}
		if (selection instanceof IMarkSelection) {
			IMarkSelection ms = (IMarkSelection) selection;
			try {
			    showText(ms.getDocument().get(ms.getOffset(), ms.getLength()));
			} catch (BadLocationException e) { 
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @param items
	 */
	private void showItems(Object[] items) {
		for (Object object : items) {
			if (object instanceof Node) {
				Node node = (Node) object;
				Integer index = node.getIndex();
//				updateEDDebugView(index);
			}
			else if (object instanceof Integer) {
				Integer index = (Integer) object;
				updateEDDTreeView(index);
			}
		}
		
//		System.out.println(Arrays.asList(items));
	}
	
	/**
	 * @param text
	 */
	private void showText(String text) {
//		System.out.println(text);
	}
	
	/**
	 * @param index
	 */
	private void updateEDDebugView(Integer index) {
		IViewPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(EDDebugView.VIEW_ID);
		if (part instanceof EDDebugView) {
			EDDebugView view = (EDDebugView) part;
			if (view.isQuestionPanelVisible()) {
				if (index != null) {
					view.updateSelection(index);
				}
			}
		}
	}
	
	/**
	 * @param index
	 */
	private void updateEDDTreeView(Integer index) {
		IViewPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(EDDTreeView.ID);
		if (part instanceof EDDTreeView) {
			EDDTreeView view = (EDDTreeView) part;
			if (index != null) {
				view.updateSelectionByIndex(index);
			}
		}
	}
}