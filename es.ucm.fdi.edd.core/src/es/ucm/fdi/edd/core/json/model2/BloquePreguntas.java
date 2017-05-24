package es.ucm.fdi.edd.core.json.model2;

import java.util.LinkedList;

import com.google.gson.annotations.SerializedName;

public class BloquePreguntas {
	
	@SerializedName("pregunta")
	private LinkedList<Pregunta> preguntas;
	
	public BloquePreguntas() {
		
	}

	public LinkedList<Pregunta> getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(LinkedList<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}
}
