package es.ucm.fdi.edd.ui.perspectives;

import org.eclipse.debug.ui.IDebugUIConstants;
import org.eclipse.search.ui.NewSearchUI;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.console.IConsoleConstants;
import org.eclipse.ui.progress.IProgressConstants;
import org.erlide.ui.ErlideUIConstants;
import org.erlide.ui.views.eval.LiveExpressionsView;
import org.erlide.ui.views.processlist.ProcessListView;

import es.ucm.fdi.edd.ui.views.EDDThreadsTreeView;
import es.ucm.fdi.edd.ui.views.EDDTreeView;
import es.ucm.fdi.edd.ui.views.EDDViewer;
import es.ucm.fdi.edd.ui.views.EDDebugView;
import es.ucm.fdi.edd.ui.views.EddSequenceView;
import es.ucm.fdi.edd.ui.views.GraphvizView;

/**
 * Erlang Declarative Debugger Perspective definition.
 */
@SuppressWarnings("restriction")
public class EDDPerspective implements IPerspectiveFactory {

	private IPageLayout layout;

	/**
	 * Erlang Declarative Debugger Perspective
	 */
	public EDDPerspective() {
		super();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IPerspectiveFactory#createInitialLayout(org.eclipse.ui.IPageLayout)
	 */
	public void createInitialLayout(IPageLayout pageLayout) {
		this.layout = pageLayout;
		addViews();
		addActionSets();
		addNewWizardShortcuts();
		addPerspectiveShortcuts();
		addViewShortcuts();
	}

	/**
	 * Sets the initial contents of the perspective.
	 */
	private void addViews() {
		String editorArea = layout.getEditorArea();
		
		IFolderLayout consoleFolder = layout.createFolder("bottom", IPageLayout.BOTTOM, 0.75f, editorArea);
		consoleFolder.addView("org.eclipse.pde.runtime.LogView");
		consoleFolder.addView(IPageLayout.ID_PROBLEM_VIEW);
		consoleFolder.addView(IConsoleConstants.ID_CONSOLE_VIEW);
		consoleFolder.addView(IPageLayout.ID_PROP_SHEET);
		consoleFolder.addView(ProcessListView.ID);
		consoleFolder.addView(LiveExpressionsView.ID);
		consoleFolder.addPlaceholder(NewSearchUI.SEARCH_VIEW_ID);
		consoleFolder.addPlaceholder(IPageLayout.ID_BOOKMARKS);
		consoleFolder.addPlaceholder(IProgressConstants.PROGRESS_VIEW_ID);
        		
		IFolderLayout topLeftFolder= layout.createFolder("leftTop", IPageLayout.TOP, 0.50f, editorArea);
		topLeftFolder.addView(EDDebugView.VIEW_ID);
		topLeftFolder.addView(IDebugUIConstants.ID_DEBUG_VIEW);
		
		IFolderLayout leftFolder= layout.createFolder("left", IPageLayout.LEFT, 0.20f, editorArea);
		leftFolder.addView(ErlideUIConstants.NAVIGATOR_VIEW_ID);
		leftFolder.addView(IPageLayout.ID_PROJECT_EXPLORER);
		
		IFolderLayout topRightFolder= layout.createFolder("rightTop", IPageLayout.RIGHT, 0.50f, "leftTop");
//		topRightFolder.addView(IDebugUIConstants.ID_VARIABLE_VIEW);
//		topRightFolder.addView(IDebugUIConstants.ID_BREAKPOINT_VIEW);
//		topRightFolder.addView(IDebugUIConstants.ID_EXPRESSION_VIEW);
//		topRightFolder.addView(IDebugUIConstants.ID_REGISTER_VIEW);
//		topRightFolder.addView(EDDViewer.VIEW_ID);
		topRightFolder.addView(GraphvizView.VIEW_ID);
		topRightFolder.addView(EDDThreadsTreeView.VIEW_ID);
		topRightFolder.addView(EddSequenceView.VIEW_ID);
		
		IFolderLayout rightFolder= layout.createFolder("right", IPageLayout.RIGHT, 0.75f, editorArea);
		rightFolder.addView(IPageLayout.ID_OUTLINE);
		rightFolder.addView(EDDTreeView.ID);
	}

	/**
	 * Sets the initial contents of the action sets.
	 */
	private void addActionSets() {
//		layout.addActionSet("org.eclipse.debug.ui.launchActionSet"); 
//		layout.addActionSet("org.eclipse.debug.ui.debugActionSet"); 
//		layout.addActionSet("org.eclipse.debug.ui.profileActionSet"); 
//		layout.addActionSet("org.eclipse.jdt.debug.ui.JDTDebugActionSet"); 
//		layout.addActionSet("org.eclipse.jdt.junit.JUnitActionSet"); 
//		layout.addActionSet("org.eclipse.team.ui.actionSet"); 
//		layout.addActionSet("org.eclipse.team.cvs.ui.CVSActionSet"); 
//		layout.addActionSet("org.eclipse.ant.ui.actionSet.presentation"); 
//		layout.addActionSet(JavaUI.ID_ACTION_SET);
//		layout.addActionSet(JavaUI.ID_ELEMENT_CREATION_ACTION_SET);
//		layout.addActionSet(IPageLayout.ID_NAVIGATE_ACTION_SET); 
		
		layout.addActionSet(IDebugUIConstants.LAUNCH_ACTION_SET);
        layout.addActionSet(IDebugUIConstants.DEBUG_ACTION_SET);
        layout.addActionSet(IPageLayout.ID_NAVIGATE_ACTION_SET);
		layout.addActionSet("org.erlide.ui.actions.openActionSet");
	}

	/**
	 * Sets the initial contents of the "Window/Open perspective" menu.
	 */
	private void addPerspectiveShortcuts() {
		layout.addPerspectiveShortcut("org.eclipse.ui.resourcePerspective");
		layout.addPerspectiveShortcut("es.ucm.fdi.edd.ui.perspectives.EDDPerspective");
		layout.addPerspectiveShortcut("org.erlide.ui.perspectives.ErlangPerspective");
	}

	/**
	 * Sets the initial contents of the "New Wizard" toolbar menu.
	 */
	private void addNewWizardShortcuts() {
		layout.addNewWizardShortcut("org.eclipse.ui.wizards.new.file");
		layout.addNewWizardShortcut("org.eclipse.ui.editors.wizards.UntitledTextFileWizard");
		layout.addNewWizardShortcut("org.eclipse.ui.wizards.new.folder");
		layout.addNewWizardShortcut("es.ucm.fdi.edd.ui.wizards.EDDQuestionsWizard");
		layout.addNewWizardShortcut("org.erlide.ui.wizards.ErlangSourceFile");
        layout.addNewWizardShortcut("org.erlide.ui.wizards.newproject");
        layout.addNewWizardShortcut("org.erlide.ui.wizards.ErlangScratchpad");
	}

	/** 
	 * Sets the initial contents of the "Window/Show View" menu.
	 */
	private void addViewShortcuts() {
		layout.addShowViewShortcut(IConsoleConstants.ID_CONSOLE_VIEW);
		layout.addShowViewShortcut(IPageLayout.ID_PROP_SHEET);
		layout.addShowViewShortcut(IDebugUIConstants.ID_DEBUG_VIEW);
		layout.addShowViewShortcut(IPageLayout.ID_PROJECT_EXPLORER);
		layout.addShowViewShortcut(IDebugUIConstants.ID_VARIABLE_VIEW);
		layout.addShowViewShortcut(IPageLayout.ID_OUTLINE);
		
		layout.addShowViewShortcut(EDDViewer.VIEW_ID);
		layout.addShowViewShortcut(EDDTreeView.ID);
		layout.addShowViewShortcut(GraphvizView.VIEW_ID);
		layout.addShowViewShortcut(EDDThreadsTreeView.VIEW_ID);
		layout.addShowViewShortcut(EddSequenceView.VIEW_ID);
	}
}