package es.ucm.fdi.edd.core.graphviz;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Tree2Dot {
	
	public static void main(String[] args) {
		
		TreeParent rootTest = createTestTree();
		System.out.println("preOrden");
		preOrden(rootTest);
		System.out.println("inOrden");
		inOrden(rootTest);
		System.out.println("postOrden");
		postOrden(rootTest);
		
		TreeParent root = createTree();
		
		GraphViz gv = new GraphViz();
		gv.addln(gv.start_graph());
		
		tree2list(root, gv);
//		tree2dot(root, gv);
		
		gv.addln(gv.end_graph());
		System.out.println(gv.getDotSource());
		
		String type = "gif";
		File out = new File("files/output/images/sample." + type);
		gv.writeGraphToFile(gv.getGraph(gv.getDotSource(), type), out);
	}
	
	private static TreeParent createTree() {
		TreeObject to1 = new TreeObject("Leaf_1");
		TreeObject to2 = new TreeObject("Leaf_2");
		TreeObject to3 = new TreeObject("Leaf_3");
		TreeParent p1 = new TreeParent("Parent_1");
		p1.addChild(to1);
		p1.addChild(to2);
		p1.addChild(to3);
		
		TreeObject to4 = new TreeObject("Leaf_4");
		TreeParent p2 = new TreeParent("Parent_2");
		p2.addChild(to4);
		
		TreeParent root = new TreeParent("Root");
		root.addChild(p1);
		root.addChild(p2);
		
		return root;
	}
	
	private static TreeParent createTestTree() {
		TreeParent root = new TreeParent("2");
			TreeParent p7 = new TreeParent("7");
				TreeObject to2 = new TreeObject("2");
				TreeParent p6 = new TreeParent("6");
					TreeObject to5 = new TreeObject("5");
					TreeObject to11 = new TreeObject("11");
		
			TreeParent p5 = new TreeParent("5");
				TreeParent p9 = new TreeParent("9");
					TreeObject to4 = new TreeObject("4");
		
		root.addChild(p7);
			p7.addChild(to2);
			p7.addChild(p6);
				p6.addChild(to5);
				p6.addChild(to11);
			
		root.addChild(p5);
			p5.addChild(p9);
				p9.addChild(to4);
		
		return root;
	}
	
	//*** Preorden 2,7,2,6,5,11,5,9,4
	private static void preOrden(TreeObject node) {
		System.out.print(node.getName() + ", ");
		TreeObject aux = null;
		if (node instanceof TreeParent) {
			TreeParent parent = (TreeParent) node;
			TreeObject[] list = parent.getChildren();
			for (TreeObject to : list) {
				aux = to;
				preOrden(aux);
			}
		}
	}
	
	//*** Inorden 2,7,5,6,11,2,5,4,9
	private static void inOrden(TreeObject node) {
		
	}

	//*** Postorden 2,5,11,6,7,4,9,5,2
	private static void postOrden(TreeObject node) {
		TreeObject aux = null;
		if (node instanceof TreeParent) {
			TreeParent parent = (TreeParent) node;
			TreeObject[] list = parent.getChildren();
			for (TreeObject to : list) {
				aux = to;
				postOrden(aux);
			}
		}
		System.out.print(node.getName() + ", ");
	}

	
	private static void tree2list(TreeParent parent, GraphViz gv) {
		TreeObject[] list = parent.getChildren();
		if (list == null)
			return;

		for (TreeObject to : list) {
			if (to instanceof TreeParent) {
				TreeParent tp = (TreeParent) to;
				gv.addln(tp.getName() + " [label=\""+tp.getName()+"\",shape=\"box\", color=blue];");
				gv.addln(parent.getName() + " -> " + tp.getName() + " ;");
				tree2dot(tp, gv);
				gv.addln(parent.getName() + " [label=\""+parent.getName()+"\",shape=\"box\", color=blue];");
			} else {
				gv.addln(to.getName() + " [label=\""+to.getName()+"\", color=red];");
			}
		}
	}
	
	private static void tree2dot(TreeParent parent, GraphViz gv) {
		TreeObject[] list = parent.getChildren();
		if (list == null)
			return;

		for (TreeObject to : list) {
			if (to instanceof TreeParent) {
				TreeParent tp = (TreeParent) to;
				gv.addln(tp.getName() + " [label=\""+tp.getName()+"\",shape=\"box\", color=green];");
				tree2dot(tp, gv);
				gv.addln(parent.getName() + " -> " + tp.getName() + " [label=\"" + tp.getAttribute() + "\", color=blue];");
			} else {
				gv.addln(parent.getName() + " -> " + to.getName() + " [label=\"" + to.getAttribute() + "\", color=red];");
			}
		}
	}

	public void execute(TreeParent tree, String outputFile) {
		GraphViz gv = new GraphViz();
		gv.addln(gv.start_graph());
//		tree2list(tree, gv);
		tree2dot(tree, gv);
		gv.addln(gv.end_graph());
		
		String dotSource = gv.getDotSource();
		writeFile(new File(outputFile), dotSource, false);
		
		String type = "gif";
		File out = new File("files/output/images/sample." + type);
		gv.writeGraphToFile(gv.getGraph(dotSource, type), out);
	}
	
	public void writeFile(File file, String content, boolean append) {
		try {
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile(), append);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}