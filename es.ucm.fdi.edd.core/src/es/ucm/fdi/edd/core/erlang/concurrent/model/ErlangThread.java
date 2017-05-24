package es.ucm.fdi.edd.core.erlang.concurrent.model;

import java.util.LinkedList;
import java.util.List;

public class ErlangThread extends ErlangInfo {

	private String name;

	private String creator;

	// Mensaje que creo el Thread
	private ErlangMessage creatorMessage;

	// Mensajes enviados por el thread
	private List<ErlangMessage> sentMessages;

	// Mensajes recividos por el thread
	private List<ErlangMessage> getMessages;

	public ErlangThread() {
		this.creator = "";
		this.sentMessages = new LinkedList<ErlangMessage>();
		this.getMessages = new LinkedList<ErlangMessage>();
	}

	public ErlangThread(String id, String creator, String name) {
		this();
		this.id = id;
		this.creator = creator;
		this.name = name;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullName() {
		if (creator != null && !creator.isEmpty()) {
			return this.name + "," + this.creator + ")";
		} else {
			return this.name + ")";
		}
	}

	public ErlangMessage getCreatorMessage() {
		return creatorMessage;
	}

	public void setCreatorMessage(ErlangMessage creatorMessage) {
		this.creatorMessage = creatorMessage;
	}

	public List<ErlangMessage> getSentMessages() {
		return sentMessages;
	}

	public void addSentMessages(ErlangMessage sentMessage) {
		this.sentMessages.add(sentMessage);
	}

	public List<ErlangMessage> getGetMessages() {
		return getMessages;
	}

	public void addGetMessages(ErlangMessage getMessage) {
		this.getMessages.add(getMessage);
	}
}
