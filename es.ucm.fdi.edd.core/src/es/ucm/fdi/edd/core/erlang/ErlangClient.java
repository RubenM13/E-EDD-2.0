package es.ucm.fdi.edd.core.erlang;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.ericsson.otp.erlang.OtpErlangAtom;
import com.ericsson.otp.erlang.OtpErlangInt;
import com.ericsson.otp.erlang.OtpErlangList;
import com.ericsson.otp.erlang.OtpErlangLong;
import com.ericsson.otp.erlang.OtpErlangObject;
import com.ericsson.otp.erlang.OtpErlangPid;
import com.ericsson.otp.erlang.OtpErlangString;
import com.ericsson.otp.erlang.OtpErlangTuple;
import com.ericsson.otp.erlang.OtpException;
import com.ericsson.otp.erlang.OtpMbox;
import com.ericsson.otp.erlang.OtpNode;

import es.ucm.fdi.edd.core.erlang.concurrent.converter.ErlangInfoConverter;
import es.ucm.fdi.edd.core.erlang.concurrent.converter.ErlangTreeConverter;
import es.ucm.fdi.edd.core.erlang.concurrent.model.ErlangAnswer;
import es.ucm.fdi.edd.core.erlang.concurrent.model.ErlangMessage;
import es.ucm.fdi.edd.core.erlang.concurrent.model.ErlangQuestion;
import es.ucm.fdi.edd.core.erlang.concurrent.model.ErlangThread;
import es.ucm.fdi.edd.core.erlang.concurrent.model.ErlangTreeInfo;
import es.ucm.fdi.edd.core.erlang.concurrent.model.ErlangTreeNode;
import es.ucm.fdi.edd.core.erlang.connection.ErlConnectionManager;
import es.ucm.fdi.edd.core.erlang.model.DebugTree;
import es.ucm.fdi.edd.core.erlang.model.EddModel;
import es.ucm.fdi.edd.core.erlang.model.EddState;
import es.ucm.fdi.edd.core.erlang.model.ZoomDebugTree;

/**
 * Para probar la comunicación erlang<->java, debes ejecutar la aplicación java y luego abrir una consola erlang para el paso de mensajes.  
 * 
 * Ejemplo:
 * 		cmd> erl -sname console -setcookie erlide
 * 
 * (console@localhost)1> {edd, eddjava@localhost} ! {buggy_call, self()}.
 * (console@localhost)2> {edd, eddjava@localhost} ! {question, 16, none}.
 */
public class ErlangClient implements Runnable, AutoCloseable, Observer {
	
//	private static final Logger log = Logger.getLogger(ErlangClient.class.getName());

	public static final int UNKNOWN_CURRENT_QUESTION_INDEX = -100;

	/** The Erlang/OTP mailbox. */
	private static final String MAILBOX = "edd";

	private static final int recTime = 2000;

	private String buggyCall;
	private String location;
	private OtpNode node;
	private OtpMbox mailbox;

	private OtpErlangPid pidServer;
	private OtpErlangObject erlState;

	private EddModel eddModel;
	private boolean firstTime;

	private volatile boolean stopFlag = false;

	private final CountDownLatch startSignal;
	private final CountDownLatch doneSignal;

	private boolean concurrent = false;
	private List<ErlangThread> threads;
	private List<ErlangMessage> messages;
	private ErlangTreeInfo tree;
	private boolean linked = false;

	/**
	 * Starts the erlang server client. 
	 * 
	 * @param doneSignal 
	 * 			done signal
	 * @param buggyCall
	 * 			a buggy call to debug.
	 * @param location
	 * 			the location of the erlang source file to debug.
	 * @param node
	 * 			the Erlang/OTP node.
	 * 
	 */
	public ErlangClient(CountDownLatch startSignal, CountDownLatch doneSignal, String buggyCall, String location, OtpNode node) {
		ErlConnectionManager.getInstance().addObserver(this); 
		
		this.startSignal = startSignal;
		this.doneSignal = doneSignal;
		this.buggyCall = buggyCall;
		this.location = location;
		this.node = node;
		this.mailbox = node.createMbox(MAILBOX);
		
		eddModel = new EddModel();
		firstTime = true;
	}

	public ErlangClient(CountDownLatch startSignal, CountDownLatch doneSignal, String buggyCall, String location, OtpNode node, boolean concurrent) {
		this(startSignal, doneSignal, buggyCall, location, node);
		this.concurrent = concurrent;
	}

	/**
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		System.out.println("Erlang EDD server running...");
		while (!stopFlag) {
			try {
				if (ErlConnectionManager.getInstance().isServerConnected()) {
					OtpErlangObject message = mailbox.receive(recTime);
					if (message != null) {
						System.out.println("<-- RECEIVED message: " + message);
					}
					if (message instanceof OtpErlangTuple) {
						OtpErlangTuple tuple = (OtpErlangTuple) message;
						int arity = tuple.arity();
						if (tuple.elementAt(0) instanceof OtpErlangAtom) {
							OtpErlangAtom command = (OtpErlangAtom) tuple.elementAt(0);
							switch (arity) {
								case 2:
									processMessage(command, tuple.elementAt(1));
									break;
								case 3:
									processMessage(command, tuple.elementAt(1), tuple.elementAt(2));
									break;
								case 4:
									processMessage(command, tuple.elementAt(1), tuple.elementAt(2), tuple.elementAt(3));
									break;
									
								default:
									break;
							}
						}
					}
				}
				TimeUnit.SECONDS.sleep(0);
			} catch (OtpException e) {
				System.out.println("Se ha producido un error: "	+ e.getMessage());			
				System.out.println("El proceso 'ErlangServer' ha finalizado");
				e.printStackTrace();
				mailbox.close();
			} catch (InterruptedException e) {
				System.out.println("Interrupted, closing...");
				mailbox.close();
		    }
		}
		node.close();
		mailbox.close();
		ErlConnectionManager.getInstance().clientDisconnect();
		System.out.println("\n\t--> Client closed! *******************************************************");
	}

	/**
	 * Process any received message.
	 * 
	 * @param command
	 * 			The command to execute.
	 * @param args
	 * 			The arguments list.
	 * 
	 * @throws OtpException
	 */
	private synchronized void processMessage(OtpErlangAtom command, OtpErlangObject... args) throws OtpException {
		String key = command.atomValue();
		int size = args.length;
		switch(key) {
			case "ready": {
				proccessReady(command, args[0]);
	    		System.out.println("\n\t--> >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + Thread.currentThread().getName() + " is Up\n");
	    		ErlConnectionManager.getInstance().clientConnect();
	    		doneSignal.countDown(); //reduce count of CountDownLatch by 1
				break;
			}
				
			case "dbg_tree":
				processDebugTree(command, args[0]);
				break;
			
			case "question":
				if (size == 2) {
					processQuestion(command, args[0], args[1]);
				}
				else if (size == 3) {
					processZoomQuestion(command, args[0], args[1], args[2]);
				} else if (size == 1 && args[0] instanceof OtpErlangLong) {
					eddModel.setCurrentQuestionIndex((int) ((OtpErlangLong) args[0]).longValue());
				}
				break;
			
			case "buggy_node":
				if (args.length == 2) {
					processBuggyNode(command, args[0], args[1]);
				} else {
					processBuggyNode(command, args[0]);
				}
				break;
				
			case "aborted":
				processAborted(command);
				break;
				
			case "error":
				processError(command, args[0]);
				break;
			case "pids_info":
				if (args[0] instanceof OtpErlangList) {
					this.threads = ErlangInfoConverter.convertThread((OtpErlangList) args[0]);
				}
				break;
			case "communcations":
				if (args[0] instanceof OtpErlangList) {
					this.messages = ErlangInfoConverter.convertMessage((OtpErlangList) args[0]);
				}
				break;
			case "tree":
				if (args[0] instanceof OtpErlangTuple) {
					this.tree = ErlangTreeConverter.convertToTree((OtpErlangTuple) args[0]);
					this.eddModel.setDebugTree(this.tree);
				}
				break;
			case "dict_traces":
				if (args[0] instanceof OtpErlangList) {
					ErlangInfoConverter.link((OtpErlangList) args[0], this.threads, this.messages, this.tree.getVertexesMap());
					this.linked = true;
				}
				break;
			default:
				OtpErlangTuple response = new OtpErlangTuple(command);
				errorResponse(mailbox.self(), response);
		}
	}
	
	/**
	 * {command, pid}
	 * {ready, <edderlang@localhost>}
	 * 
	 * @param command
	 * @param otpErlangObject
	 */
	private void proccessReady(OtpErlangAtom command, OtpErlangObject otpErlangObject) {
		if (otpErlangObject instanceof OtpErlangPid) {
			pidServer = (OtpErlangPid) otpErlangObject;
			if (!this.concurrent) {
				sendBuggyCall();
			} else {
				this.sendSecuenceCall(this.buggyCall, this.location);
			}
		}
		else {
			System.out.println("The 'ready' tuple is malformmed...");
		}
	}

	/**
	 * Send reply with buggy call and its location
	 */
	private void sendBuggyCall() {
		OtpErlangObject[] reply = new OtpErlangObject[4];
		reply[0] = new OtpErlangAtom("buggy_call");
		reply[1] = new OtpErlangString(buggyCall);
		reply[2] = new OtpErlangString(location);
		if (firstTime) {
			erlState = new OtpErlangAtom("none"); 
			firstTime = false;
		}
		reply[3] = erlState;

		try {
			startSignal.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		sendMessage(pidServer, reply);
	}

	/**
	 * {buggy_con_call, "merge_con:mergesort([o,h,i,o,i,s,t,h,e,b,e,s,t], fun merge_con:comp/2)", DIRECTORY, 500, none}.
	 */
	public void sendSecuenceCall(String buggyCall, String location) {
		OtpErlangObject[] reply = new OtpErlangObject[5];
		reply[0] = new OtpErlangAtom("buggy_con_call");
		reply[1] = new OtpErlangString(buggyCall);
		reply[2] = new OtpErlangString(location);
		reply[3] = new OtpErlangInt(4000);
		reply[4] = new OtpErlangAtom("none");
		try {
			startSignal.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		sendMessage(pidServer, reply);
	}

	/**
	 * {command, json}
	 * {dbg_tree, "{"vertices..."}"}
	 * 
	 * @param command
	 * @param otpErlangObject
	 */
	private void processDebugTree(OtpErlangAtom command, OtpErlangObject otpErlangObject) {
		if (otpErlangObject instanceof OtpErlangTuple) {
			OtpErlangTuple dbgTreeTuple = (OtpErlangTuple) otpErlangObject;
			if (dbgTreeTuple.arity() == 2) {
				OtpErlangTuple vertextesTuple = (OtpErlangTuple) dbgTreeTuple.elementAt(0);
				if (vertextesTuple.arity() == 2) {
					OtpErlangList vertexesList = (OtpErlangList) vertextesTuple.elementAt(1);
					// Diferenciar el tipo de depuración por el primer elemento de la lista 
					OtpErlangObject firstElement = vertexesList.elementAt(0);
					if (firstElement instanceof OtpErlangTuple) {
						OtpErlangTuple vertexTuple = (OtpErlangTuple) firstElement;
						if (vertexTuple.arity() == 4) {
							// Normal debugging
							eddModel.setDebugTree(new DebugTree(dbgTreeTuple));
						}
						else if (vertexTuple.arity() == 2) {
							// Zoom debugging
							eddModel.setZoomDebugTree(new ZoomDebugTree(dbgTreeTuple));
						}
						else {
							System.err.println("The 'vertex' arity is malformmed...");
						}
					}
				}
			}
			else {
				System.err.println("The 'dbg_tree' tuple is malformmed...");
			}			
		}
		else {
			System.err.println("The 'dbg_tree' tuple is malformmed...");
		}
	}
	
	/**
	 * {command, questionIndex, {tuple}}
	 * {question,53,{...}}
	 * 
	 * @param from
	 * @param question
	 * @param state
	 */
	private void processQuestion(OtpErlangAtom command, OtpErlangObject arg1, OtpErlangObject arg2) {
		if (arg1 instanceof OtpErlangLong && arg2 instanceof OtpErlangTuple) {
			OtpErlangLong qIndex = (OtpErlangLong) arg1;
			OtpErlangTuple stateTuple = (OtpErlangTuple) arg2;
			if (stateTuple.arity() == 4) {
				int index = (int) qIndex.longValue();
				eddModel.setCurrentQuestionIndex(index);
				eddModel.setCurrentZoomQuestionIndex(index);
				if (!concurrent) {
					// Proceso de debug secuencial
					eddModel.setState(new EddState(stateTuple));
				}
				System.out.println("\tCurrent Question:" + qIndex.longValue());
				// Avanzar en la búsqueda del buggy_node...
				//sendAnswer("n");
			} else {
				System.err.println("The tuple is malformmed...");
			}
		} else {
			System.err.println("The 'question' tuple is malformmed...");
		}
	}
	
	/**
	 * @param command
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	private void processZoomQuestion(OtpErlangAtom command, OtpErlangObject arg1, OtpErlangObject arg2, OtpErlangObject arg3) {
		if (arg1 instanceof OtpErlangLong && arg2 instanceof OtpErlangList && arg3 instanceof OtpErlangTuple) {
			OtpErlangLong qIndex = (OtpErlangLong) arg1;
			OtpErlangList zoomAnswers = (OtpErlangList) arg2;
			OtpErlangTuple stateTuple = (OtpErlangTuple) arg3;
			
			LinkedList<String> answersList = new LinkedList<String>();
			for (OtpErlangObject otpErlangObject : zoomAnswers) {
				if (otpErlangObject instanceof OtpErlangAtom) {
					OtpErlangAtom atom = (OtpErlangAtom) otpErlangObject;
					answersList.add(atom.atomValue());
				}
				else {
					System.err.println("The 'answer' is malformmed, it will be ignored...");	
				}
			}
			eddModel.setAnswerList(answersList);
			
			if (stateTuple.arity() == 4) {
				// Clear the buggy_node
				eddModel.setBuggyNodeIndex(-1);
				eddModel.setBuggyErrorCall(null);
				
				eddModel.setCurrentQuestionIndex((int) qIndex.longValue());
				eddModel.setCurrentZoomQuestionIndex((int) qIndex.longValue());
				eddModel.setState(new EddState(stateTuple));
				System.out.println("\tCurrent Zoom Question:" + qIndex.longValue() + " --> " + Arrays.toString(zoomAnswers.elements()));
			}
			else {
				System.err.println("The tuple is malformmed...");
			}
		}
		else if (arg1 instanceof OtpErlangString && arg2 instanceof OtpErlangList && arg3 instanceof OtpErlangTuple) {
			OtpErlangString question = (OtpErlangString) arg1;
			OtpErlangList zoomAnswers = (OtpErlangList) arg2;
			OtpErlangTuple stateTuple = (OtpErlangTuple) arg3;
			
			String currentZoomQuestion = question.stringValue();
			
			LinkedList<String> answersList = new LinkedList<String>();
			for (OtpErlangObject otpErlangObject : zoomAnswers) {
				if (otpErlangObject instanceof OtpErlangAtom) {
					OtpErlangAtom atom = (OtpErlangAtom) otpErlangObject;
					answersList.add(atom.atomValue());
				}
				else {
					System.err.println("The 'answer' is malformmed, it will be ignored...");	
				}
			}
			eddModel.setAnswerList(answersList);
			
			if (stateTuple.arity() == 4) {
				// Clear the buggy_node
				eddModel.setBuggyNodeIndex(-1);
				eddModel.setBuggyErrorCall(null);
				
				eddModel.setCurrentQuestionIndex(UNKNOWN_CURRENT_QUESTION_INDEX);
				eddModel.setCurrentZoomQuestionIndex(UNKNOWN_CURRENT_QUESTION_INDEX);
				eddModel.setCurrentZoomQuestion(currentZoomQuestion);
				eddModel.setState(new EddState(stateTuple));
				System.out.println("\tCurrent Zoom Question:" + currentZoomQuestion + " --> " + Arrays.toString(zoomAnswers.elements()));
			}
			else {
				System.err.println("The tuple is malformmed...");
			}
		}
		else {
			System.err.println("The 'question' tuple is malformmed...");
		}
	}

	/**
	 * Send reply with answer
	 * 
	 * @param response
	 * 			the response
	 */
	private void sendAnswer(String response) {
		OtpErlangObject[]  reply = new OtpErlangObject[2];
		reply[0] = new OtpErlangAtom("answer");
		try {
			reply[1] = new OtpErlangInt(Integer.valueOf(response));
		} catch (NumberFormatException e) {
			reply[1] = new OtpErlangAtom(response);
		}
		if (eddModel.getBuggyNodeIndex() == null || eddModel.getBuggyNodeIndex() == -1) {
			sendMessage(pidServer, reply);
			if (response.equals("a")) {
				System.out.println("Abortar...");
				try {
					// FIXME ¿Debería cerrar el cliente...?
					close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			System.err.println("Discard: " + response);
		}
	}
	
	/**
	 * {command, number}
	 * {buggy_node, 16}
	 * 
	 * @param command
	 * @param otpErlangObject
	 */
	private void processBuggyNode(OtpErlangAtom command, OtpErlangObject arg1) {
		if (arg1 instanceof OtpErlangLong) {
			OtpErlangLong buggyNode = (OtpErlangLong) arg1;
			int buggyNodeIndex = (int) buggyNode.longValue();
			System.out.println(String.format("Buggy node: %s Error call: %s", buggyNodeIndex, ""));
			eddModel.setBuggyNodeIndex(buggyNodeIndex);
			eddModel.setBuggyErrorCall("");
		}
		else {
			System.err.println("The 'buggy_node' tuple is malformmed...");
		}
	}
	
	/**
	 * {command, number}
	 * {buggy_node, 16}
	 * 
	 * @param command
	 * @param otpErlangObject
	 */
	private void processBuggyNode(OtpErlangAtom command, OtpErlangObject arg1, OtpErlangObject arg2) {
		if (arg1 instanceof OtpErlangLong && arg2 instanceof OtpErlangString) {
			OtpErlangLong buggyNode = (OtpErlangLong) arg1;
			OtpErlangString buggyCall = (OtpErlangString) arg2;
			int buggyNodeIndex = (int) buggyNode.longValue();
			String buggyErrorCall = buggyCall.stringValue();
			System.out.println(String.format("Buggy node: %s Error call: %s", buggyNodeIndex, buggyErrorCall));
			eddModel.setBuggyNodeIndex(buggyNodeIndex);
			eddModel.setBuggyErrorCall(buggyErrorCall);
		}
		else {
			System.err.println("The 'buggy_node' tuple is malformmed...");
		}
	}
	
	/**
	 * @param buggyErrorCall
	 */
	private void sendZoomDebug(String buggyErrorCall) {
		OtpErlangObject[]  reply = new OtpErlangObject[4];
		reply[0] = new OtpErlangAtom("zoom_dbg");
		reply[1] = new OtpErlangString(buggyErrorCall);
		reply[2] = new OtpErlangString(location);
		reply[3] = new OtpErlangAtom("none");
		sendMessage(pidServer, reply);
	}
	
	/**
	 * Finish the communication with the server.
	 * 
	 * @throws OtpException
	 */
	private void processAborted(OtpErlangAtom command) throws OtpException {
		System.out.println("Aborted...");
	}
	
	/**
	 * @throws OtpException
	 */
	private void processError(OtpErlangAtom command, OtpErlangObject arg1) throws OtpException {
		if (arg1 instanceof OtpErlangTuple) {
			OtpErlangTuple errorTuple = (OtpErlangTuple)arg1;
			if (errorTuple.arity() == 2) {
				OtpErlangAtom errorMsg1 = (OtpErlangAtom) errorTuple.elementAt(0);
				OtpErlangObject errorObj = errorTuple.elementAt(1);
				if (errorObj instanceof OtpErlangAtom) {
					OtpErlangAtom errorMsg2 = (OtpErlangAtom) errorObj;
					System.err.println(String.format("Error node: %s, %s", errorMsg1.atomValue(), errorMsg2.atomValue()));
				} else if (errorObj instanceof OtpErlangTuple) {
					OtpErlangTuple errorMsgTuple = (OtpErlangTuple) errorObj;
					OtpErlangObject[] elements = errorMsgTuple.elements();
					System.err.println(String.format("Error node: %s, %s", errorMsg1.atomValue(), Arrays.toString(elements)));
					
				} else {
					System.err.println(String.format("Error node: %s, %s", errorMsg1.atomValue(), errorObj.toString()));
				}
				
				// FIXME ¿Debería cerrar el cliente...?
				try {
					close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else {
				System.err.println("Unknown 'error' arity...");
			}
		}
		else {
			System.err.println("The 'error' tuple is malformmed...");
		}
	}
	
	/**
	 * Sends a error message given the source node.
	 * 
	 * Sends:
	 * 		<code>{error, message}</code>
	 * 
	 * @param pidSender
	 * 			The source node.
	 * @param message
	 * 			The error message.
	 */
	private void errorResponse(OtpErlangPid pidSender, OtpErlangTuple message) {
		System.err.println("An unknown error has occurred trying to execute the command: " + message.toString());
		OtpErlangAtom errorAtom = new OtpErlangAtom("error");
		OtpErlangObject[] response = new OtpErlangObject[] {errorAtom, message};
//		sendMessage(pidSender, response);
	}

	/**
	 * Sends a message given the source node.
	 * 
	 * @param pidSender
	 * 			The source node.
	 * @param message
	 * 			The message.
	 */
	private void sendMessage(OtpErlangPid pidSender, OtpErlangObject[] message) {
		OtpErlangTuple tuple = new OtpErlangTuple(message);
		System.out.println("--> SEND message [" + pidSender + "]: " + tuple.toString());
		mailbox.send(pidSender, tuple);
	}
	
	//--------------------------------------------------------------------------------------------------------------------------
	
	public String getBuggyCall() {
		return buggyCall;
	}

	public void setBuggyCall(String buggyCall) {
		this.buggyCall = buggyCall;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public void restart() {
		firstTime = true;
		sendBuggyCall();
	}

	public EddModel getEddModel() {
		return eddModel;
	}
	
	public void setAnswer(String reply, CountDownLatch countDownLatch) {
		if (this.concurrent) {
			this.setAnswerConcurent(reply, countDownLatch);
		} else {
			this.setGenericAnswer(reply, countDownLatch);
		}
	}

	private void setAnswerConcurent(String reply, CountDownLatch countDownLatch) {
		if (reply.equalsIgnoreCase("abort")) {
			this.setGenericAnswer(reply, countDownLatch);
			try {
				close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			if (this.isNumber(reply)) {
				this.fromSeqDiag(reply);
			} else {
				if (this.tree != null) {
					ErlangTreeNode node = this.tree.getVertexesMap().get(this.eddModel.getCurrentQuestionIndex());
					if (node != null) {
						ErlangQuestion question = node.getQuestion();
						if (question != null) {
							if (reply.equalsIgnoreCase("undo")) {
								if (question.undoAnswer()) {
									this.setGenericAnswer(reply, countDownLatch);
								}
							}
							int index = question.getActualAnswerTextList().indexOf(reply);
							if (index != -1) {
								ErlangAnswer answer = question.getAnswers().get(index);
								question.setAnswer(answer);
								if (!answer.getChosen().equals(ErlangAnswer.SimpleChosen.QUESTION)) {
									this.setGenericAnswer(answer.getChosen().toString().toLowerCase(), countDownLatch);
								}
							} else {
								System.err.println("Error processing the answer");
							}
						} else {
							System.err.println("Error processing the answer");
						}
					} else {
						System.err.println("Error processing the answer");
					}
				}
			}
		}
	}

	private boolean isNumber(String value) {
		try {
			Integer.valueOf(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	private void fromSeqDiag(String response) {
		OtpErlangObject[] reply = new OtpErlangObject[2];
//		reply[0] = new OtpErlangAtom("from_seq_diag");
		reply[0] = new OtpErlangAtom("goto");
		try {
			reply[1] = new OtpErlangInt(Integer.valueOf(response));
		} catch (NumberFormatException e) {
			reply[1] = new OtpErlangAtom(response);
		}
		OtpErlangTuple tuple = new OtpErlangTuple(reply);
		reply = new OtpErlangObject[2];
		reply[0] = new OtpErlangAtom("answer");
		reply[1] = tuple;
		if (eddModel.getBuggyNodeIndex() == null || eddModel.getBuggyNodeIndex() == -1) {
			sendMessage(pidServer, reply);
		} else {
			System.err.println("Discard: " + response);
		}
	}

	private void setGenericAnswer(String reply, CountDownLatch countDownLatch) {
		sendAnswer(reply);
		countDownLatch.countDown(); //reduce count of CountDownLatch by 1
	}

	public void startZoomDebug(String buggyErrorCall) {
		sendZoomDebug(buggyErrorCall);
	}

	public void stopClient() throws Exception {
		close();
	}
	
	@Override
	public void close() throws Exception {
		stopFlag = true;
	}

	@Override
	public void update(Observable o, Object arg) {
		ErlConnectionManager connectionManager = (ErlConnectionManager) o;
//		System.err.println(String.format("\tClient: %s, Server: %s", connectionManager.isClientConnected(), connectionManager.isServerConnected()));
	}

	public synchronized List<ErlangThread> getThreads() {
		List<ErlangThread> aux = null;
		if (linked) {
			aux = this.threads;
			this.threads = null;
			this.linked = this.messages != null;
		}
		return aux;
	}

	public synchronized List<ErlangMessage> getMessages() {
		List<ErlangMessage> aux = null;
		if (linked) {
			aux = this.messages;
			this.messages = null;
			this.linked = this.threads != null;
		}
		return aux;
	}

	public ErlangTreeInfo getTree() {
		ErlangTreeInfo aux = this.tree;
		this.tree = null;
		return aux;
	}

}