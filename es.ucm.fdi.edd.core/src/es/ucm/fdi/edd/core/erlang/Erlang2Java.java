package es.ucm.fdi.edd.core.erlang;

import java.io.IOException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.ericsson.otp.erlang.OtpNode;

import es.ucm.fdi.edd.core.erlang.concurrent.model.ErlangMessage;
import es.ucm.fdi.edd.core.erlang.concurrent.model.ErlangThread;
import es.ucm.fdi.edd.core.erlang.connection.ErlConnectionManager;
import es.ucm.fdi.edd.core.erlang.model.EddModel;

/**
 * Erlang to Java communication. 
 */
public class Erlang2Java implements Observer {
	
//	private static final Logger log = Logger.getLogger(Erlang2Java.class.getName());

	public static final String NODE = "eddjava@localhost";
	public static final String COOKIE = "erlide";
	
	private static final String THREAD_CLIENT_NAME = "EDD-Client";
	private static final String THREAD_SERVER_NAME = "EDD-Server";
	
	private Thread clientThread;
	private Thread serverThread;
	
	private ErlangClient erlangClient;
	private ErlangServer erlangServer;
	private String eddInitialPath;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int params = args.length;
		switch (params) {
			case 0: {
				System.out.println("The argument list can not be empty.");
				break;
			}
				
			case 2: {
				String buggyCall = args[0];
				String location = args[1];
				Erlang2Java main = new Erlang2Java(ErlangServer.WORKING_DIRECTORY);
				main.initialize(buggyCall, location);
				
				try {
					TimeUnit.SECONDS.sleep(5);
					String buggyCall2 = "merge:mergesort([b,a], fun merge:comp/2)";
					String location2 = "C:/Users/Ruben/Workspace/runtime-tests/EDDSample/src/";
					main.restartDebugger(buggyCall2, location2);
					
					TimeUnit.SECONDS.sleep(5);
					main.stopServer();
					
					for (int i = 0; i < 2; i++) {
						System.out.println("=============== START [" + i + "]===============\n");
						TimeUnit.SECONDS.sleep(5);
						main = null;
						main = new Erlang2Java(ErlangServer.WORKING_DIRECTORY);
						main.initialize(buggyCall, location);
						TimeUnit.SECONDS.sleep(5);
						main.stopServer();	
						System.out.println("=============== END ===============\n");
					}
				} catch (Exception e) {
					e.printStackTrace();
				} 
				
				break;
			}

			default: {
				System.out.println("You must provide two argument: a buggy call and the location of the erlang source file to debug.");
				break;
			}
		}
	}
	
	/**
	 * Constructor 
	 * 
	 * @param eddPath
	 */
	public Erlang2Java(String eddPath) {
		ErlConnectionManager.getInstance().addObserver(this);
		eddInitialPath = eddPath;
	}
	
	/**
	 * Initialize the communication.
	 * 
	 * @param buggyCall
	 * 			a buggy call to debug.
	 * @param location
	 * 			the location of the erlang source file to debug.
	 */
	public void initialize(String buggyCall, String location) {
		this.initialize(buggyCall, location, false);
	}

	private void initialize(String buggyCall, String location, boolean sequence) {
		try {
			verifyConnection();
			
			OtpNode node = new OtpNode(NODE);
			node.setCookie(COOKIE);
			
			final CountDownLatch startSignal = new CountDownLatch(1);
			final CountDownLatch doneSignal = new CountDownLatch(2);
			
			erlangClient = new ErlangClient(startSignal, doneSignal, buggyCall, location, node, sequence);
			clientThread = new Thread(erlangClient, THREAD_CLIENT_NAME);
			
			erlangServer = new ErlangServer(startSignal, doneSignal, eddInitialPath);
			serverThread = new Thread(erlangServer, THREAD_SERVER_NAME);
			
			clientThread.start();
			serverThread.start();
					
			// main thread is waiting on CountDownLatch to finish
			doneSignal.await(); 
			System.out.println("\n\t--> >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> All services are up, Application is starting now\n");
			
//			erlClient.join();
//			erlServer.join();
				
		} catch (IOException e) {
			System.out.println("No se puede iniciar nodo. Has arrancado epmd?");
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Llama al proceso erlang del diagrama de secuencia, y espera hasta el número de turnos pasado por parámetro para obtener los datos
	 * @param buggyCall
	 * @param location
	 * @param threads
	 * @param messages
	 * @param countDown
	 * @throws Exception
	 */
	public void initConcurrent(String buggyCall, String location, List<ErlangThread> threads, List<ErlangMessage> messages, int countDown) throws Exception {
		if (this.erlangClient == null || !this.clientThread.isAlive()) {
			try {
				this.initialize(buggyCall, location, true);
				this.getSeqInfo(threads, messages, countDown);
			} catch(InterruptedException e) {
				e.printStackTrace();
				this.stopServer();
			}
			if (threads.isEmpty() || messages.isEmpty()) {
				this.stopServer();
			}
		} else {
			this.erlangClient.sendSecuenceCall(buggyCall, location);
			this.getSeqInfo(threads, messages, countDown);
		}
	}

	private void getSeqInfo(List<ErlangThread> threads, List<ErlangMessage> messages, int countDown) throws InterruptedException {
		for (int i = 0; i < countDown && (threads.isEmpty() || messages.isEmpty()); i++) {
			if (threads.isEmpty()) {
				List<ErlangThread> aux = this.erlangClient.getThreads();
				if (aux != null) {
					threads.addAll(aux);
				}
			}
			if (messages.isEmpty()) {
				List<ErlangMessage> aux = this.erlangClient.getMessages();
				if (aux != null) {
					messages.addAll(aux);
				}
			}
			TimeUnit.MILLISECONDS.sleep(100);
		}
	}

	private void verifyConnection() {
		if (ErlConnectionManager.getInstance().isServerConnected() || ErlConnectionManager.getInstance().isClientConnected()) {
			try {
				System.out.println("Verificar conexión...");
				stopServer();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void update(Observable o, Object arg) {
		try {
			ErlConnectionManager connectionManager = (ErlConnectionManager) o;
			boolean clientConnected = connectionManager.isClientConnected();
			boolean serverConnected = connectionManager.isServerConnected();
			if (connectionManager.areBothConnected()) {
//				log.info(String.format("\tClient: %s, Server: %s", clientConnected, serverConnected));
			} 
			else {
//				log.info(String.format("\tSOMEONE IS DISCONNECTED -> Client: %s, Server: %s", clientConnected, serverConnected));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Stops the server.
	 * 
	 * @throws Exception
	 */
	public void stopServer() throws Exception {
		erlangClient.stopClient();
		erlangServer.stopServer();
	}

	private void interruptWhenIsAlive() {
		if (clientThread.isAlive()) {
			System.out.println("KILL: Client");
			clientThread.interrupt();
		}
		if (serverThread.isAlive()) {
			serverThread.interrupt();
			System.out.println("KILL: Server");
		}
	}
	
	/**
	 * Starts the communication.
	 * 
	 * @param buggyCall
	 * 			a buggy call to debug.
	 * @param location
	 * 			the location of the erlang source file to debug.
	 */
	public void restartDebugger(String buggyCall, String location) {
		erlangClient.setBuggyCall(buggyCall);
		erlangClient.setLocation(location);
		erlangClient.restart();
	}
	
	public EddModel getEddModel() {
		return erlangClient.getEddModel();
	}
	
	public void sendAnswer(String reply) {
		erlangClient.setAnswer(reply, new CountDownLatch(1));
	}
	
	public void startZoomDebug(String buggyErrorCall) {
		erlangClient.startZoomDebug(buggyErrorCall);
	}

}