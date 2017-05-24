package es.ucm.fdi.edd.core.umlgraph;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.umlgraph.doclet.OptionProvider;
import org.umlgraph.doclet.UmlGraph;

import com.sun.javadoc.Doc;
import com.sun.javadoc.RootDoc;

public class GenerateUMLGraph {
	
	/** The file PIC extension. */
    private static final String PIC = "pic";
    /** The application command. */
	private static final String PIC2PLOT = "pic2plot";
	/** Buffer length to read/write files. */
    private static final int BUFFER_LENGTH = 1024*64;
	/** The path */
    private static final String PATH = "./umlgraph";

	public static void main(String[] args) {
		GenerateUMLGraph g = new GenerateUMLGraph();
//		g.execute("sequenceTest.pic", "png");
		g.execute("LongSample.pic", "png");
		g.execute("LongSample.pic", "gif");
//		g.execute("LongSample.pic", "fig");
//		g.execute("LongSample.pic", "svg");
		
		//buildGraph();
	}

	private static void buildGraph() {
		try {
			RootDoc rootDoc = null;
			OptionProvider optionProvider = null;
			Doc doc = null;
			
			UmlGraph.buildGraph(rootDoc, optionProvider, doc);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void execute(String filename, String format) {
		try {
			// Compute the filename without extension and expected format.
			int idx = filename.lastIndexOf('.') + 1;
			String filenameWithoutExtension = filename.substring(0, idx);
//			String format = filename.substring(idx);
		      
			// Execute the pic2plot program to convert the PIC file to another format.
//			String command = PIC2PLOT + " -T" + format + " --bitmap-size 1024x1024 " + filename + " >" + filenameWithoutExtension + format;
//			String command = PIC2PLOT + " -T" + format + " --no-centering --bitmap-size 10240x10240+0+0 --font-size 0.00175 --page-size a4,xoffset=-5mm,yoffset=-5mm " + filename;
//			String command = PIC2PLOT + " -T" + format + " -n --bitmap-size 10240x10240 --font-size 0.00175 " + filename;
			String command = PIC2PLOT + " -T" + format + " --bitmap-size 16384x16384+0+0 -O --bg-color #c0c0c0 --font-size 0.00175 --page-size a4,xoffset=0cm,yoffset=0cm " + filename;
						
			System.out.println(command);
			String[] envp = new String[0];
			File dir = new File(PATH);
			Process pic2plotProcess = Runtime.getRuntime().exec(command, envp, dir);

			// Copy the data produced by the pic2plot process to the requested file.
			FileOutputStream fos = new FileOutputStream(PATH + File.separator + filenameWithoutExtension + format);
			copyStream(pic2plotProcess.getInputStream(), fos);
			fos.close();

			copyStream(pic2plotProcess.getErrorStream(), System.err);

			// Wait for end of the pic2plot process.
			pic2plotProcess.waitFor();

			// Delete the temp generated PIC file.
//			File picFile = new File(filenameWithoutExtension + PIC);
//			if (!picFile.delete()) {
//				throw new Error("Could not delete file '" + filenameWithoutExtension + PIC + '\'');
//			}
		} catch (Exception exc) {
			throw new Error(exc);
		}
	}

	/**
     * Copy an input stream to an output stream.
     */
	private void copyStream(InputStream is, OutputStream os) throws IOException {
		byte[] buffer = new byte[BUFFER_LENGTH];
		int count;
		while ((count = is.read(buffer, 0, buffer.length)) != -1) {
			os.write(buffer, 0, count);
		}
	}
}