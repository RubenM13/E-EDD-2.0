package es.ucm.fdi.edd.core.json.model2;

import com.google.gson.annotations.SerializedName;

public class Guia {

	@SerializedName("-id")
	private Long id;
	@SerializedName("-name")
	private String name;
	@SerializedName("-image")
	private String image;
	@SerializedName("tema")
	private Tema temas;
	private BloqueRespuestas bloqueRespuestas;
	private BloqueTipoPregunta bloqueTipoPregunta;
	
	public Guia(Long id, String name, String image) {
		this.id = id;
		this.name = name;
		this.image =  image;
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

	public Tema getTemas() {
		return temas;
	}

	public void setTemas(Tema temas) {
		this.temas = temas;
	}

	public BloqueRespuestas getBloqueRespuestas() {
		return bloqueRespuestas;
	}

	public void setBloqueRespuestas(BloqueRespuestas bloqueRespuestas) {
		this.bloqueRespuestas = bloqueRespuestas;
	}

	public BloqueTipoPregunta getBloqueTipoPregunta() {
		return bloqueTipoPregunta;
	}

	public void setBloqueTipoPregunta(BloqueTipoPregunta bloqueTipoPregunta) {
		this.bloqueTipoPregunta = bloqueTipoPregunta;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
