package es.ucm.fdi.edd.core.erlang.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.ericsson.otp.erlang.OtpErlangAtom;
import com.ericsson.otp.erlang.OtpErlangList;
import com.ericsson.otp.erlang.OtpErlangLong;
import com.ericsson.otp.erlang.OtpErlangObject;
import com.ericsson.otp.erlang.OtpErlangString;
import com.ericsson.otp.erlang.OtpErlangTuple;

public class ZoomDebugTree implements iDebugTree{
	
	private OtpErlangTuple zoomDebugTreeTuple;
	
	private Map<Integer, EddVertex> vertexesMap;
	private List<EddEdge> edgesMap;
	
	public ZoomDebugTree(OtpErlangTuple tuple) {
		this.zoomDebugTreeTuple = tuple;
		
		vertexesMap = new HashMap<Integer, EddVertex>();
		edgesMap = new LinkedList<EddEdge>();
		
		initialize();
	}
	
	private void initialize() {
		processVertexes();
		processEdges();
	}

	private void processVertexes() {
		OtpErlangTuple vertextesTuple = (OtpErlangTuple) zoomDebugTreeTuple.elementAt(0);
		if (vertextesTuple.arity() == 2) {
			OtpErlangAtom vertexesAtom = (OtpErlangAtom) vertextesTuple.elementAt(0);
			if (vertexesAtom.atomValue().equals("vertices")) {
				OtpErlangList vertexesList = (OtpErlangList) vertextesTuple.elementAt(1);
				System.out.println(Arrays.toString(vertexesList.elements()));
				for (OtpErlangObject child : vertexesList) {
					if (child instanceof OtpErlangTuple) {
						OtpErlangTuple vertexTuple = (OtpErlangTuple) child;
						processVertexTuple(vertexTuple);
					}
				}
			}
			else {
				System.out.println("The 'vertexes' atom is malformmed...");
			}
		}
		else {
			System.out.println("The 'vertexes' tuple is malformmed...");
		}
	}
	
	private void processVertexTuple(OtpErlangTuple vertexTuple) {
		if (vertexTuple.arity() == 2) {
			
			Long node = null; 
			String question = null;
			
			OtpErlangTuple t1 = (OtpErlangTuple) vertexTuple.elementAt(0);
			if (t1.arity() == 2) {
				OtpErlangAtom idAtom = (OtpErlangAtom) t1.elementAt(0);
				if (idAtom.atomValue().equals("id")) {
					OtpErlangLong nodeIndex = (OtpErlangLong) t1.elementAt(1);
					node = nodeIndex.longValue();
				}
				else {
					System.out.println("The 'id' atom is malformmed...");
				}
			}
			else {
				System.out.println("The 'edge' tuple is malformmed...");
			}
			
			OtpErlangTuple t2 = (OtpErlangTuple) vertexTuple.elementAt(1);
			if (t2.arity() == 2) {
				OtpErlangAtom questionAtom = (OtpErlangAtom) t2.elementAt(0);
				if (questionAtom.atomValue().equals("question")) {
					OtpErlangString questionMsg = (OtpErlangString) t2.elementAt(1);
					question = questionMsg.stringValue();
				}
				else {
					System.out.println("The 'question' atom is malformmed...");
				}
			}
			else {
				System.out.println("The 'edge' tuple is malformmed...");
			}
						
			EddVertex vertex = new EddVertex(node, question);
			vertexesMap.put(node.intValue(), vertex);
		}
		else {
			System.out.println("The 'vertex' tuple is malformmed...");
		}
	}

	private void processEdges() {
		OtpErlangTuple edgesTuple = (OtpErlangTuple) zoomDebugTreeTuple.elementAt(1);
		if (edgesTuple.arity() == 2) {
			OtpErlangAtom edgesAtom = (OtpErlangAtom) edgesTuple.elementAt(0);
			if (edgesAtom.atomValue().equals("edges")) {
				OtpErlangList edgesList = (OtpErlangList) edgesTuple.elementAt(1);
				System.out.println(Arrays.toString(edgesList.elements()));
				for (OtpErlangObject child : edgesList) {
					if (child instanceof OtpErlangTuple) {
						OtpErlangTuple edgeTuple = (OtpErlangTuple) child;
						if (edgeTuple.arity() == 2) {
							OtpErlangLong from = (OtpErlangLong) edgeTuple.elementAt(0);
							OtpErlangLong to = (OtpErlangLong) edgeTuple.elementAt(1);
							EddEdge edge = new EddEdge(from.longValue(), to.longValue());
							edgesMap.add(edge);
						}
						else {
							System.out.println("The 'edge' tuple is malformmed...");
						}
					}
				}
			}
			else {
				System.out.println("The 'edges' atom is malformmed...");
			}
		}
		else {
			System.out.println("The 'edges' tuple is malformmed...");
		}
	}

	public Map<Integer, EddVertex> getVertexesMap() {
		return vertexesMap;
	}

	public void setVertexesMap(Map<Integer, EddVertex> vertexesMap) {
		this.vertexesMap = vertexesMap;
	}

	public List<EddEdge> getEdgesMap() {
		return edgesMap;
	}

	public void setEdgesMap(List<EddEdge> edgesMap) {
		this.edgesMap = edgesMap;
	}
}