package es.ucm.fdi.edd.core.graphviz;

public class TreeObject {

	private String name;
	private String attribute;
	private TreeParent parent;

	public TreeObject(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public void setParent(TreeParent parent) {
		this.parent = parent;
	}

	public TreeParent getParent() {
		return parent;
	}

	public String toString() {
		return getName() + "{" + getAttribute() + "}";
	}
}