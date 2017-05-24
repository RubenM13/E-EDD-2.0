package es.ucm.fdi.edd.graphiti.views;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.graphiti.ui.editor.DiagramComposite;
import org.eclipse.graphiti.ui.editor.DiagramEditorInput;
import org.eclipse.graphiti.ui.editor.IDiagramEditorInput;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.part.ViewPart;

public class EDDView extends ViewPart implements IResourceChangeListener, IPartListener2, ISelectionListener {

	/** The ID of the view as specified by the extension. */
	public static final String ID = "es.ucm.fdi.edd.graphiti.views.EDDView";
	private static final String SAMPLE_URI = "platform:/plugin/es.ucm.fdi.edd.graphiti/diagram/SampleEdd.diagram#/0";
	private static final String PROVIDER_ID = "es.ucm.fdi.edd.graphiti.diagram.eddDiagramTypeProvider";
	
	private DiagramComposite diagramComposite;
	private IFile selectedFile;
	
	@Override
	public void createPartControl(Composite parent) {
		diagramComposite = new DiagramComposite(this, parent, SWT.NONE);
		diagramComposite.setLayout(new FillLayout());
		diagramComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		URI uri = URI.createURI(SAMPLE_URI);
		diagramComposite.setInput(new DiagramEditorInput(uri, PROVIDER_ID));
		
		installResourceListener();
		installSelectionListener();
		installPartListener();
	}
	
	private void installResourceListener() {
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this);
	}

	private void installSelectionListener() {
		getSite().getWorkbenchWindow().getSelectionService().addSelectionListener(this);
	}

	private void installPartListener() {
		getSite().getPage().addPartListener(this);
		// tries to load an image for the current active part, if any
		final IWorkbenchPartReference activePartReference = getSite().getPage().getActivePartReference();
		if (activePartReference != null)
			reactToPartChange(activePartReference);
	}

	@Override
	public void setFocus() {
		diagramComposite.setFocus();
	}

	public Object getAdapter(@SuppressWarnings("rawtypes") Class type) {
		Object returnObj = null;

		if (diagramComposite != null && !diagramComposite.isDisposed()) {
			returnObj = diagramComposite.getAdapter(type);
		}

		if (returnObj != null) {
			return returnObj;
		}

		return super.getAdapter(type);
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (!(selection instanceof IStructuredSelection))
			return;
		IStructuredSelection structured = (IStructuredSelection) selection;
		if (structured.size() != 1)
			return;
		Object selected = structured.getFirstElement();
		IFile file = (IFile) Platform.getAdapterManager().getAdapter(selected, IFile.class);
		reload(file);
	}

	@Override
	public void partActivated(IWorkbenchPartReference partRef) {
		reactToPartChange(partRef);
	}

	@Override
	public void partBroughtToTop(IWorkbenchPartReference partRef) {
		reactToPartChange(partRef);
	}

	@Override
	public void partClosed(IWorkbenchPartReference partRef) {
		//
	}

	@Override
	public void partDeactivated(IWorkbenchPartReference partRef) {
		//
	}

	@Override
	public void partOpened(IWorkbenchPartReference partRef) {
		reactToPartChange(partRef);
	}

	@Override
	public void partHidden(IWorkbenchPartReference partRef) {
		//
	}

	@Override
	public void partVisible(IWorkbenchPartReference partRef) {
		reactToPartChange(partRef);
	}

	@Override
	public void partInputChanged(IWorkbenchPartReference partRef) {
		reactToPartChange(partRef);
	}

	@Override
	public void resourceChanged(IResourceChangeEvent event) {
		if (selectedFile == null || !selectedFile.exists())
			return;
		if (event.getDelta() == null)
			return;
		IResourceDelta interestingChange = event.getDelta().findMember(selectedFile.getFullPath());
		if (interestingChange != null)
			requestUpdate();
	}
	
	private void requestUpdate() {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				if (getSite() == null || !EDDView.this.getSite().getPage().isPartVisible(EDDView.this))
					// don't do anything if we are not showing
					return;
				reload(selectedFile);
			}
		});
	}
	
	protected void reload(IFile file) {
		if (file == null || !file.exists())
			return;
				
		URI diagramURI = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
		IDiagramEditorInput input = new DiagramEditorInput(diagramURI, PROVIDER_ID);
		//FIXME diagramComposite.setInput(input);
	}

	private void reactToPartChange(IWorkbenchPartReference part) {
		if (!(part.getPart(false) instanceof IEditorPart))
			return;
		IEditorPart editorPart = (IEditorPart) part.getPart(false);
		if (!getViewSite().getPage().isPartVisible(editorPart))
			return;
//		IGraphicalFileProvider graphicalSource = (IGraphicalFileProvider) editorPart.getAdapter(IGraphicalFileProvider.class);
//		if (graphicalSource != null)
//			selectedFile = graphicalSource.getGraphicalFile();
//		else 
//			selectedFile = null;		
		if (selectedFile == null) {
			IFile asFile = (IFile) editorPart.getAdapter(IFile.class);
			if (asFile == null)
				asFile = (IFile) editorPart.getEditorInput().getAdapter(IFile.class);
			if (asFile == null)
				return;
			selectedFile = asFile;
		}
		requestUpdate();
	}
	
	@Override
	public void dispose() {
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
		getSite().getPage().removePartListener(this);
		getSite().getWorkbenchWindow().getSelectionService().removeSelectionListener(this);
		super.dispose();
	}
}