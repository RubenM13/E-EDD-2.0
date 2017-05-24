package es.ucm.fdi.edd.ui.views.utils;

import java.io.File;

/**
 * File walker
 */
public class Filewalker {

	/**
	 * 
	 * @param path
	 */
	public void walk(String path) {
		File root = new File(path);
		File[] list = root.listFiles();
		if (list == null)
			return;
		for (File file : list) {
			if (file.isDirectory()) {
				walk(file.getAbsolutePath());
				System.out.println("Dir: " + file.getAbsoluteFile());
			} else {
				System.out.println("File: " + file.getAbsoluteFile());
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Filewalker fw = new Filewalker();
		fw.walk("D:/workspace/runtime-tests/EDDAckermann");
	}
}