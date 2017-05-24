package es.ucm.fdi.edd.ui.views;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.Viewport;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.parts.ContentOutlinePage;
import org.eclipse.gef.ui.parts.SelectionSynchronizer;
import org.eclipse.gmf.runtime.common.core.util.Log;
import org.eclipse.gmf.runtime.common.core.util.Trace;
import org.eclipse.gmf.runtime.diagram.core.util.ViewType;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.TreeContainerEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.TreeDiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.TreeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.DiagramUIDebugOptions;
import org.eclipse.gmf.runtime.diagram.ui.internal.DiagramUIPlugin;
import org.eclipse.gmf.runtime.diagram.ui.internal.DiagramUIStatusCodes;
import org.eclipse.gmf.runtime.diagram.ui.internal.l10n.DiagramUIPluginImages;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramEditDomain;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramEditorInput;
import org.eclipse.gmf.runtime.draw2d.ui.internal.parts.ScrollableThumbnailEx;
import org.eclipse.gmf.runtime.draw2d.ui.internal.parts.ThumbnailEx;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.part.PageBook;
import org.erlide.ui.editors.erl.ErlangEditor;

/**
 * A diagram outline page
 */
@SuppressWarnings("restriction")
public class EddDiagramOutlinePage extends ContentOutlinePage implements IAdaptable {
	
    /** the ID of the outline */
    protected static final int ID_OUTLINE = 0;

    /** the id of the over view */
    protected static final int ID_OVERVIEW = 1;

	private PageBook pageBook;
	private Control outline;
	private Canvas overview;
	private IAction showOutlineAction, showOverviewAction;
	private boolean overviewInitialized;
	private ThumbnailEx thumbnail;
	private DisposeListener disposeListener;
	
	private ErlangEditor editor;
	private SelectionSynchronizer synchronizer;
	private KeyHandler keyHandler;
	

	/**
	 * @param viewer
	 * @param debugFile 
	 */
	public EddDiagramOutlinePage(EditPartViewer viewer, IFile debugFile) {
		super(viewer);
		
		initialize(debugFile);
	}

	private void initialize(IFile debugFile) {
		try {
			IFile erlFile = ResourcesPlugin.getWorkspace().getRoot().getFile(debugFile.getFullPath());
			IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			IEditorPart editorPart = IDE.openEditor(page, erlFile);
			editor = (ErlangEditor) editorPart;
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.Page#init(org.eclipse.ui.part.IPageSite)
	 */
	public void init(IPageSite pageSite) {
		super.init(pageSite);
		ActionRegistry registry = getActionRegistry();
		IActionBars bars = pageSite.getActionBars();
		String id = ActionFactory.UNDO.getId();
		bars.setGlobalActionHandler(id, registry.getAction(id));
		id = ActionFactory.REDO.getId();
		bars.setGlobalActionHandler(id, registry.getAction(id));
		id = ActionFactory.DELETE.getId();
		bars.setGlobalActionHandler(id, registry.getAction(id));
		bars.updateActionBars();

		// Toolbar refresh to solve linux defect RATLC525198
		bars.getToolBarManager().markDirty();
	}

	/**
	 * configures the outline viewer
	 */
	protected void configureOutlineViewer() {
		getViewer().setEditDomain(getEditDomain());
		getViewer().setEditPartFactory(getOutlineViewEditPartFactory());

		MenuManager outlineContextMenuProvider = getOutlineContextMenuProvider(getViewer());
		if (outlineContextMenuProvider != null) {
			getViewer().setContextMenu(outlineContextMenuProvider);
		}

		getViewer().setKeyHandler(getKeyHandler());
		// getViewer().addDropTargetListener(new LogicTemplateTransferDropTargetListener(getViewer()));
		IToolBarManager tbm = this.getSite().getActionBars()
				.getToolBarManager();
		showOutlineAction = new Action() {
			public void run() {
				showPage(ID_OUTLINE);
			}
		};
		showOutlineAction
				.setImageDescriptor(DiagramUIPluginImages.DESC_OUTLINE);
		showOutlineAction
				.setToolTipText(DiagramUIMessages.OutlineView_OutlineTipText);
		tbm.add(showOutlineAction);
		showOverviewAction = new Action() {

			public void run() {
				showPage(ID_OVERVIEW);
			}
		};
		showOverviewAction
				.setImageDescriptor(DiagramUIPluginImages.DESC_OVERVIEW);
		showOverviewAction
				.setToolTipText(DiagramUIMessages.OutlineView_OverviewTipText);
		tbm.add(showOverviewAction);
		showPage(getDefaultOutlineViewMode());
	}
	
	public void createControl(Composite parent) {
		pageBook = new PageBook(parent, SWT.NONE);
		outline = getViewer().createControl(pageBook);
		overview = new Canvas(pageBook, SWT.NONE);
		pageBook.showPage(outline);
		configureOutlineViewer();
		hookOutlineViewer();
		initializeOutlineViewer();
	}

	public void dispose() {
		unhookOutlineViewer();
		if (thumbnail != null) {
			thumbnail.deactivate();
		}
		this.overviewInitialized = false;
		super.dispose();
	}

	@SuppressWarnings("rawtypes")
	public Object getAdapter(Class type) {
		// if (type == ZoomManager.class)
		// return getZoomManager();
		return null;
	}

	public Control getControl() {
		return pageBook;
	}

	/**
	 * hook the outline viewer
	 */
	protected void hookOutlineViewer() {
		getSelectionSynchronizer().addViewer(getViewer());
	}

	/**
	 * initialize the outline viewer
	 */
	protected void initializeOutlineViewer() {
		try {
			TransactionUtil.getEditingDomain(getDiagram()).runExclusive(
					new Runnable() {

						public void run() {
							getViewer().setContents(getDiagram());
						}
					});
		} catch (InterruptedException e) {
			Trace.catching(DiagramUIPlugin.getInstance(),
					DiagramUIDebugOptions.EXCEPTIONS_CATCHING, getClass(),
					"initializeOutlineViewer", e); //$NON-NLS-1$
			Log.error(DiagramUIPlugin.getInstance(),
					DiagramUIStatusCodes.IGNORED_EXCEPTION_WARNING,
					"initializeOutlineViewer", e); //$NON-NLS-1$
		}
	}

	/**
	 * initialize the overview
	 */
	protected void initializeOverview() {
		LightweightSystem lws = new LightweightSystem(overview);
		RootEditPart rep = getGraphicalViewer().getRootEditPart();
		DiagramRootEditPart root = (DiagramRootEditPart) rep;
		thumbnail = new ScrollableThumbnailEx((Viewport) root.getFigure());
		// thumbnail.setSource(root.getLayer(LayerConstants.PRINTABLE_LAYERS));
		thumbnail.setSource(root.getLayer(LayerConstants.SCALABLE_LAYERS));

		lws.setContents(thumbnail);
		disposeListener = new DisposeListener() {

			public void widgetDisposed(DisposeEvent e) {
				if (thumbnail != null) {
					thumbnail.deactivate();
					thumbnail = null;
				}
			}
		};
		getEditor().addDisposeListener(disposeListener);
		this.overviewInitialized = true;
	}

	/**
	 * show page with a specific ID, possibel values are ID_OUTLINE and
	 * ID_OVERVIEW
	 * 
	 * @param id
	 */
	protected void showPage(int id) {
		if (id == ID_OUTLINE) {
			showOutlineAction.setChecked(true);
			showOverviewAction.setChecked(false);
			pageBook.showPage(outline);
			if (thumbnail != null)
				thumbnail.setVisible(false);
		} else if (id == ID_OVERVIEW) {
			if (!overviewInitialized)
				initializeOverview();
			showOutlineAction.setChecked(false);
			showOverviewAction.setChecked(true);
			pageBook.showPage(overview);
			thumbnail.setVisible(true);
		}
	}

	/**
	 * unhook the outline viewer
	 */
	protected void unhookOutlineViewer() {
		getSelectionSynchronizer().removeViewer(getViewer());
		if (disposeListener != null && getEditor() != null
				&& !getEditor().isDisposed())
			getEditor().removeDisposeListener(disposeListener);
	}


	/**
	 * getter for the editor conrolo
	 * 
	 * @return <code>Control</code>
	 */
	protected Control getEditor() {
		return getGraphicalViewer().getControl();
	}
	
	// =====================================================

	/**
     * Gets my editing domain derived from my diagram editor input.
     * <P>
     * If subclasses have a known editing domain, they should override this
     * method to return that editing domain as that will be more efficient that
     * the generic implementation provided here.
     * 
     * @return my editing domain
     */
    public TransactionalEditingDomain getEditingDomain() {
        if (getDiagram() != null) {
            return TransactionUtil.getEditingDomain(getDiagram());
        }
        return null;
    }
    
    /**
     * @see org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart#getDiagramEditDomain()
     */
    public IDiagramEditDomain getDiagramEditDomain() {
        return (IDiagramEditDomain) getEditDomain();
    }
    
    /**
	 * Returns the edit domain.
	 * 
	 * @return the edit domain
	 */
	protected DefaultEditDomain getEditDomain() {
		// TODO Auto-generated method stub
		return null; //DiagramEditDomain
	}
    
    /**
     * @see org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart#getDiagram()
     */
    public Diagram getDiagram() {
        if (editor.getEditorInput() != null)
            return ((IDiagramEditorInput) editor.getEditorInput()).getDiagram();
        return null;
    }
    
    /**
     * Creates edit part factory that will be creating tree edit parts in
     * the tree viewer
     * @return <code>EditPartFactory</code> factory for the tree viewer
     */
    protected EditPartFactory getOutlineViewEditPartFactory() {
		return new EditPartFactory() {
			public EditPart createEditPart(EditPart context, Object model) {
				if (model instanceof Diagram) {
					return new TreeDiagramEditPart(model);
                } else if (model instanceof View
                    && ViewType.GROUP.equals(((View) model).getType())) {
                    return new TreeContainerEditPart(model);
				} else {
					return new TreeEditPart(model);
				}
			}
		};
	}
    
    /**
     * Returns the context menu provider for the outline view.
     * 
     * @param viewer The outline viewer for which this context menu provider
     *  will be added.
     *  
     * @return A menu manager that can be used in the provided outline viewer
     *  or null if no context menu should be shown.
     *  
     */
    protected MenuManager getOutlineContextMenuProvider(EditPartViewer viewer) {
		return null;
	}
    
    /**
     * Returns this editor's outline-page default display mode.
     * 
     * @return int the integer value indicating the content-outline-page dispaly
     *         mode
     */
    protected int getDefaultOutlineViewMode() {
        return ID_OVERVIEW;
    }

	private EditPartViewer getGraphicalViewer() {
		// TODO Auto-generated method stub
		return null; //DiagramGraphicalViewer
	}
	
	private SelectionSynchronizer getSelectionSynchronizer() {
		// TODO Auto-generated method stub
		if (synchronizer == null)
			synchronizer = new SelectionSynchronizer();
		return synchronizer;
	}
	
	private KeyHandler getKeyHandler() {
		// TODO Auto-generated method stub
		 if (keyHandler == null) {
			 keyHandler = new KeyHandler();
			 
			 // ...
		 }
		 
		 return keyHandler;
	}
	
	private ActionRegistry getActionRegistry() {
		// TODO Auto-generated method stub
		return null;
	}
}
