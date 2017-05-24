package es.ucm.fdi.edd.ui.dialogs;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import es.ucm.fdi.edd.core.erlang.concurrent.model.ErlangAnswer;
import es.ucm.fdi.edd.core.erlang.concurrent.model.ErlangAnswer.SimpleChosen;
import es.ucm.fdi.edd.core.erlang.concurrent.model.ErlangQuestion;
import es.ucm.fdi.edd.ui.Activator;

public class QuestionTabDialog {

	private static final String UNABLE_QUESTION_TEXT = "Unable question. To enable this question, you must to answer the previous ones.";
	private static final String ANSWER_QUESTION_TEXT = "Unable question. The question is already answered.";

	private static Image check = AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "/icons/check.gif").createImage();	

	private TabItem tab;
//	private Label info;
	private QuestionDialog dialog;
	private List<Button> buttons;
	private QuestionTabDialog child = null;
	private ErlangQuestion question;
	private String id;
	
	public QuestionTabDialog(QuestionDialog dialog, TabFolder tabFolder, int style, ErlangQuestion question, String id) {
		tab = new TabItem(tabFolder, style);
		this.dialog = dialog;
		this.question = question;
		this.id = id;
		this.createTab(id);
	}
	
	private void createTab(String id) {
		tab.setText("Question " + id);
	    tab.setToolTipText(question.getText());
	    tab.setControl(createQuestionControl(id));
	}
	
	private Control createQuestionControl(String tabId){
		Composite panel = new Composite(tab.getParent(), SWT.NONE);

		RowLayout rowLayout = new RowLayout();
        rowLayout.wrap = false;
        rowLayout.pack = true;
        rowLayout.justify = true;
        rowLayout.type = SWT.VERTICAL;
        rowLayout.marginLeft = 5;
        rowLayout.marginTop = 5;
        rowLayout.marginRight = 5;
        rowLayout.marginBottom = 5;
        rowLayout.spacing = 4;
        panel.setLayout(rowLayout);

//        info = new Label(panel, SWT.LEFT);
//        info.setForeground(new Color(tab.getDisplay(), 255,0,0));
//        info.setText(UNABLE_QUESTION_TEXT);
        
		// Se añade el texto de la pregunta
		new Label(panel, SWT.LEFT).setText(question.getText());

		// Se añaden las posibles respuestas
		Label answerLabel = new Label(panel, SWT.LEFT + SWT.BOLD);
		answerLabel.setText("\nAnswers:");
		this.makeButtons(tabId, panel);

		return panel;
	}

	private void makeButtons(String tabId, Composite panel) {
		ErlangAnswer aux = question.getAnswer();

        buttons = new LinkedList<Button>();
        int i = 1;
		for (ErlangAnswer answer : question.getAnswers()) {
			Composite buttonPanel = new Composite(panel, SWT.NONE);
			RowLayout buttonRowLayout = new RowLayout(SWT.HORIZONTAL);
			String text = answer.getText();
			
	        buttonPanel.setLayout(buttonRowLayout);
			Button b = new Button(buttonPanel, SWT.WRAP | SWT.RADIO);
			buttons.add(b);

			Label buttonLabel = new Label(buttonPanel, SWT.LEFT + SWT.BOLD);
			buttonLabel.setText(text);
			b.addSelectionListener(new QuestionDialogButtonListener(dialog, answer, question, this, i));
			if (aux == answer) {
				b.setSelection(true);
				tab.setImage(check);
				if (answer.getChosen().equals(SimpleChosen.QUESTION)) {
					this.createChild(answer.getQuestionChosen(), i);
				}
			}
			i++;
		}
//		this.unabled();
		this.enabled();
	}

	public void createChild(ErlangQuestion question, int i) {
		this.child = new QuestionTabDialog(dialog, tab.getParent(), SWT.NONE, question, id + "." +i);
		this.child.enabled();
	}

	void answer (Button button) {
		tab.setImage(check);
		for (Button b : this.buttons) {
			b.setSelection(b == button);
		}
	}

	public void removeChild() {
		if (this.child != null) {
			this.child.removeChild();
			this.child.tab.dispose();
			this.child = null;
		}
	}
	
	public boolean enabled() {
		boolean enable = !this.question.isAnswer();
		this.setButtonsEnabled(enable);
		if (this.child != null) {
			this.child.enabled();
		}
//		if (enable) {
//			info.setText("");
//		} else {
//			info.setText(ANSWER_QUESTION_TEXT);
//			if (this.child != null) {
//				this.child.enabled();
//			}
//		}
		return enable;
	}

	public void unabled() {
		this.setButtonsEnabled(false);
	}

	private void setButtonsEnabled(boolean enable) {
		for (Button b : this.buttons) {
			b.setEnabled(enable);
		}
	}
}
