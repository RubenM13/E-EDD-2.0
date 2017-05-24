package es.ucm.fdi.edd.core.json.model2;

import java.util.LinkedList;

import com.google.gson.annotations.SerializedName;


public class Pregunta {

	@SerializedName("-id")
	private Long id;
	@SerializedName("-idAyuda")
	private Long idAyuda;
	@SerializedName("-idTipoPregunta")
	private Long idTipoPregunta;
	@SerializedName("-pregunta")
	private String pregunta;
	@SerializedName("-image")
	private String image;
	
	@SerializedName("respuestaLink")
	private LinkedList<RespuestaLink> respuestasLink;

	public Pregunta(Long id, Long idAyuda, Long idTipoPregunta, String pregunta, String image) {
		this.id = id;
		this.idAyuda = idAyuda;
		this.idTipoPregunta = idTipoPregunta;
		this.pregunta = pregunta;
		this.image = image;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdAyuda() {
		return idAyuda;
	}

	public void setIdAyuda(Long idAyuda) {
		this.idAyuda = idAyuda;
	}

	public Long getIdTipoPregunta() {
		return idTipoPregunta;
	}

	public void setIdTipoPregunta(Long idTipoPregunta) {
		this.idTipoPregunta = idTipoPregunta;
	}

	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public LinkedList<RespuestaLink> getRespuestasLink() {
		return respuestasLink;
	}

	public void setRespuestasLink(LinkedList<RespuestaLink> respuestasLink) {
		this.respuestasLink = respuestasLink;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
