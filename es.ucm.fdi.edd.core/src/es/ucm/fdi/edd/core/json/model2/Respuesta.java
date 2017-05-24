package es.ucm.fdi.edd.core.json.model2;

import com.google.gson.annotations.SerializedName;

public class Respuesta {

	@SerializedName("-idRespuesta")
	private Long idRespuesta;
	@SerializedName("#text")
	private String respuesta;

	public Respuesta(Long idRespuesta, String respuesta) {
		this.idRespuesta = idRespuesta;
		this.respuesta = respuesta;
	}

	public Long getIdRespuesta() {
		return idRespuesta;
	}

	public void setIdRespuesta(Long idRespuesta) {
		this.idRespuesta = idRespuesta;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
}
