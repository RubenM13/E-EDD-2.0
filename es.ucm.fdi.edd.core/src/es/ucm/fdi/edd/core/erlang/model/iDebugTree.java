package es.ucm.fdi.edd.core.erlang.model;

import java.util.List;
import java.util.Map;

public interface iDebugTree {

	public abstract Map<Integer, ? extends iEddVertex> getVertexesMap();

	public abstract List<EddEdge> getEdgesMap();

}