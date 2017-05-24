package es.ucm.fdi.edd.core.erlang;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class IOThreadHandler extends Thread {

	private InputStream inputStream;
	private CountDownLatch doneSignal;
	private StringBuilder output = new StringBuilder();

	public IOThreadHandler(InputStream inputStream, CountDownLatch doneSignal) {
		setName("IOThreadHandler");
		this.inputStream = inputStream;
		this.doneSignal = doneSignal;
	}

	public void run() {
		Scanner br = null;
		try {
			br = new Scanner(new InputStreamReader(inputStream));
			String line = null;
			while (br.hasNextLine()) {
				line = br.nextLine();
				output.append(line + System.getProperty("line.separator"));
			}
		} finally {
			br.close();
			doneSignal.countDown(); //reduce count of CountDownLatch by 1
		}
	}

	public StringBuilder getOutput() {
		return output;
	}
}