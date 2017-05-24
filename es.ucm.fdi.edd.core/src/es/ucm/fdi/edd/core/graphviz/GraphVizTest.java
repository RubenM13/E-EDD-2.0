package es.ucm.fdi.edd.core.graphviz;

import java.io.File;

public class GraphVizTest {

	public static void main(String[] args) {
		GraphVizTest p = new GraphVizTest();
		p.start1();
//		p.start2();
	}

	/**
	 * Construct a DOT graph in memory, convert it to image and store the image
	 * in the file system.
	 */
	private void start1() {
		GraphViz gv = new GraphViz();
		gv.addln(gv.start_graph());
		gv.addln("A -> B;");
		gv.addln("A -> C;");
		gv.addln(gv.end_graph());
		System.out.println(gv.getDotSource());

		String type = "gif";
		File out = new File("files/output/images/sample1." + type);
		gv.writeGraphToFile(gv.getGraph(gv.getDotSource(), type), out);
	}

	/**
	 * Read the DOT source from a file, convert to image and store the image in
	 * the file system.
	 */
	private void start2() {
		String input = "files/output/tree.dot";

		GraphViz gv = new GraphViz();
		gv.readSource(input);
		System.out.println(gv.getDotSource());

		String type = "gif";
		File out = new File("files/output/images/sample2." + type);
		gv.writeGraphToFile(gv.getGraph(gv.getDotSource(), type), out);
	}
}