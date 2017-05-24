package es.ucm.fdi.edd.core.erlang.concurrent.model;

import java.util.LinkedList;
import java.util.List;

public class ErlangMessage extends ErlangInfo {

	public enum ElementType {
		NEW,
		SEND,
		GET;
	}

	// Tipo del dato a visualizar
	private ElementType type;

	private String sender;
	private String reciver;

	private String info;

	private List<ErlangTreeNode> nodes;

	private int pos;
	
	public ErlangMessage() {
		nodes = new LinkedList<ErlangTreeNode>();
	}

	public ErlangMessage(String id, ElementType type, String sender, String reciver, String info) {
		this();
		this.id = id;
		this.type = type;
		this.sender = sender;
		this.reciver = reciver;
		this.info = info;
	}

	public ElementType getType() {
		return type;
	}

	public void setType(ElementType type) {
		this.type = type;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReciver() {
		return reciver;
	}

	public void setReciver(String reciver) {
		this.reciver = reciver;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public List<ErlangTreeNode> getNodes() {
		return nodes;
	}

	public void addNode(ErlangTreeNode node) {
		this.nodes.add(node);
	}

	protected void setNodes(List<ErlangTreeNode> nodes) {
		this.nodes = nodes;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public String getLinkInfo() {
		String result = "Pos " + this.pos + " MessageId " + this.id +"";
		for (ErlangTreeNode node : this.nodes) {
			result += " | NodeID: " + node.getId() + " Question: " + node.getQuestionText();
		}
		return result;
	}
}
