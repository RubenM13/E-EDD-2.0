package es.ucm.fdi.edd.core.erlang.jinterface;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ProcessBuildDemo {

	private static final String CMD = "CMD";
	/** @see C:\>cmd /? */
	private static final String C = "/C"; // '/C' --> Carries out the command specified by string and then terminates

	public static void main(String[] args) throws IOException {
		
		String path = "C:/Users/Ruben/Workspace/edd-master/ebin/";
		String command = "dir";
		String[] commands = { CMD, C, command };
		
		ProcessBuilder probuilder = new ProcessBuilder(commands);
		probuilder.directory(new File(path));

		Process process = probuilder.start();

		// Read out dir output
		InputStream is = process.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String line;
		System.out.printf("Output of running %s is:\n",	Arrays.toString(commands));
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}

		// Wait to get exit value
		try {
			int exitValue = process.waitFor();
			System.out.println("\n\nExit Value is " + exitValue);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}