package es.ucm.fdi.edd.core.json.model2;

import java.util.LinkedList;

import com.google.gson.annotations.SerializedName;

public class BloqueAyudas {

	@SerializedName("ayuda")
	private LinkedList<Ayuda> ayudas;

	public BloqueAyudas() {

	}

	public LinkedList<Ayuda> getAyudas() {
		return ayudas;
	}

	public void setAyudas(LinkedList<Ayuda> Ayudas) {
		this.ayudas = Ayudas;
	}
}
