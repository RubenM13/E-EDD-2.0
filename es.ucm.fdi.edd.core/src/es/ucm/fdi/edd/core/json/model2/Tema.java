package es.ucm.fdi.edd.core.json.model2;

import com.google.gson.annotations.SerializedName;

public class Tema {
	
	@SerializedName("-id")
	private Long id;
	@SerializedName("-name")
	private String name;
	@SerializedName("subTema")
	private SubTema subTemas;
	
	public Tema(Long id, String name) {
		this.id = id;
		this.name = name;
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

	public SubTema getSubTemas() {
		return subTemas;
	}

	public void setSubTemas(SubTema subTemas) {
		this.subTemas = subTemas;
	}
}
