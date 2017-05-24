package es.ucm.fdi.edd.core.erlang.concurrent.model;

import java.util.LinkedList;
import java.util.List;

public class ErlangQuestion {

	private int nodeId;
	private String text;
	private List<ErlangAnswer> answers;
	private String callrec;

	// Respuesta actual dada por el usuario a la pregunta
	private ErlangAnswer answer;

	public ErlangQuestion() {
		this.answers = new LinkedList<ErlangAnswer>();
		this.answer = null;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text.replace("\"", "");
	}

	public boolean isAnswer() {
		if (this.answer != null && this.answer.getChosen().equals(ErlangAnswer.SimpleChosen.QUESTION)) {
			return this.answer.getQuestionChosen().isAnswer();
		} else {
			return this.answer != null;
		}
	}

	public String getActualText() {
		if (this.answer != null && this.answer.getChosen().equals(ErlangAnswer.SimpleChosen.QUESTION)) {
			return this.answer.getQuestionChosen().getActualText();
		} else {
			String text = this.text;
			text += "\nAnswers:";
			for (int i = 1; i <= this.answers.size(); i++) {
				ErlangAnswer answer = this.answers.get(i-1);
				text += "\n -Answer " + i + ": " + answer.getText();
			}
			return text;
		}
	}

	public List<ErlangAnswer> getAnswers() {
		return answers;
	}

	public void addAnswer(ErlangAnswer answer) {
		this.answers.add(answer);
		answer.setId(this.answers.size());
	}

	public List<String> getActualAnswerTextList() {
		if (this.answer != null && this.answer.getChosen().equals(ErlangAnswer.SimpleChosen.QUESTION)) {
			return this.answer.getQuestionChosen().getActualAnswerTextList();
		} else {
			List<String> aux = new LinkedList<String>();
			for (int i = 1; i <= this.answers.size(); i++) {
				aux.add("Answer " + i);
			}
			return aux;
		}
	}

	public boolean undoAnswer() {
		if (this.answer == null) {
			return true;
		} else {
			if (this.answer.getChosen().equals(ErlangAnswer.SimpleChosen.QUESTION)) {
				this.answer.getQuestionChosen().undoAnswer();
			} else {
				this.answer = null;
			}
			return false;
		}
	}

	public String getCallrec() {
		return callrec;
	}

	public void setCallrec(String callrec) {
		this.callrec = callrec;
	}

	public ErlangAnswer getAnswer() {
		return answer;
	}

	public void setAnswer(ErlangAnswer answer) {
		this.answer = answer;
	}
	
	void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}
	
	public int getNodeId() {
		return this.nodeId;
	}
}
