package es.ucm.fdi.edd.core.graphviz;

import java.util.ArrayList;

public class TreeParent extends TreeObject {

	private ArrayList<TreeObject> children;

	public TreeParent(String name) {
		super(name);
		children = new ArrayList<TreeObject>();
	}

	public void addChild(TreeObject child) {
		children.add(child);
		child.setParent(this);
	}

	public void removeChild(TreeObject child) {
		children.remove(child);
		child.setParent(null);
	}

	public TreeObject[] getChildren() {
		return (TreeObject[]) children.toArray(new TreeObject[children.size()]);
	}

	public boolean hasChildren() {
		return children.size() > 0;
	}

	public static void walk(TreeParent node) {
		TreeObject[] list = node.getChildren();
		if (list == null)
			return;

		for (TreeObject to : list) {
			if (to instanceof TreeParent) {
				TreeParent tp = (TreeParent) to;
				walk(tp);
				System.out.println("Parent:" + to.toString());
			} else {
				System.out.println("Leaf:" + to.toString());
			}
		}
	}
}