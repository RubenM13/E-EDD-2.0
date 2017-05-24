package es.ucm.fdi.edd.core.erlang.jinterface;

public class KillProcessDemo {
	
	public static void main(String[] args) {
		try {
			Runtime.getRuntime().exec("taskkill /F /PID " +  7296);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
