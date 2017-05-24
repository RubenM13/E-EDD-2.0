package es.ucm.fdi.edd.ui.views.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileSearch {

	private String fileNameToSearch;
	
	private List<String> result = new ArrayList<String>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FileSearch fileSearch = new FileSearch();
		// try different directory and filename :)
		fileSearch.searchDirectory(new File("D:/workspace/runtime-tests/EDDAckermann"), "ackermann.beam");
		int count = fileSearch.getResult().size();
		if (count == 0) {
			System.out.println("\nNo result found!");
		} else {
			System.out.println("\nFound " + count + " result!\n");
			for (String matched : fileSearch.getResult()) {
				System.out.println("Found : " + matched);
			}
		}
	}

	/**
	 * @param directory
	 * @param fileNameToSearch
	 */
	public void searchDirectory(File directory, String fileNameToSearch) {
		setFileNameToSearch(fileNameToSearch);
		if (directory.isDirectory()) {
			search(directory);
		} else {
			System.out.println(directory.getAbsoluteFile() + " is not a directory!");
		}
	}

	/**
	 * @param file
	 */
	private void search(File file) {
		if (file.isDirectory()) {
			System.out.println("Searching directory ... " + file.getAbsoluteFile());
			// do you have permission to read this directory?
			if (file.canRead()) {
				for (File temp : file.listFiles()) {
					if (temp.isDirectory()) {
						search(temp);
					} else {
						if (getFileNameToSearch().equals(temp.getName().toLowerCase())) {
							result.add(temp.getAbsoluteFile().toString());
						}
					}
				}
			} else {
				System.out.println(file.getAbsoluteFile() + "Permission Denied");
			}
		}
	}
	
	/**
	 * @return
	 */
	public String getFileNameToSearch() {
		return fileNameToSearch;
	}

	/**
	 * @param fileNameToSearch
	 */
	public void setFileNameToSearch(String fileNameToSearch) {
		this.fileNameToSearch = fileNameToSearch;
	}

	/**
	 * @return
	 */
	public List<String> getResult() {
		return result;
	}	
}