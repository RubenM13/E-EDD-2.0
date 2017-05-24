package es.ucm.fdi.edd.core.json.model2;

import com.google.gson.annotations.SerializedName;

public class Ayuda {

	@SerializedName("-idAyuda")
	private Long idAyuda;
	@SerializedName("#text")
	private String ayuda;
	@SerializedName("-image")
	private String image;

	public Ayuda(Long idAyuda, String ayuda, String image) {
		this.idAyuda = idAyuda;
		this.ayuda = ayuda;
		this.image = image;
	}

	public Long getIdAyuda() {
		return idAyuda;
	}

	public void setIdAyuda(Long idAyuda) {
		this.idAyuda = idAyuda;
	}

	public String getAyuda() {
		return ayuda;
	}

	public void setAyuda(String ayuda) {
		this.ayuda = ayuda;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
