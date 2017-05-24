package es.ucm.fdi.edd.core.json.model;

import com.google.gson.annotations.SerializedName;

public class Edges {
	
	@SerializedName("from")
	private int from;
	@SerializedName("to")
	private int to;
	
	public Edges() {
		// 
	}

	public Edges(int from, int to) {
		this.from = from;
		this.to = to;
	}

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public int getTo() {
		return to;
	}

	public void setTo(int to) {
		this.to = to;
	}
}
