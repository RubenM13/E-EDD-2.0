package es.ucm.fdi.edd.core.json.model2;

import com.google.gson.annotations.SerializedName;

public class TipoPregunta {

	@SerializedName("-idTipoPregunta")
	private Long idTipoPregunta;
	@SerializedName("#text")
	private String tipoPregunta;

	public TipoPregunta(Long idTipoPregunta, String tipoPregunta) {
		this.idTipoPregunta = idTipoPregunta;
		this.tipoPregunta = tipoPregunta;
	}

	public Long getIdTipoPregunta() {
		return idTipoPregunta;
	}

	public void setIdTipoPregunta(Long idTipoPregunta) {
		this.idTipoPregunta = idTipoPregunta;
	}

	public String getTipoPregunta() {
		return tipoPregunta;
	}

	public void setTipoPregunta(String tipoPregunta) {
		this.tipoPregunta = tipoPregunta;
	}
}
