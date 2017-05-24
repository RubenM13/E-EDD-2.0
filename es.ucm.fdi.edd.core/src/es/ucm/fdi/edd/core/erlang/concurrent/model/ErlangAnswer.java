package es.ucm.fdi.edd.core.erlang.concurrent.model;

public class ErlangAnswer {

	private int id;
	private String text;
	private ErlangQuestion questionChosen;

	public enum SimpleChosen {
		CORRECT,
		INCORRECT,
		QUESTION
	}

	private SimpleChosen chosen;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text.replace("\"", "");
	}

	public ErlangQuestion getQuestionChosen() {
		return questionChosen;
	}

	public void setQuestionChosen(ErlangQuestion chosen) {
		this.questionChosen = chosen;
		this.chosen = SimpleChosen.QUESTION;
	}

	public SimpleChosen getChosen() {
		return chosen;
	}

	public void setChosen(String chosen) {
		this.chosen = SimpleChosen.valueOf(chosen.toUpperCase());
		this.questionChosen = null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String toString() {
		return "[ErlangANswer] Text: " + this.text + " Chosen: " + this.chosen;
	}
}
