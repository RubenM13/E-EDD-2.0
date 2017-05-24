package es.ucm.fdi.edd.ui;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.osgi.framework.Bundle;
import org.eclipse.core.runtime.Platform;

public class Prueba {

	private static final String SANDBOX = "C:/Users/Ruben/Workspace/e-edd-master/es.ucm.fdi.edd.sandbox";
	private static final String SAMPLE_FILE = "graphviz/Sample.dot";

	public static void main(String[] args) {
		Bundle bundle = Platform.getBundle(SANDBOX);
		URL fileURL = bundle.getEntry(SAMPLE_FILE);
		File file = null;
		try {
		    file = new File(FileLocator.resolve(fileURL).toURI());
		} catch (URISyntaxException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		System.out.println(file.getName());
	}

}
