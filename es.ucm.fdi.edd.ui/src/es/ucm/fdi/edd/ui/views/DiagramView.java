package es.ucm.fdi.edd.ui.views;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.content.IContentDescription;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gmf.runtime.common.core.util.Log;
import org.eclipse.gmf.runtime.common.core.util.Trace;
import org.eclipse.gmf.runtime.diagram.ui.internal.DiagramUIDebugOptions;
import org.eclipse.gmf.runtime.diagram.ui.internal.DiagramUIPlugin;
import org.eclipse.gmf.runtime.diagram.ui.internal.DiagramUIStatusCodes;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.part.ViewPart;

import com.abstratt.content.ContentSupport;
import com.abstratt.content.IContentProviderRegistry.IProviderDescription;
import com.abstratt.imageviewer.IGraphicalContentProvider;

import es.ucm.fdi.edd.ui.Activator;
import es.ucm.fdi.emf.model.ed2.ED2;
import es.ucm.fdi.emf.model.ed2.Ed2Factory;
import es.ucm.fdi.emf.model.ed2.Leaf;
import es.ucm.fdi.emf.model.ed2.Model;
import es.ucm.fdi.emf.model.ed2.Node;
import es.ucm.fdi.emf.model.ed2.TreeElement;

@SuppressWarnings("restriction")
public class DiagramView extends ViewPart implements IAdaptable, IResourceChangeListener, IPartListener2, ISelectionListener {

	private GraphicalViewer viewer;
	
	public DiagramView() {
		super();
	}

	@Override
	public void createPartControl(Composite parent) {
		viewer = new GraphicalViewer(parent);
		
//		URI diagramURI = URI.createPlatformResourceURI("/E-EDD/ed2/testGMF.ed2_diagram", false);
//		URI modelURI = URI.createPlatformResourceURI("/E-EDD/ed2/testGMF.ed2", false);
//		Resource diagramResource = Ed2DiagramEditorUtil.createDiagram(diagramURI, modelURI, new NullProgressMonitor());
//		Diagram diagram = (Diagram) diagramResource.getContents().get(0);
		
		try {
			final IPath filePath = new Path("/E-EDD/ed2/testAutoGMF.ed2_diagram");
			final IFile file = Activator.getRoot().getFile(filePath);
			System.out.println(file.exists());
			IContentDescription contentDescription = file.getContentDescription();
			IProviderDescription providerDefinition =
					ContentSupport.getContentProviderRegistry().findContentProvider(
									contentDescription.getContentType(), IGraphicalContentProvider.class);
			IGraphicalContentProvider provider = (IGraphicalContentProvider) providerDefinition.getProvider();
			viewer.setContentProvider(provider);
			Object input = providerDefinition.read(file);
			viewer.setInput(input);
		} catch (CoreException e) {
			e.printStackTrace();
		}
		
//		IProviderDescription providerDefinition =
//				ContentSupport.getContentProviderRegistry().findContentProvider(
//								contentDescription.getContentType(), IGraphicalContentProvider.class);
//
//		IGraphicalContentProvider provider = (IGraphicalContentProvider) providerDefinition.getProvider();
//		viewer = new GraphicalViewer(parent);
//		viewer.setContentProvider(provider);
//		
//		URI diagramURI = URI.createPlatformResourceURI("/E-EDD/ed2/testGMF.ed2_diagram", false);
//		URI modelURI = URI.createPlatformResourceURI("/E-EDD/ed2/testGMF.ed2", false);
//		Resource diagramResource = Ed2DiagramEditorUtil.createDiagram(diagramURI, modelURI, new NullProgressMonitor());
//		Diagram diagram = (Diagram) diagramResource.getContents().get(0);
//		viewer.setInput(diagram);
		
		//---------------------------------------------------------------------------------------------------------------
		
		
//		initializeGraphicalViewer(); 
//		getGraphicalViewer().createControl(parent); 
//		getViewSite().setSelectionProvider(getGraphicalViewer()); 
//		getSite().setSelectionProvider(getGraphicalViewer());
//		
//		String basePartName = getPartName();
//		
//		// Create a dummy model
//		model = getDataModel();
// 
//		// Initialize the viewer, 'parent' is the enclosing RCP windowframe
//		viewer.createControl(parent);
//		viewer.setRootEditPart(rootEditPart);
//		viewer.setEditPartFactory(editPartFactory);
// 
//		// Inject the model into the viewer, the viewer will traverse the model automatically
//		viewer.setContents(model);
// 
//		// Set the view's background to white
//		viewer.getControl().setBackground(new Color(null, 255, 255, 255));

		
//		viewer = new GraphicalViewer(parent);
//
//		IGraphicalContentProvider provider = (IGraphicalContentProvider) providerDefinition
//				.getProvider();
//		viewer.setContentProvider(provider);
//		viewer.setInput(input);

		// DiagramGraphicalViewer viewer = new DiagramGraphicalViewer();
		// viewer.createControl(parent);
		//
		// List<EObject> list = null;
		// try {
		// URI diagramURI =
		// URI.createPlatformResourceURI("/E-EDD/ed2/testAutoGMF.ed2_diagram",
		// false);
		// list =
		// EDDHelper.loadModel("D:/workspace/runtime-tests/E-EDD/ed2/testAutoGMF.ed2");
		// } catch (IOException e1) {
		// e1.printStackTrace();
		// }
		//
		// final Model model = (Model) list.get(0);
		// Diagram diagram = ViewService.createDiagram(model,
		// ModelEditPart.MODEL_ID,
		// Ed2DiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
		//
		// RootEditPart rootEditPart =
		// EditPartService.getInstance().createRootEditPart(diagram);
		// viewer.setRootEditPart(rootEditPart);
		// viewer.setEditPartFactory(new Ed2EditPartFactory());
		// viewer.getControl().setBackground(ColorConstants.listBackground);
		// viewer.setContents(diagram);
	}

/*
	public void initializeGraphicalViewer()	{ 
		getGraphicalViewer().setRootEditPart(new ScalableFreeformRootEditPart()); 
		// set YOUR (GEF) part factory 
		getGraphicalViewer().setEditPartFactory(new Ed2EditPartFactory()); 
//		viewer.setEditDomain(new ViewPartEditDomain(this)); 
		
		
		URI diagramURI = URI.createPlatformResourceURI("/E-EDD/ed2/testGMF.ed2_diagram", false);
		URI modelURI = URI.createPlatformResourceURI("/E-EDD/ed2/testGMF.ed2", false);
		Resource diagramResource = Ed2DiagramEditorUtil.createDiagram(diagramURI, modelURI, new NullProgressMonitor());
		Diagram diagram = (Diagram) diagramResource.getContents().get(0);
		viewer.setContents(diagram);
	} 

	public ScrollingGraphicalViewer getGraphicalViewer() { 
		if (viewer == null) 
			viewer = new ScrollingGraphicalViewer(); 
		return viewer; 
	} 
*/
	
	/**
     * initialize the outline viewer
     */
    protected void initializeOutlineViewer() {
        try {
            TransactionUtil.getEditingDomain(getDiagram()).runExclusive(
                new Runnable() {
                	@Override
                    public void run() {
                        getViewer().setContents(getDiagram());
                    }
                });
        } catch (InterruptedException e) {
            Trace.catching(DiagramUIPlugin.getInstance(), DiagramUIDebugOptions.EXCEPTIONS_CATCHING, getClass(), "initializeOutlineViewer", e);
            Log.error(DiagramUIPlugin.getInstance(), DiagramUIStatusCodes.IGNORED_EXCEPTION_WARNING, "initializeOutlineViewer", e);
        }
    }	
    
    
    
	private EObject getDiagram() {
		return null;
	}
	
	private EditPartViewer getViewer() {
		return null;
	}

	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}
	
	private Model getDataModel() {
		Leaf leaf1 = Ed2Factory.eINSTANCE.createLeaf();
		leaf1.setIndex(1);
		leaf1.setName("Leaf 1");
		
		Leaf leaf2 = Ed2Factory.eINSTANCE.createLeaf();
		leaf2.setIndex(2);
		leaf2.setName("Leaf 2");
		
		Leaf leaf3 = Ed2Factory.eINSTANCE.createLeaf();
		leaf3.setIndex(3);
		leaf3.setName("Leaf 3");
		
		Node node1 = Ed2Factory.eINSTANCE.createNode();
		node1.setIndex(4);
		node1.setName("Parent 1");	
		node1.getLeaves().add(leaf1);
		node1.getLeaves().add(leaf2);
		node1.getLeaves().add(leaf3);

		Leaf leaf4 = Ed2Factory.eINSTANCE.createLeaf();
		leaf4.setIndex(1);
		leaf4.setName("Leaf 4");
		
		Leaf _leaf = Ed2Factory.eINSTANCE.createLeaf();
		_leaf.setIndex(1);
		_leaf.setName("Leaf");
		
		Node nodeZ = Ed2Factory.eINSTANCE.createNode();
		nodeZ.setIndex(4);
		nodeZ.setName("Node Z");
		nodeZ.getLeaves().add(_leaf);
		
		Node nodeY = Ed2Factory.eINSTANCE.createNode();
		nodeY.setIndex(4);
		nodeY.setName("Node Y");
		nodeY.getNodes().add(nodeZ);
		
		Node nodeX = Ed2Factory.eINSTANCE.createNode();
		nodeX.setIndex(4);
		nodeX.setName("Node X");	
		nodeX.getNodes().add(nodeY);
		
		Node node2 = Ed2Factory.eINSTANCE.createNode();
		node2.setIndex(4);
		node2.setName("Parent 2");	
		node2.getLeaves().add(leaf4);
		node2.getNodes().add(nodeX);
		
		Leaf leaf = Ed2Factory.eINSTANCE.createLeaf();
		leaf.setIndex(4);
		leaf.setName("Leaf");
		
		TreeElement te = Ed2Factory.eINSTANCE.createNode();
		te.setIndex(1);
		te.setName("TreeElement");

		ED2 ed2 = Ed2Factory.eINSTANCE.createED2();
		ed2.setName("Root");
		EList<TreeElement> elements = ed2.getTreeElements();
		elements.add(node1);
		elements.add(node2);
		elements.add(leaf);
		elements.add(te);
		
		Model model = Ed2Factory.eINSTANCE.createModel();
		model.setEd2(ed2);
		
		return model;
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		// TODO Auto-generated method stub
	}

	@Override
	public void partActivated(IWorkbenchPartReference partRef) {
		// TODO Auto-generated method stub
	}

	@Override
	public void partBroughtToTop(IWorkbenchPartReference partRef) {
		// TODO Auto-generated method stub
	}

	@Override
	public void partClosed(IWorkbenchPartReference partRef) {
		// TODO Auto-generated method stub
	}

	@Override
	public void partDeactivated(IWorkbenchPartReference partRef) {
		// TODO Auto-generated method stub
	}

	@Override
	public void partOpened(IWorkbenchPartReference partRef) {
		// TODO Auto-generated method stub
	}

	@Override
	public void partHidden(IWorkbenchPartReference partRef) {
		// TODO Auto-generated method stub
	}

	@Override
	public void partVisible(IWorkbenchPartReference partRef) {
		// TODO Auto-generated method stub
	}

	@Override
	public void partInputChanged(IWorkbenchPartReference partRef) {
		// TODO Auto-generated method stub
	}

	@Override
	public void resourceChanged(IResourceChangeEvent event) {
		// TODO Auto-generated method stub	
	}	
}
