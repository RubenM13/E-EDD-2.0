package es.ucm.fdi.edd.ui.wizards;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.IDialogPage;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.FindReplaceDocumentAdapter;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.erlide.ui.editors.erl.ErlangEditor;
import org.erlide.ui.editors.util.EditorUtility;

import es.ucm.fdi.edd.core.json.model.Vertices;

public class EDDQuestionsWizardQuestionPage extends WizardPage {
	
	private static final Map<String, String> myMap;
    static {
        myMap = new LinkedHashMap<String, String>();
        myMap.put("y", "Yes");
        myMap.put("n", "No");
        myMap.put("t", "Trusted");
        myMap.put("d", "Don't know");
        myMap.put("i", "Inadmissible");
//        myMap.put("u", "Undo ");
//        myMap.put("a", "Abort");
    }

	private ISelection selection;
	private Text questionText;
	private int index;

	/**
	 * Constructor for SampleNewWizardPage.
	 * @param selection
	 * @param document
	 * @param i
	 */
	public EDDQuestionsWizardQuestionPage(ISelection selection, int index) {
		super("dynamicPage_" + index);
		setTitle("EDD Questions Wizard");
		setDescription("Wizard page " + index);
		this.selection = selection;
		this.index = index;
	}

	/**
	 * @see IDialogPage#createControl(Composite)
	 */
	public void createControl(Composite parent) {
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.verticalSpacing = 9;
		
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(layout);
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Label label = new Label(container, SWT.NULL);
		label.setText("Question:");

		questionText = new Text(container, SWT.BORDER | SWT.SINGLE);
		questionText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent event) {
				selectAndReveal();
			}

			private void selectAndReveal() {
				try {
					Path path = new Path("/EDD/examples/mergesort/merge_ok.erl");
				    IFile erlFile = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
					IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
					IEditorPart editorPart = IDE.openEditor(page, erlFile);
					ErlangEditor editor = (ErlangEditor) editorPart;
					final IDocument document = editor.getDocument();
					
					int line = 5 + index;
					int lineStart = document.getLineOffset(line);
	                int lineLength = document.getLineLength(line);
	                EditorUtility.revealInEditor(editorPart, lineStart, lineLength);
					
	                String text = "mergesort([], _Comp) -> [];";  //questionText.getText();
					FindReplaceDocumentAdapter dad = new FindReplaceDocumentAdapter(document);
					IRegion region = dad.find(0, text, true, false, false, false);
//					EditorUtility.revealInEditor(editorPart, region);
	            } catch (PartInitException e) {
					e.printStackTrace();
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			}
		});
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		questionText.setLayoutData(gd);
		
	    createRadioButtonsSection(container);
	    createComponentsButton(container);
		
		initialize();
		dialogChanged();
		setControl(container);
	}

	private void createRadioButtonsSection(Composite parent) {
		for(Entry<String, String> entry : myMap.entrySet()) {
		    String key = entry.getKey();
		    String value = entry.getValue();
			
		    Button radioButton = new Button(parent, SWT.RADIO);
		    radioButton.setText(value);
		    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		    gd.horizontalSpan = 2;
		    radioButton.setLayoutData(gd);
		}
	}
	
	protected void createComponentsButton(Composite parent) {
		Composite compButtons = new Composite(parent, SWT.NONE);
		compButtons.setLayout(new GridLayout(5, false));
		compButtons.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END | GridData.VERTICAL_ALIGN_END));

		Button button1 = new Button(compButtons, SWT.PUSH);
		button1.setText("Yes");
		button1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				updateStatusPage();
			}
		});

		Button button2 = new Button(compButtons, SWT.PUSH);
		button2.setText("No");
		button2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				updateStatusPage();
			}
		});

		Button button3 = new Button(compButtons, SWT.PUSH);
		button3.setText("Trusted");
		button3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				updateStatusPage();
			}
		});
		
		Button button4 = new Button(compButtons, SWT.PUSH);
		button4.setText("Don't know");
		button4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				updateStatusPage();
			}
		});

		Button button5 = new Button(compButtons, SWT.PUSH);
		button5.setText("Inadmissible");
		button5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				updateStatusPage();
			}
		});
	}

	/**
	 * Tests if the current workbench selection is a suitable container to use.
	 */
	private void initialize() {
		if (selection != null && selection.isEmpty() == false && selection instanceof IStructuredSelection) {
			IStructuredSelection ssel = (IStructuredSelection) selection;
		}
	}

	/**
	 * Ensures that both text fields are set.
	 */
	private void dialogChanged() {
		updateStatus(null);
	}

	private void updateStatus(String message) {
		setErrorMessage(message);
		setPageComplete(message == null);
	}
	
	/**
	 * Valida el estado de la página, si todo esta OK
	 */
	protected void updateStatusPage() {
		setPageComplete(false);
		if (false) {
			setErrorMessage(null);
			setMessage("", WARNING);
			return;
		} else if (true) {
			setErrorMessage(null);
			setMessage(getDescription(), INFORMATION);
			setPageComplete(true);
			return;
		}

		setErrorMessage(null);
		setMessage(null);
		setPageComplete(true);
	}
	
	private boolean validatePage() {
		return false;
	}
	
	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		EDDQuestionsWizard wizard = (EDDQuestionsWizard) getWizard();
		Vertices vertice = wizard.getVertices().get(index);
		questionText.setText(vertice.getQuestion());
	}
}