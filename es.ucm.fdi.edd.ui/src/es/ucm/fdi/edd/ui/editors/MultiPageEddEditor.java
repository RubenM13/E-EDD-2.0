package es.ucm.fdi.edd.ui.editors;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
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
import org.eclipse.swt.widgets.FontDialog;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IShowEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.editors.text.TextEditor;

import es.ucm.fdi.emf.model.ed2.presentation.Ed2Editor;

public class MultiPageEddEditor extends Ed2Editor implements IResourceChangeListener, IShowEditorInput, ISelectionProvider {
	
	/** The text editor used in page 0. */
	private TextEditor editor;
	
	/** The font chosen in page 1. */
	private Font font;

	/** The text widget used in page 2. */
	private StyledText text;
	
	public MultiPageEddEditor() {
		super();
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this);
	}
	
	public void createPages() {
		super.createPages();
		createPage0();
		createPage1();
		createPage2();
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

			/**
			 * Sets the font related data to be applied to the text in page 2.
			 */
			private void setFont() {
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
	
	@Override
	public IActionBars getActionBars() {
		return getActionBarContributor().getActionBars();
	}
	
	@Override
	public EditingDomainActionBarContributor getActionBarContributor() {
		return (EditingDomainActionBarContributor)getEditorSite().getActionBarContributor();
	}	
	
	/**
	 * Saves the multi-page editor's document.
	 */
	public void doSave(IProgressMonitor monitor) {
//		getEditor(0).doSave(monitor);
//		getEditor(3).doSave(monitor);
//		getEditor(4).doSave(monitor);
	}
	
	/**
	 * Saves the multi-page editor's document as another file. Also updates the
	 * text for page 0's tab, and updates this multi-page editor's input to
	 * correspond to the nested editor's.
	 */
	public void doSaveAs() {
//		IEditorPart editor = getEditor(0);
//		editor.doSaveAs();
//		setPageText(0, editor.getTitle());
//		setInput(editor.getEditorInput());
		
//		IEditorPart ed2Editor = getEditor(3);
//		ed2Editor.doSaveAs();
//		setPageText(3, ed2Editor.getTitle());
//		setInput(ed2Editor.getEditorInput());
		
//		IEditorPart ed2DiagramEditor = getEditor(4);
//		ed2DiagramEditor.doSaveAs();
//		setPageText(4, ed2DiagramEditor.getTitle());
//		setInput(ed2DiagramEditor.getEditorInput());
	}
	
	@Override
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		// TODO Auto-generated method stub
	}

	@Override
	public void removeSelectionChangedListener(ISelectionChangedListener listener) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public ISelection getSelection() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSelection(ISelection selection) {
		// TODO Auto-generated method stub
	}

	@Override
	public void showEditorInput(IEditorInput editorInput) {
		// TODO Auto-generated method stub
	}

	@Override
	public void resourceChanged(IResourceChangeEvent event) {
		// TODO Auto-generated method stub
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
}
