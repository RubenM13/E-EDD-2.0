package es.ucm.fdi.edd.ui.views;

import org.eclipse.core.resources.IFile;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.abstratt.content.IContentProviderRegistry.IProviderDescription;

import es.ucm.fdi.edd.ui.views.utils.SWTImageCanvas;

public class EDDViewer extends ViewPart {
	
	public final static String VIEW_ID = "es.ucm.fdi.edd.ui.views.EDDViewer";

	public SWTImageCanvas imageCanvas;
	
	private IFile selectedFile;
	private IProviderDescription providerDefinition;

	/**
	 * The constructor.
	 */
	public EDDViewer() {
		// empty
	}

	/**
	 * Create the GUI.
	 * 
	 * @param composite
	 *            The Composite handle of parent
	 */
	public void createPartControl(Composite composite) {
		imageCanvas = new SWTImageCanvas(composite);
	}

	/**
	 * Called when we must grab focus.
	 * 
	 * @see org.eclipse.ui.part.ViewPart#setFocus
	 */
	public void setFocus() {
		imageCanvas.setFocus();
	}

	/**
	 * Called when the View is to be disposed
	 */
	public void dispose() {
		imageCanvas.dispose();
		super.dispose();
	}
	
	public IProviderDescription getContentProviderDescription() {
		return providerDefinition;
	}
	
	public IFile getSelectedFile() {
		return selectedFile;
	}
}