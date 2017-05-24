package es.ucm.fdi.edd.core.erlang.model;

public class EddEdge {
	
	private Integer from;
	private Integer to;

	public EddEdge(Long from, Long to) {
		this.from = from.intValue();
		this.to = to.intValue();
	}
	
	public EddEdge(Integer from, Integer to) {
		this.from = from;
		this.to = to;
	}

	public Integer getFrom() {
		return from;
	}

	public void setFrom(Integer from) {
		this.from = from;
	}

	public Integer getTo() {
		return to;
	}

	public void setTo(Integer to) {
		this.to = to;
	}
}