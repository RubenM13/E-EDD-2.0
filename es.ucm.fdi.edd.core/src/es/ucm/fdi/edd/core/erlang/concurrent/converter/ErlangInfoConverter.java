package es.ucm.fdi.edd.core.erlang.concurrent.converter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.ericsson.otp.erlang.OtpErlangAtom;
import com.ericsson.otp.erlang.OtpErlangList;
import com.ericsson.otp.erlang.OtpErlangLong;
import com.ericsson.otp.erlang.OtpErlangObject;
import com.ericsson.otp.erlang.OtpErlangPid;
import com.ericsson.otp.erlang.OtpErlangString;
import com.ericsson.otp.erlang.OtpErlangTuple;

import es.ucm.fdi.edd.core.erlang.concurrent.model.ErlangInfo;
import es.ucm.fdi.edd.core.erlang.concurrent.model.ErlangMessage;
import es.ucm.fdi.edd.core.erlang.concurrent.model.ErlangMessage.ElementType;
import es.ucm.fdi.edd.core.erlang.concurrent.model.ErlangThread;
import es.ucm.fdi.edd.core.erlang.concurrent.model.ErlangTreeNode;

public class ErlangInfoConverter {

	/**
	 * Convierte la lista de información de los threads en formato de erlang a una lista en el formato de la aplicación
	 * @param list
	 * @return
	 */
	public static List<ErlangThread> convertThread(OtpErlangList list) {
		List<ErlangThread> threads = new LinkedList<ErlangThread>();
		for (OtpErlangObject obj : list.elements()) {
			if (obj instanceof OtpErlangTuple) {
				threads.add(convertThread((OtpErlangTuple) obj));
			}
		}
		return threads;
	}

	/**
	 * Convierte un elemento erlang de un thread al formato de la aplicación
	 * @param tuple
	 * @return
	 */
	private static ErlangThread convertThread(OtpErlangTuple tuple) {
		OtpErlangPid id = (OtpErlangPid) tuple.elementAt(1);
		if (tuple.elementAt(2) instanceof OtpErlangTuple) {
			OtpErlangTuple aux = (OtpErlangTuple) tuple.elementAt(2);
			if (aux.elementAt(1) instanceof OtpErlangTuple) {
				ErlangThread thread = convertCallThread((OtpErlangTuple) aux.elementAt(1));
				thread.setId(String.valueOf(id.id()));
				return thread;
			}
		}
		return null;
	}

	/**
	 * Obtiene la información de la llamada de inicio del thread para pasarla al formato de la aplicación
	 * @param tuple
	 * @return
	 */
	private static ErlangThread convertCallThread(OtpErlangTuple tuple) {
		ErlangThread thread = new ErlangThread();
		OtpErlangAtom className = (OtpErlangAtom) tuple.elementAt(0);
		OtpErlangAtom funtion = (OtpErlangAtom) tuple.elementAt(1);
		if (tuple.elementAt(2) instanceof OtpErlangList) {
			OtpErlangList list = (OtpErlangList) tuple.elementAt(2);
			if (list.elementAt(0) != null && list.elementAt(0) instanceof OtpErlangList
					&& list.elementAt(1) != null && list.elementAt(1) instanceof OtpErlangTuple) {
				OtpErlangList module = (OtpErlangList) list.elementAt(0);
				OtpErlangTuple fun = (OtpErlangTuple) list.elementAt(1);
				String name = className.atomValue() + ":" + funtion.atomValue() + "(" + module.toString() + ",#fun<";
				boolean first = true;
				for (OtpErlangObject obj : fun.elements()) {
					if (first) {
						first = false;
					} else {
						name += ".";
					}
					name += obj.toString();
				}
				name += ">";
				thread.setName(name);
				// Obtiene el id del thread que creo el proceso si no es el inicial
				if (list.elementAt(2) != null && list.elementAt(2) instanceof OtpErlangPid) {
					OtpErlangPid creator = (OtpErlangPid) list.elementAt(2);
					thread.setCreator(String.valueOf(creator.id()));
				}
			}
		}
		return thread;
	}

	/**
	 * Convierte la lista de información de los mesajes en formato de erlang a una lista en el formato de la aplicación
	 * @param list
	 * @return
	 */
	public static List<ErlangMessage> convertMessage(OtpErlangList list) {
		List<ErlangMessage> messages = new LinkedList<ErlangMessage>();
		int pos = 1;
		Iterator<OtpErlangObject> it = list.iterator();
		while (it.hasNext()) {
//		for (OtpErlangObject obj : list.elements()) {
			OtpErlangObject obj = it.next();
			if (obj instanceof OtpErlangTuple) {
				ErlangMessage message = convertMessage((OtpErlangTuple) obj);
				message.setPos(pos);
				messages.add(message);
				pos++;
			}
		}
		return messages;
	}

	/**
	 * Convierte un elemento erlang de un mensaje al formato de la aplicación
	 * @param tuple
	 * @return
	 */
	private static ErlangMessage convertMessage(OtpErlangTuple erlangMessage) {
		OtpErlangAtom type = (OtpErlangAtom) erlangMessage.elementAt(0);
		return convertMessage((OtpErlangTuple) erlangMessage.elementAt(1), type.atomValue());
	}

	/**
	 * Convierte un elemento erlang de un mensaje al formato de la aplicación según su tipo
	 * @param erlangMessage
	 * @param type
	 * @return
	 */
	private static ErlangMessage convertMessage(OtpErlangTuple erlangMessage, String type) {
		ErlangMessage message = new ErlangMessage();
		OtpErlangPid sender = (OtpErlangPid) erlangMessage.elementAt(1);
		OtpErlangPid reciver = (OtpErlangPid) erlangMessage.elementAt(2);
		message.setSender(String.valueOf(sender.id()));
		message.setReciver(String.valueOf(reciver.id()));
		OtpErlangLong id;
		if (type.equalsIgnoreCase("spawned")) {
			message.setType(ElementType.NEW);
			id = (OtpErlangLong) erlangMessage.elementAt(3);
		} else {
//			OtpErlangTuple info = (OtpErlangTuple) erlangMessage.elementAt(3);
			message.setInfo(erlangMessage.elementAt(3).toString());
			id = (OtpErlangLong) erlangMessage.elementAt(4);
			if (type.equalsIgnoreCase("sent")) {
				message.setType(ElementType.SEND);
			} else {
				message.setType(ElementType.GET);
			}
		}
		message.setId(id.toString());
		return message;
	}

	/**
	 * Metodo encargado de conectar la información que se pasa como parametros.
	 * Los mensajes con los nodos según la información pasada en la lista.
	 * Los threads con los mensajes que utilizan
	 * @param list
	 * 		Lista que contiene la información para enlazar los mensajes con los nodos
	 * @param threads
	 * @param messages
	 * 		Lista de mensajes que se van a enlazar con los nodos 
	 * @param nodes
	 * 		Mapa con los nodos con los que se van a enlazar los mensajes
	 */
	public static void link(OtpErlangList list, List<ErlangThread> threads, List<ErlangMessage> messages, Map<Integer, ErlangTreeNode> nodes){
		link(mapping(threads), messages);
		Map<Long, ErlangMessage> messagesMap = mapping(messages);
		for (OtpErlangObject obj : list.elements()) {
			if (obj instanceof OtpErlangTuple) {
				link((OtpErlangTuple) obj, messagesMap, nodes);
			}
		}
	}

	/**
	 * Conecta los threads con los mensajes.
	 * @param threads
	 * @param messages
	 */
	private static void link(Map<Long, ErlangThread> threads, List<ErlangMessage> messages) {
		for (ErlangMessage message : messages) {
			ErlangThread thread;
			switch (message.getType()) {
				case GET:
					thread = threads.get(Long.valueOf(message.getReciver()));
					thread.addGetMessages(message);;
					break;
				case NEW:
					thread = threads.get(Long.valueOf(message.getReciver()));
					thread.setCreatorMessage(message);
					thread = threads.get(Long.valueOf(message.getSender()));
					thread.addSentMessages(message);
					break;
				case SEND:
					thread = threads.get(Long.valueOf(message.getSender()));
					thread.addSentMessages(message);
					break;
			}
		}
	}

	private static <E extends ErlangInfo> Map<Long, E> mapping(List<E> list) {
		Map<Long, E> result = new HashMap<Long, E>();
		for (E info : list) {
			result.put(Long.valueOf(info.getId()), info);
		}
		return result;
	}

	private static void link(OtpErlangTuple info, Map<Long, ErlangMessage> messages, Map<Integer, ErlangTreeNode> nodes) {
		OtpErlangLong idMessage = (OtpErlangLong) info.elementAt(0);
		ErlangMessage message = messages.get(idMessage.longValue());
		if (message != null) {
			OtpErlangString leido = (OtpErlangString) info.elementAt(1);
			String leidoStr = leido.stringValue();
			System.out.print("Mensaje " + message.getId() + " - " + message.getPos() + " [Preguntas: ");
			for(int i = 0; i < leidoStr.length(); i++) {
				Integer nodeId = (int) leidoStr.charAt(i);
				ErlangTreeNode node = nodes.get(nodeId);
				if (node != null) {
					System.out.print(nodeId + " ");
					message.addNode(node);
				} else {
					System.out.println("Node with id " + nodeId + " not found");
				}
			}
			System.out.print("]");
			System.out.println();
		} else {
			System.out.println("Message with id " + idMessage + " not found");
		}
	}
}
