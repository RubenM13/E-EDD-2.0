package es.ucm.fdi.edd.core.erlang.model;

import java.util.List;

public interface iEddVertex {

	public abstract Integer getIdNode();

	public abstract String getQuestionText();

	public abstract List<String> getActualAnswerTextList();

	public abstract EddInfo getInfo();

	public abstract MFA getMfa();

}