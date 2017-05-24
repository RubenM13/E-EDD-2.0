package es.ucm.fdi.edd.core.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
	
	/**
	 * @param pFilename
	 * @param data
	 * @throws IOException
	 */
	public static void writeToFile(String pFilename, String data) throws IOException {
		writeToFile(pFilename, new StringBuffer(data));
	}
	
	/**
     * @param pFilename
     * @param pData
     * @throws IOException
     */
    public static void writeToFile(String pFilename, StringBuffer pData) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter(pFilename));
        out.write(pData.toString());
        out.flush();
        out.close();
	}

	/**
	 * @param pFilename
	 * @return
	 * @throws IOException
	 */
	public static StringBuffer readFromFile(String pFilename) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(pFilename));
		StringBuffer data = new StringBuffer();
		int c = 0;
		while ((c = in.read()) != -1) {
			data.append((char) c);
		}
		in.close();
		return data;
	}
}
