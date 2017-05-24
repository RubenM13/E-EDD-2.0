package es.ucm.fdi.edd.core.erlang.model;

public class EddInfo {
	
	private String questionUnformatted;
	private Long clause;
	private String file;
	private Long line;

	public EddInfo(String questionUnformatted, Long clause, String file, Long line) {
		this.questionUnformatted = questionUnformatted;
		this.clause = clause;
		this.file = file;
		this.line = line;
	}

	public String getQuestionUnformatted() {
		return questionUnformatted;
	}

	public void setQuestionUnformatted(String questionUnformatted) {
		this.questionUnformatted = questionUnformatted;
	}

	public Long getClause() {
		return clause;
	}

	public void setClause(Long clause) {
		this.clause = clause;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public Long getLine() {
		return line;
	}

	public void setLine(Long line) {
		this.line = line;
	}
}
