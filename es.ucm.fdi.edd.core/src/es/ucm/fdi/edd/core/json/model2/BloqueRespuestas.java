package es.ucm.fdi.edd.core.json.model2;

import java.util.LinkedList;

import com.google.gson.annotations.SerializedName;

public class BloqueRespuestas {
	
	@SerializedName("respuesta")
	private LinkedList<Respuesta> respuestas;
	
	public BloqueRespuestas() {
		
	}

	public LinkedList<Respuesta> getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(LinkedList<Respuesta> respuestas) {
		this.respuestas = respuestas;
	}
	
	public Respuesta getRespuesta(int idRespuesta) {
		for (Respuesta element : respuestas) {
			if (element.getIdRespuesta() == idRespuesta) {
				return element;
			}
		}
		
		return null;
	}
}
