package es.ucm.fdi.edd.core.erlang.concurrent.converter;

import java.util.HashMap;
import java.util.Map;

import com.ericsson.otp.erlang.OtpErlangList;
import com.ericsson.otp.erlang.OtpErlangLong;
import com.ericsson.otp.erlang.OtpErlangObject;
import com.ericsson.otp.erlang.OtpErlangTuple;

import es.ucm.fdi.edd.core.erlang.concurrent.model.ErlangAnswer;
import es.ucm.fdi.edd.core.erlang.concurrent.model.ErlangQuestion;
import es.ucm.fdi.edd.core.erlang.concurrent.model.ErlangTreeInfo;
import es.ucm.fdi.edd.core.erlang.concurrent.model.ErlangTreeNode;
import es.ucm.fdi.edd.core.erlang.model.EddInfo;

public class ErlangTreeConverter {

	/**
	 * 
	 * 		OtpErlangTuple
	 * 		OtpErlangTuple
	 * @param tuple
	 */
	public static ErlangTreeInfo convertToTree(OtpErlangTuple tuple) {
		OtpErlangObject erlangObj = tuple.elementAt(0);
		if (erlangObj != null && erlangObj instanceof OtpErlangTuple) {
			Map<Integer, ErlangTreeNode> nodes = convertNodes((OtpErlangTuple) erlangObj);
			erlangObj = tuple.elementAt(1);
			if (nodes != null && !nodes.isEmpty() && erlangObj != null && erlangObj instanceof OtpErlangTuple) {
				return convertEdges((OtpErlangTuple) erlangObj, nodes);
			}
		}
		return null;

	}

	/**
	 * 
	 * OtpErlangTuple	
			vertices
			OtpErlangList
				OtpErlangTuple	
	 * @param tuple
	 * @return
	 */
	private static Map<Integer, ErlangTreeNode> convertNodes(OtpErlangTuple tuple) {
		Map<Integer, ErlangTreeNode> map = new HashMap<Integer, ErlangTreeNode>();
		OtpErlangObject erlangObj = tuple.elementAt(0);
		if (erlangObj != null && erlangObj.toString().equalsIgnoreCase("vertices")) {
			erlangObj = tuple.elementAt(1);
			if (erlangObj != null && erlangObj instanceof OtpErlangList) {
				for (OtpErlangObject obj : ((OtpErlangList) erlangObj).elements()) {
					if (obj != null && obj instanceof OtpErlangTuple) {
						ErlangTreeNode aux = convertTreeNode((OtpErlangTuple) obj);
						if (aux != null) {
							map.put((int) aux.getId(), aux);
						}
					}
				}
			}
		}
		return map;
	}

	/**
	 * OtpErlangTuple
			edges
			OtpErlangList
				OtpErlangTuple
					OtpErlangLong
					OtpErlangLong
	 * @param tuple
	 * @param nodes
	 */
	private static ErlangTreeInfo convertEdges(OtpErlangTuple tuple, Map<Integer, ErlangTreeNode> nodes) {
		OtpErlangObject erlangObj = tuple.elementAt(0);
		ErlangTreeInfo root = new ErlangTreeInfo(nodes);
		if (erlangObj != null && erlangObj.toString().equalsIgnoreCase("edges")) {
			erlangObj = tuple.elementAt(1);
			if (erlangObj != null && erlangObj instanceof OtpErlangList) {
				OtpErlangObject[] list = ((OtpErlangList) erlangObj).elements();
				for (OtpErlangObject obj : list) {
					if (obj != null && obj instanceof OtpErlangTuple) {
						// Obtenemos los ids de los nodos padre e hijo y los enlazamos
						OtpErlangTuple aux = (OtpErlangTuple) obj;
						erlangObj = aux.elementAt(0);
						if (erlangObj != null && erlangObj instanceof OtpErlangLong) {
							ErlangTreeNode node = nodes.get((int) ((OtpErlangLong) erlangObj).longValue());
							erlangObj = aux.elementAt(1);
							if (node != null && erlangObj != null && erlangObj instanceof OtpErlangLong) {
								int childId = (int) ((OtpErlangLong) erlangObj).longValue();
								node.addChild(nodes.get(childId));
								root.addEdge(node.getId(), childId);
							}
						}
					}
				}
			}
		}
		return root;
	}

	/**
	 * Convierte la información de la tupla pasada a un objeto ErlangTreeNode
	 * OtpErlangTuple
	 * 		id - OtpErlangTuple
	 *			id
	 *			NodeId - OtpErlangLong
	 *		question - OtpErlangTuple (question)
	 *		info - OtpErlangTuple
	 *			info
	 *			nodeInfo - OtpErlangTuple
	 * @param tuple
	 * @return
	 */
	private static ErlangTreeNode convertTreeNode(OtpErlangTuple tuple){
		ErlangTreeNode node = null;
		OtpErlangObject erlangObj = tuple.elementAt(0);
		// Comprueba que el primer elemento es la tupla donde esta el id
		if (erlangObj instanceof OtpErlangTuple) {
			OtpErlangTuple aux = (OtpErlangTuple) erlangObj;
			erlangObj = aux.elementAt(1);
			// Comprueba que el segundo elemeto de la tupla id corresponde con el long
			if (erlangObj != null && erlangObj instanceof OtpErlangLong) {
				node = new ErlangTreeNode(((OtpErlangLong) erlangObj).longValue());
				erlangObj = tuple.elementAt(1);
				if (erlangObj != null && erlangObj instanceof OtpErlangTuple) {
					aux = (OtpErlangTuple) erlangObj;
					erlangObj = aux.elementAt(1);
					if (erlangObj != null && erlangObj instanceof OtpErlangTuple) {
						aux = (OtpErlangTuple) erlangObj;
					}
					node.setQuestion(convertQuestion(aux));
					erlangObj = tuple.elementAt(2);
					if (erlangObj != null && erlangObj instanceof OtpErlangTuple) {
						node.setInfo(getNodeLine((OtpErlangTuple) erlangObj));
					}
					
				}
			}
		}
		return node;
	}

	/**
	 * {info,{#Pid<edderlang@localhost.58.0>,{callrec_stack_item,{call_info,{merge_con,take,[1,[b,e,h,t]]},
		{pos_info,{merge_con,"merge_con.erl",63,"(1, [H | _]) -> [H]"}}},none,[{'H',b}],[],[],[],[],[b],65}}}
	 * @return
	 */
	private static EddInfo getNodeLine(OtpErlangTuple tuple) {
		EddInfo info = null;
		OtpErlangObject erlangObj = tuple.elementAt(1);
		for (int i = 0; i < 2 ; i++) {
			if (erlangObj instanceof OtpErlangTuple) {
				OtpErlangTuple aux = (OtpErlangTuple) erlangObj;
				erlangObj = aux.elementAt(1);
			}
		}
		if (erlangObj instanceof OtpErlangTuple) {
			OtpErlangTuple aux = (OtpErlangTuple) erlangObj;
			erlangObj = aux.elementAt(0);
			if (erlangObj.toString().equals("call_info")) {
				erlangObj = aux.elementAt(2);
			} else {
				if (erlangObj.toString().equals("receive_info")) {
					erlangObj = aux.elementAt(1);
				}
			}
			
			if (erlangObj != null && erlangObj instanceof OtpErlangTuple) {
				aux = (OtpErlangTuple) erlangObj;
				if (aux.elements().length == 1) {
					aux = (OtpErlangTuple) aux.elementAt(0);
				}
				erlangObj = aux.elementAt(1);
				if (erlangObj instanceof OtpErlangTuple) {
					aux = (OtpErlangTuple) erlangObj;
					erlangObj = aux.elementAt(2);
					if (erlangObj instanceof OtpErlangLong) {
						info = new EddInfo(aux.elementAt(3).toString(), -1L, aux.elementAt(1).toString(), ((OtpErlangLong) erlangObj).longValue());
					}
				}
			}
		}
		return info;
	}

	/**
	 * Convierte la información de la tupla pasada a un objeto ErlangQuestion
	 * OtpErlangTuple
	 *		question
	 *		text 	- OtpErlangString
	 *		answers - OtpErlangList
	 *			OtpErlangTuple (answer)
	 *		OtpErlangString
	 * @param tuple
	 * @return
	 */
	private static ErlangQuestion convertQuestion(OtpErlangTuple tuple){
		ErlangQuestion question = new ErlangQuestion();

		//Obtiene el texto de la pregunta
		OtpErlangObject erlangObj = tuple.elementAt(1);
		if (erlangObj != null) {
			question.setText(erlangObj.toString());
		}

		erlangObj = tuple.elementAt(3);
		if (erlangObj != null) {
			question.setCallrec(erlangObj.toString());
		}

		// Obtiene la información relativa a las posibles respuestas de la pregunta
		erlangObj = tuple.elementAt(2);
		if (erlangObj != null && erlangObj instanceof OtpErlangList) {
			OtpErlangList list = (OtpErlangList) erlangObj;
			for (OtpErlangObject obj : list.elements()) {
				if (obj != null && obj instanceof OtpErlangTuple) {
					question.addAnswer(convertAnswer((OtpErlangTuple) obj));
				}
			}
		}
		return question;
	}

	/**
	 * Convierte la información de la tupla pasada a un objeto ErlangAnswer
	 * OtpErlangTuple
	 *		answer
	 *		text    - 	OtpErlangString
	 *		chosen  -	OtpErlangAtom || OtpErlangTuple (question)
	 * @param tuple
	 * @return
	 */
	private static ErlangAnswer convertAnswer(OtpErlangTuple tuple){
		ErlangAnswer answer = new ErlangAnswer();

		//Obtiene el texto de la pregunta
		OtpErlangObject erlangObj = tuple.elementAt(1);
		if (erlangObj != null) {
			answer.setText(erlangObj.toString());
		}
		erlangObj = tuple.elementAt(2);
		if (erlangObj != null) {
			if (erlangObj instanceof OtpErlangTuple) {
				OtpErlangTuple aux = (OtpErlangTuple) erlangObj;
				if (aux.elementAt(0).toString().equalsIgnoreCase("question")) {
					answer.setQuestionChosen(convertQuestion(aux));
				} else {
					answer.setChosen(aux.elementAt(0).toString());
				}
			} else {
				answer.setChosen(erlangObj.toString());
			}
		}
		return answer;
	}
}
