package es.ucm.fdi.edd.ui.dialogs;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;

import es.ucm.fdi.edd.core.erlang.concurrent.model.ErlangAnswer;
import es.ucm.fdi.edd.core.erlang.concurrent.model.ErlangQuestion;
import es.ucm.fdi.edd.ui.views.EddDebugControler;

public class QuestionDialogButtonListener implements SelectionListener {

	private static EddDebugControler controler = EddDebugControler.getEddDebugControler();

	private QuestionDialog dialog;
	private ErlangAnswer answer;
	private ErlangQuestion question;
	private QuestionTabDialog tab;
	private int i;

	public QuestionDialogButtonListener(QuestionDialog dialog, ErlangAnswer answer, ErlangQuestion question, QuestionTabDialog tab, int i) {
		this.dialog = dialog;
		this.answer = answer;
		this.question = question;
		this.tab = tab;
		this.i = i;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		this.selected(e);
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		this.selected(e);
	}

	private void selected(SelectionEvent e) {
		this.question.setAnswer(answer);
		this.tab.answer((Button) e.getSource()); 
		this.tab.removeChild();
		System.out.println(this.answer);
		switch (this.answer.getChosen()) {
			case QUESTION:
				if (this.answer.getQuestionChosen() != null) {
					this.tab.createChild(this.answer.getQuestionChosen(), i);
				}
				break;
			default:
				dialog.activateNextQuestion();
//				controler.setAnswer(dialog.getMessagePos(), "Answer " + i);
				controler.setAnswer(question.getNodeId(), "Answer " + i);
				break;
		}
	}
}
