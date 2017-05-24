package es.ucm.fdi.edd.core.erlang.jinterface;

import java.io.IOException;

import com.ericsson.otp.erlang.OtpErlangAtom;
import com.ericsson.otp.erlang.OtpErlangDecodeException;
import com.ericsson.otp.erlang.OtpErlangExit;
import com.ericsson.otp.erlang.OtpErlangLong;
import com.ericsson.otp.erlang.OtpErlangObject;
import com.ericsson.otp.erlang.OtpErlangPid;
import com.ericsson.otp.erlang.OtpErlangRangeException;
import com.ericsson.otp.erlang.OtpErlangString;
import com.ericsson.otp.erlang.OtpErlangTuple;
import com.ericsson.otp.erlang.OtpMbox;
import com.ericsson.otp.erlang.OtpNode;

public class EDDJInterface {
	
	public static void main(String[] args) throws Exception {
		int params = args.length;
		switch (params) {
			case 0:
				System.out.println("The argument list can not be empty.");
				break;
				
			case 2:
				String buggyCall = args[0];
				String location = args[1];
				EDDJInterface edd = new EDDJInterface();
				edd.execute(buggyCall, location);
				break;

			default:
				System.out.println("You must provide two argument: a buggy call and the location of the erlang source file to debug.");
				break;
		}
	}

	private void execute(String buggyCall, String location) throws IOException,
			OtpErlangExit, OtpErlangDecodeException, OtpErlangRangeException {
		ErlangServer erlServer = new ErlangServer(location);
		erlServer.start();
		System.out.println("Erlang EDD server running...");
		
		OtpNode myNode = new OtpNode("eddjava@localhost");
		OtpMbox myMbox = myNode.createMbox("edd");

		// Receive PID from erlang server
		OtpErlangObject myObject = myMbox.receive();
		System.out.println("<<<<<<<<<< Received message: " + myObject);
		OtpErlangTuple myMsg = (OtpErlangTuple) myObject;
		OtpErlangAtom command = (OtpErlangAtom) myMsg.elementAt(0);
		OtpErlangPid from = (OtpErlangPid) myMsg.elementAt(1);
		// Should be 'ready'
//		System.out.println("Command received: " + command.toString());

		// Send reply with buggy call and its location
		OtpErlangObject[] reply = new OtpErlangObject[3];
		reply[0] = new OtpErlangAtom("buggy_call");
		reply[1] = new OtpErlangString(buggyCall);
		reply[2] = new OtpErlangString(location);
		OtpErlangTuple myTuple = new OtpErlangTuple(reply);
		System.out.println(">>>>>>>>>> Send message: " + from + " :: " + myTuple);
		myMbox.send(from, myTuple);

		// Receive debugging tree
		myObject = myMbox.receive();
		System.out.println("<<<<<<<<<< Received message: " + myObject);
		myMsg = (OtpErlangTuple) myObject;
		command = (OtpErlangAtom) myMsg.elementAt(0);
		OtpErlangString dbg_tree = (OtpErlangString) myMsg.elementAt(1);
		// Should be 'debugging_tree'
//		System.out.println("Command received: " + command.toString());
//		System.out.println("Data received: " + dbg_tree.toString());

		getBuggyNode(myMbox, from);
	}

	/**
	 * Recursive
	 * 
	 * @param myMbox
	 * @param from
	 * 
	 * @return
	 * 
	 * @throws OtpErlangExit
	 * @throws OtpErlangRangeException
	 * @throws OtpErlangDecodeException
	 */
	private static int getBuggyNode(OtpMbox myMbox, OtpErlangPid from) throws OtpErlangExit, OtpErlangRangeException, OtpErlangDecodeException {
		// Receive question
		OtpErlangObject myObject = myMbox.receive();
		System.out.println("<<<<<<<<<< Received message: " + myObject);
		OtpErlangTuple myMsg = (OtpErlangTuple) myObject;
		OtpErlangAtom command = (OtpErlangAtom) myMsg.elementAt(0);
//		System.out.println("Command received: " + command.toString());
		if (command.toString().equals("question")) {
			OtpErlangLong question = (OtpErlangLong) myMsg.elementAt(1);
//			System.out.println("Question: " + question.longValue());

			OtpErlangTuple state = (OtpErlangTuple) myMsg.elementAt(2);
//			System.out.println("State: " + state.toString() );

			// Send reply with answer
			OtpErlangObject[] reply = new OtpErlangObject[2];
			// Desde interfaz hacer la petición...
			reply[0] = new OtpErlangAtom("answer");
			reply[1] = new OtpErlangAtom("n");
			OtpErlangTuple myTuple = new OtpErlangTuple(reply);
			System.out.println(">>>>>>>>>> Send message: " + from + " :: " + myTuple);
			myMbox.send(from, myTuple);

			return getBuggyNode(myMbox, from);
		}

		if (command.toString().equals("buggy_node")) {
			OtpErlangLong buggyNode = (OtpErlangLong) myMsg.elementAt(1);
			System.out.println("Buggy node: " + buggyNode.intValue());

			return buggyNode.intValue();
		}

		if (command.toString().equals("aborted")) {
			System.out.println("Aborted.");
		}

		return -1;
	}
}