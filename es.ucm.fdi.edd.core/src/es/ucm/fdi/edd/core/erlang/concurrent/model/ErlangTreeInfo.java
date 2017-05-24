package es.ucm.fdi.edd.core.erlang.concurrent.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import es.ucm.fdi.edd.core.erlang.model.EddEdge;
import es.ucm.fdi.edd.core.erlang.model.iDebugTree;

public class ErlangTreeInfo implements iDebugTree{

	private Map<Integer, ErlangTreeNode> nodes;
//	private Map<Long, Long> edges;
	private List<EddEdge> edgesMap;

	public ErlangTreeInfo(Map<Integer, ErlangTreeNode> nodes) {
		this.nodes = nodes;
//		this.edges = new HashMap<Long, Long>();
		this.edgesMap = new LinkedList<EddEdge>();
	}

	public Map<Integer, ErlangTreeNode> getVertexesMap() {
		return nodes;
	}

	@Override
	public List<EddEdge> getEdgesMap() {
		return edgesMap;
	}

	public void addEdge(long parentId, long childId) {
		this.edgesMap.add(new EddEdge(parentId, childId));
//		this.edges.put(parentId, childId);
	}
}
