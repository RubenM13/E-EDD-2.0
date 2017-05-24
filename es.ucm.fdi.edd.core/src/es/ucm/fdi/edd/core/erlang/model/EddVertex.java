package es.ucm.fdi.edd.core.erlang.model;

import java.util.List;

public class EddVertex implements iEddVertex {
	
	private Integer node;
	private String question;
	private EddInfo info;
	private MFA mfa;
	
	/**
	 * Normal debugging
	 * 
	 * @param node
	 * @param question
	 * @param info
	 */
	public EddVertex(Integer node, String question, EddInfo info) {
		this.node = node;
		this.question = question;
		this.info = info;
	}
	
	/**
	 * Normal debugging
	 * 
	 * @param node
	 * @param question
	 * @param info
	 */
	public EddVertex(Long node, String question, EddInfo info, MFA mfa) {
		this.node = node.intValue();
		this.question = question;
		this.info = info;
		this.mfa = mfa;
	}
	
	/**
	 * Zoom debugging
	 * 
	 * @param node
	 * @param question
	 */
	public EddVertex(Long node, String question) {
		this.node = node.intValue();
		this.question = question;
	}

	/* (non-Javadoc)
	 * @see es.ucm.fdi.edd.core.erlang.model.iEddVertex#getNode()
	 */
	@Override
	public Integer getIdNode() {
		return node;
	}

	public void setNode(Integer node) {
		this.node = node;
	}

	/* (non-Javadoc)
	 * @see es.ucm.fdi.edd.core.erlang.model.iEddVertex#getQuestion()
	 */
	@Override
	public String getQuestionText() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
	
	/* (non-Javadoc)
	 * @see es.ucm.fdi.edd.core.erlang.model.iEddVertex#getInfo()
	 */
	@Override
	public EddInfo getInfo() {
		return info;
	}

	public void setInfo(EddInfo info) {
		this.info = info;
	}

	/* (non-Javadoc)
	 * @see es.ucm.fdi.edd.core.erlang.model.iEddVertex#getMfa()
	 */
	@Override
	public MFA getMfa() {
		return mfa;
	}

	public void setMfa(MFA mfa) {
		this.mfa = mfa;
	}

	@Override
	public List<String> getActualAnswerTextList() {
		return null;
	}
}