package es.ucm.fdi.edd.core.json.model;

import com.google.gson.annotations.SerializedName;

public class Vertices {
	
	@SerializedName("node")
	private String node;
	@SerializedName("question")
	private String question;
	
	public Vertices() {
		// 
	}

	public Vertices(String node, String question) {
		this.node = node;
		this.question = question;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
}
