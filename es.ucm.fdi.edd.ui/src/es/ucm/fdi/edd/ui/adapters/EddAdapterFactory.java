package es.ucm.fdi.edd.ui.adapters;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.gmf.runtime.diagram.ui.internal.editparts.DiagramRootTreeEditPart;
import org.eclipse.ui.IActionFilter;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.erlide.ui.editors.erl.ErlangEditor;

import es.ucm.fdi.edd.ui.views.EddDiagramOutlinePage;

/**
 * Adapter factory to support basic UI operations for <code>IActionFilter</code> 
 * and <code>IContentOutlinePage</code>.
 */
@SuppressWarnings("restriction")
public class EddAdapterFactory implements IAdapterFactory {
	
	private static final IActionFilter actionFilter = null;

	/**
	 * @see org.eclipse.core.runtime.IAdapterFactory#getAdapter(java.lang.Object, java.lang.Class)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (adapterType == IActionFilter.class && adaptableObject instanceof Object) {
			return actionFilter;
		}	
		
	    if (adapterType == IContentOutlinePage.class && adaptableObject instanceof ErlangEditor) {
	    	ErlangEditor editor = (ErlangEditor) adaptableObject;
	    	IFile file = ((FileEditorInput)editor.getEditorInput()).getFile();
	    	
	    	TreeViewer viewer = new TreeViewer();
            viewer.setRootEditPart(new DiagramRootTreeEditPart());
            return new EddDiagramOutlinePage(viewer, file);
	    }
		
		return null;	
	}

	/**
	 * @see org.eclipse.core.runtime.IAdapterFactory#getAdapterList()
	 */
	@Override
	public Class<?>[] getAdapterList() {
		return new Class[] { IActionFilter.class, IContentOutlinePage.class };
	}
}