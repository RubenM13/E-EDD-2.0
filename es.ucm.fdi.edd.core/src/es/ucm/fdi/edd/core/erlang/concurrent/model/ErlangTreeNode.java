package es.ucm.fdi.edd.core.erlang.concurrent.model;

import java.util.LinkedList;
import java.util.List;

import es.ucm.fdi.edd.core.erlang.model.EddInfo;
import es.ucm.fdi.edd.core.erlang.model.MFA;
import es.ucm.fdi.edd.core.erlang.model.iEddVertex;

public class ErlangTreeNode implements iEddVertex {

	private long id;
	private ErlangQuestion question;
	private EddInfo info;
	private List<ErlangTreeNode> childs;
	private ErlangTreeNode father;

	public ErlangTreeNode(long id) {
		this.setId(id);
		this.childs = new LinkedList<ErlangTreeNode>();
		setFather(null);
	}

	public long getId() {
		return id;
	}

	public Integer getIdNode() {
		return (int) this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ErlangQuestion getQuestion() {
		return question;
	}

	public void setQuestion(ErlangQuestion question) {
		this.question = question;
		this.question.setNodeId((int) this.id);
	}

	public String getQuestionText() {
		if (this.question == null) {
			return null;
		} else {
			return this.question.getActualText();
		}
	}

	public EddInfo getInfo() {
		return info;
	}

	public void setInfo(EddInfo info) {
		this.info = info;
	}

	public List<ErlangTreeNode> getChilds() {
		return childs;
	}

	public void addChild(ErlangTreeNode child) {
		this.childs.add(child);
		child.setFather(this);
	}

	public ErlangTreeNode getFather() {
		return father;
	}

	private void setFather(ErlangTreeNode father) {
		this.father = father;
	}

	public ErlangTreeNode getTreeRoot() {
		if (this.father == null) {
			return this;
		} else {
			return this.father.getTreeRoot();
		}
	}

	public MFA getMfa() {
		return null;
	}

	static List<ErlangTreeNode> getExampleList() {
		List<ErlangTreeNode> questions = new LinkedList<ErlangTreeNode>();
		ErlangTreeNode node = new ErlangTreeNode(1);
		ErlangQuestion question = new ErlangQuestion();
		question.setText("Question 1");
		ErlangAnswer answer = new ErlangAnswer();
		answer.setText("Yes");
		answer.setChosen("CORRECT");
		question.addAnswer(answer);
		answer = new ErlangAnswer();
		answer.setText("No");
		answer.setChosen("INCORRECT");
		question.addAnswer(answer);
		node.setQuestion(question);
		questions.add(node);

		node = new ErlangTreeNode(2);
		question = new ErlangQuestion();
		question.setText("Question 2");
		answer = new ErlangAnswer();
		answer.setText("Yes");
		answer.setChosen("CORRECT");
		question.addAnswer(answer);
		answer = new ErlangAnswer();
		answer.setText("No");
		answer.setChosen("INCORRECT");
		question.addAnswer(answer);
		answer = new ErlangAnswer();
		answer.setText("Neither");
		answer.setChosen("INCORRECT");
		question.addAnswer(answer);
		node.setQuestion(question);
		questions.add(node);

		return questions;
	}

	@Override
	public List<String> getActualAnswerTextList() { 
		return question.getActualAnswerTextList();
	}
}
