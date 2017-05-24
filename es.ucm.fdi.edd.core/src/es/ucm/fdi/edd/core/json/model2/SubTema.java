package es.ucm.fdi.edd.core.json.model2;

import com.google.gson.annotations.SerializedName;

public class SubTema {
	
	@SerializedName("-id")
	private Long id;
	@SerializedName("-name")
	private String name;
	private BloquePreguntas bloquePreguntas;
	
	private BloqueAyudas bloqueAyudas;

	public SubTema(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BloquePreguntas getBloquePreguntas() {
		return bloquePreguntas;
	}

	public void setBloquePreguntas(BloquePreguntas bloquePreguntas) {
		this.bloquePreguntas = bloquePreguntas;
	}

	public BloqueAyudas getBloqueAyudas() {
		return bloqueAyudas;
	}

	public void setBloqueAyudas(BloqueAyudas bloqueAyudas) {
		this.bloqueAyudas = bloqueAyudas;
	}
}
