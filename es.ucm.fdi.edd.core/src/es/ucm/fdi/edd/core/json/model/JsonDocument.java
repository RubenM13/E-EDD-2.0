package es.ucm.fdi.edd.core.json.model;

import java.util.LinkedList;

import com.google.gson.annotations.SerializedName;

public class JsonDocument {
	
	@SerializedName("vertices")
	private LinkedList<Vertices> vertices;
	@SerializedName("edges")
	private LinkedList<Edges> edges;
	
	public JsonDocument() {
		// 
	}

	public LinkedList<Vertices> getVertices() {
		return vertices;
	}

	public void setVertices(LinkedList<Vertices> vertices) {
		this.vertices = vertices;
	}

	public LinkedList<Edges> getEdges() {
		return edges;
	}

	public void setEdges(LinkedList<Edges> edges) {
		this.edges = edges;
	}
}
