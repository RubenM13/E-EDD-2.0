package es.ucm.fdi.edd.core.json.model2;

import com.google.gson.annotations.SerializedName;

public class RespuestaLink {
	
	@SerializedName("-idRespuesta")
	private Long idRespuesta;
	@SerializedName("-esRespuesta")
	private boolean respuesta;
	
	public RespuestaLink(Long idRespuesta, boolean respuesta) {
		this.idRespuesta = idRespuesta;
		this.respuesta = respuesta;
	}

	public Long getIdRespuesta() {
		return idRespuesta;
	}

	public void setIdRespuesta(Long idRespuesta) {
		this.idRespuesta = idRespuesta;
	}

	public boolean isRespuesta() {
		return respuesta;
	}

	public void setRespuesta(boolean respuesta) {
		this.respuesta = respuesta;
	}
}
