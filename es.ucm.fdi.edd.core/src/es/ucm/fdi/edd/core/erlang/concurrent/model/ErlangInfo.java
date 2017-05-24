package es.ucm.fdi.edd.core.erlang.concurrent.model;

import java.util.LinkedList;
import java.util.List;

import es.ucm.fdi.edd.core.erlang.concurrent.model.ErlangMessage.ElementType;


public class ErlangInfo {

	// Identificador del elemento
	protected String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public static List<ErlangMessage> getErlangMessages() {
		List<ErlangMessage> messages = new LinkedList<ErlangMessage>();
		messages.add(new ErlangMessage("m1", ElementType.NEW, "Thread1", "Thread2", "info"));
		messages.add(new ErlangMessage("m1", ElementType.SEND, "Thread1", "Thread1", "info"));
		messages.add(new ErlangMessage("m2", ElementType.NEW, "Thread1", "Thread3", "info"));
		messages.add(new ErlangMessage("m4", ElementType.SEND, "Thread1", "Thread3", "info"));
		messages.add(new ErlangMessage("m3", ElementType.NEW, "Thread3", "Thread4", "info"));
		messages.add(new ErlangMessage("m4", ElementType.GET, "Thread1", "Thread3", "info"));
		messages.add(new ErlangMessage("m6", ElementType.SEND, "Thread1", "Thread3", "info"));
		messages.add(new ErlangMessage("m5", ElementType.SEND, "Thread2", "Thread4", "info"));
		messages.add(new ErlangMessage("m7", ElementType.SEND, "Thread4", "Thread3", "info"));
		messages.add(new ErlangMessage("m5", ElementType.GET, "Thread2", "Thread4", "info"));
		messages.add(new ErlangMessage("m7", ElementType.GET, "Thread4", "Thread3", "info"));
		
		for (ErlangMessage m : messages) {
			m.setNodes(ErlangTreeNode.getExampleList());
		}

		return messages;
	}

	public static List<ErlangThread> getEntities() {
		List<ErlangThread> entities = new LinkedList<ErlangThread>();
		entities.add(new ErlangThread("Thread1", "", "class:method(arg,#fun<x>"));
		entities.add(new ErlangThread("Thread2", "Thread1", "class:method(arg,#fun<x>"));
		entities.add(new ErlangThread("Thread3", "Thread1", "class:method(arg,#fun<x>"));
		entities.add(new ErlangThread("Thread4", "Thread3", "class:method(arg,#fun<x>"));
		return entities;
	}
}
