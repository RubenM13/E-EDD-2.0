package es.ucm.fdi.edd.core.json.model2;

import java.util.LinkedList;

import com.google.gson.annotations.SerializedName;

public class BloqueTipoPregunta {

	@SerializedName("tipoPregunta")
	private LinkedList<TipoPregunta> tipoPreguntas;
	
	public BloqueTipoPregunta() {
		//
	}

	public LinkedList<TipoPregunta> getTipoPreguntas() {
		return tipoPreguntas;
	}

	public void setTipoPreguntas(LinkedList<TipoPregunta> tipoPreguntas) {
		this.tipoPreguntas = tipoPreguntas;
	}
}
