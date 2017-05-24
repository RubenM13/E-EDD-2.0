package es.ucm.fdi.edd.ui.editors;

import java.io.File;
import java.io.StringWriter;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.StringTokenizer;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.AdapterFactoryItemDelegator;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FontDialog;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IShowEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.ide.IGotoMarker;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.eclipse.ui.part.MultiPageEditorSite;

import es.ucm.fdi.emf.model.ed2.diagram.part.Ed2DiagramEditor;
import es.ucm.fdi.emf.model.ed2.presentation.Ed2Editor;

/**
 * An example showing how to create a multi-page editor. This example has 3
 * pages:
 * <ul>
 * <li>page 0 contains a nested text editor.
 * <li>page 1 allows you to change the font used in page 2
 * <li>page 2 shows the words in page 0 in sorted order
 * </ul>
 */
public class MultiPageEditor extends MultiPageEditorPart implements
		IResourceChangeListener, IShowEditorInput, IEditingDomainProvider,
		ISelectionProvider, IMenuListener, IViewerProvider, IGotoMarker {
	
	protected AdapterFactoryEditingDomain editingDomain;
	protected ComposedAdapterFactory adapterFactory;
	protected Viewer currentViewer;
	protected ISelectionChangedListener selectionChangedListener;
	protected Collection<ISelectionChangedListener> selectionChangedListeners = new ArrayList<ISelectionChangedListener>();
	protected ISelection editorSelection = StructuredSelection.EMPTY;
	protected IStatusLineManager contentOutlineStatusLineManager;
	protected TreeViewer contentOutlineViewer;
	

	/** The text editor used in page 0. */
	private TextEditor editor;
	private Ed2Editor ed2Editor;
	private Ed2DiagramEditor ed2DiagramEditor;

	/** The font chosen in page 1. */
	private Font font;

	/** The text widget used in page 2. */
	private StyledText text;

	/**
	 * Creates a multi-page editor example.
	 */
	public MultiPageEditor() {
		super();
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this);
	}
	
	/**
	 * @see org.eclipse.ui.part.MultiPageEditorPart#createSite(org.eclipse.ui.IEditorPart)
	 */
	protected IEditorSite createSite(IEditorPart page) {
		IEditorSite site = null;
		if (page == editor) {
			site = new MultiPageEditorSite(this, page) {
				@Override
				public String getId() {
					// Sets this ID so nested editor is configured for XML source
//					return ContentTypeIdForXML.ContentTypeID_XML + ".source"; //$NON-NLS-1$;
					return super.getId();
				}
			};
		}
		else {
			site = super.createSite(page);
		}
		return site;
	}

	/**
	 * Creates page 0 of the multi-page editor, which contains a text editor.
	 */
	void createPage0() {
		try {
			editor = new TextEditor();
			int index = addPage(editor, getEditorInput());
			setPageText(index, editor.getTitle());
		} catch (PartInitException e) {
			ErrorDialog.openError(getSite().getShell(),	"Error creating nested text editor", null, e.getStatus());
		}
	}

	/**
	 * Creates page 1 of the multi-page editor, which allows you to change the
	 * font used in page 2.
	 */
	void createPage1() {
		Composite composite = new Composite(getContainer(), SWT.NONE);
		GridLayout layout = new GridLayout();
		composite.setLayout(layout);
		layout.numColumns = 2;

		Button fontButton = new Button(composite, SWT.NONE);
		GridData gd = new GridData(GridData.BEGINNING);
		gd.horizontalSpan = 2;
		fontButton.setLayoutData(gd);
		fontButton.setText("Change Font...");
		fontButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				setFont();
			}
		});

		int index = addPage(composite);
		setPageText(index, "Properties");
	}

	/**
	 * Creates page 2 of the multi-page editor, which shows the sorted text.
	 */
	void createPage2() {
		Composite composite = new Composite(getContainer(), SWT.NONE);
		FillLayout layout = new FillLayout();
		composite.setLayout(layout);
		text = new StyledText(composite, SWT.H_SCROLL | SWT.V_SCROLL);
		text.setEditable(false);

		int index = addPage(composite);
		setPageText(index, "Preview");
	}

	/**
	 * Creates the pages of the multi-page editor.
	 */
	protected void createPages() {
		createPage0();
		createPage1();
		createPage2();
//		addEMFEditorPage3();
//		addGMFEditorPage4();
	}

	private void addEMFEditorPage3() {
		try {
			
			File fileToOpen = new File("D:/workspace/runtime-tests/EDDMergesort/edd/merge.ed2");
			if (fileToOpen.exists() && fileToOpen.isFile()) {
			    IFileStore fileStore = EFS.getLocalFileSystem().getStore(fileToOpen.toURI());
			    IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		        IDE.openEditorOnFileStore(page, fileStore);
			} else {
			    //Do something if the file does not exist
			}
			
			ed2Editor = new Ed2Editor();
			int index = addPage(ed2Editor, getEditorInput());
			setPageText(index, ed2Editor.getTitle());
		} catch (PartInitException e) {
			ErrorDialog.openError(getSite().getShell(),	"Error creating nested EMF editor", null, e.getStatus());
		}
	}
	
	private void addGMFEditorPage4() {
		try {
			ed2DiagramEditor = new Ed2DiagramEditor();
			int index = addPage(ed2DiagramEditor, getEditorInput());
			setPageText(index, ed2DiagramEditor.getTitle());
		} catch (PartInitException e) {
			ErrorDialog.openError(getSite().getShell(),	"Error creating nested GMF editor", null, e.getStatus());
		}
	}

	/**
	 * The <code>MultiPageEditorPart</code> implementation of this
	 * <code>IWorkbenchPart</code> method disposes all nested editors.
	 * Subclasses may extend.
	 */
	public void dispose() {
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
		super.dispose();
	}

	/**
	 * Saves the multi-page editor's document.
	 */
	public void doSave(IProgressMonitor monitor) {
		getEditor(0).doSave(monitor);
//		getEditor(3).doSave(monitor);
//		getEditor(4).doSave(monitor);
	}

	/**
	 * Saves the multi-page editor's document as another file. Also updates the
	 * text for page 0's tab, and updates this multi-page editor's input to
	 * correspond to the nested editor's.
	 */
	public void doSaveAs() {
		IEditorPart editor = getEditor(0);
		editor.doSaveAs();
		setPageText(0, editor.getTitle());
		setInput(editor.getEditorInput());
		
//		IEditorPart ed2Editor = getEditor(3);
//		ed2Editor.doSaveAs();
//		setPageText(3, ed2Editor.getTitle());
//		setInput(ed2Editor.getEditorInput());
//		
//		IEditorPart ed2DiagramEditor = getEditor(4);
//		ed2DiagramEditor.doSaveAs();
//		setPageText(4, ed2DiagramEditor.getTitle());
//		setInput(ed2DiagramEditor.getEditorInput());
	}

	/*
	 * (non-Javadoc) Method declared on IEditorPart
	 */
	public void gotoMarker(IMarker marker) {
		setActivePage(0);
		IDE.gotoMarker(getEditor(0), marker);
	}

	/**
	 * The <code>MultiPageEditorExample</code> implementation of this method
	 * checks that the input is an instance of <code>IFileEditorInput</code>.
	 */
	public void init(IEditorSite site, IEditorInput editorInput) throws PartInitException {
		if (!(editorInput instanceof IFileEditorInput))
			throw new PartInitException("Invalid Input: Must be IFileEditorInput");
		super.init(site, editorInput);
	}

	/*
	 * (non-Javadoc) Method declared on IEditorPart.
	 */
	public boolean isSaveAsAllowed() {
		return true;
	}

	/**
	 * Calculates the contents of page 2 when the it is activated.
	 */
	protected void pageChange(int newPageIndex) {
		super.pageChange(newPageIndex);
		if (newPageIndex == 2) {
			sortWords();
		}
	}

	/**
	 * Closes all project files on project close.
	 */
	public void resourceChanged(final IResourceChangeEvent event) {
		if (event.getType() == IResourceChangeEvent.PRE_CLOSE) {
			Display.getDefault().asyncExec(new Runnable() {
				public void run() {
					IWorkbenchPage[] pages = getSite().getWorkbenchWindow().getPages();
					for (int i = 0; i < pages.length; i++) {
						if (((FileEditorInput) editor.getEditorInput()).getFile().getProject().equals(event.getResource())) {
							IEditorPart editorPart = pages[i].findEditor(editor.getEditorInput());
							pages[i].closeEditor(editorPart, true);
						}
						/*
						if (((FileEditorInput) ed2Editor.getEditorInput()).getFile().getProject().equals(event.getResource())) {
							IEditorPart editorPart = pages[i].findEditor(ed2Editor.getEditorInput());
							pages[i].closeEditor(editorPart, true);
						}
						
						if (((FileEditorInput) ed2DiagramEditor.getEditorInput()).getFile().getProject().equals(event.getResource())) {
							IEditorPart editorPart = pages[i].findEditor(ed2DiagramEditor.getEditorInput());
							pages[i].closeEditor(editorPart, true);
						}
						*/
					}
				}
			});
		}
	}

	/**
	 * Sets the font related data to be applied to the text in page 2.
	 */
	void setFont() {
		FontDialog fontDialog = new FontDialog(getSite().getShell());
		fontDialog.setFontList(text.getFont().getFontData());
		FontData fontData = fontDialog.open();
		if (fontData != null) {
			if (font != null) {
				font.dispose();
			}
			font = new Font(text.getDisplay(), fontData);
			text.setFont(font);
		}
	}

	/**
	 * Sorts the words in page 0, and shows them in page 2.
	 */
	void sortWords() {
		String editorText = editor.getDocumentProvider().getDocument(editor.getEditorInput()).get();
		StringTokenizer tokenizer = new StringTokenizer(editorText,	" \t\n\r\f!@#\u0024%^&*()-_=+`~[]{};:'\",.<>/?|\\");
		ArrayList<String> editorWords = new ArrayList<String>();
		while (tokenizer.hasMoreTokens()) {
			editorWords.add(tokenizer.nextToken());
		}

		Collections.sort(editorWords, Collator.getInstance());
		StringWriter displayText = new StringWriter();
		for (int i = 0; i < editorWords.size(); i++) {
			displayText.write(((String) editorWords.get(i)));
			displayText.write(System.getProperty("line.separator"));
		}
		text.setText(displayText.toString());
	}

	@Override
	public void showEditorInput(IEditorInput editorInput) {
		String name = editorInput.getName();
		/*String id = getActivePageInstance().getId();
		if (name.equals(ICoreConstants.BUILD_FILENAME_DESCRIPTOR)) {
			if (!BuildInputContext.CONTEXT_ID.equals(id))
				setActivePage(SHOW_SOURCE ? BuildInputContext.CONTEXT_ID : BuildPage.PAGE_ID);
		} else if (name.equals(ICoreConstants.PLUGIN_FILENAME_DESCRIPTOR) || name.equals(ICoreConstants.FRAGMENT_FILENAME_DESCRIPTOR)) {
			if (!PluginInputContext.CONTEXT_ID.equals(id)) {
				if (SHOW_SOURCE) {
					setActivePage(PluginInputContext.CONTEXT_ID);
				} else if (fInputContextManager.hasContext(BundleInputContext.CONTEXT_ID)) {
					setActivePage(ExtensionsPage.PAGE_ID);
				} else {
					setActivePage(OverviewPage.PAGE_ID);
				}
			}
		} else if (!BundleInputContext.CONTEXT_ID.equals(id)) {
			setActivePage(SHOW_SOURCE ? BundleInputContext.CONTEXT_ID : OverviewPage.PAGE_ID);
		}*/
	}

	@Override
	public Viewer getViewer() {
		return currentViewer;
	}
	
	@Override
	public void menuAboutToShow(IMenuManager menuManager) {
		((IMenuListener)getEditorSite().getActionBarContributor()).menuAboutToShow(menuManager);
		
	}

	@Override
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		selectionChangedListeners.add(listener);
	}

	@Override
	public ISelection getSelection() {
		return editorSelection;
	}

	@Override
	public void removeSelectionChangedListener(ISelectionChangedListener listener) {
		selectionChangedListeners.remove(listener);
	}

	@Override
	public void setSelection(ISelection selection) {
		editorSelection = selection;

		for (ISelectionChangedListener listener : selectionChangedListeners) {
			listener.selectionChanged(new SelectionChangedEvent(this, selection));
		}
		setStatusLineManager(selection);
	}

	@Override
	public EditingDomain getEditingDomain() {
		return editingDomain;
	}
	
	public void setStatusLineManager(ISelection selection) {
		IStatusLineManager statusLineManager = currentViewer != null && currentViewer == contentOutlineViewer ? contentOutlineStatusLineManager
				: getActionBars().getStatusLineManager();
		if (statusLineManager != null) {
			if (selection instanceof IStructuredSelection) {
				Collection<?> collection = ((IStructuredSelection)selection).toList();
				switch (collection.size()) {
					case 0: {
						statusLineManager.setMessage("Selected Nothing");
						break;
					}
					case 1: {
						String text = new AdapterFactoryItemDelegator(adapterFactory).getText(collection.iterator().next());
						String message = String.format("Selected Object: {0}", text);
						statusLineManager.setMessage(message);
						break;
					}
					default: {
						String message = String.format("Selected {0} Objects", Integer.toString(collection.size()));
						statusLineManager.setMessage(message);
						break;
					}
				}
			}
			else {
				statusLineManager.setMessage("");
			}
		}
	}
	
	public IActionBars getActionBars() {
		return getActionBarContributor().getActionBars();
	}
	
	public EditingDomainActionBarContributor getActionBarContributor() {
		return (EditingDomainActionBarContributor)getEditorSite().getActionBarContributor();
	}
}