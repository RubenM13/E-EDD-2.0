package es.ucm.fdi.edd.core.erlang.model;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.ericsson.otp.erlang.OtpErlangAtom;
import com.ericsson.otp.erlang.OtpErlangList;
import com.ericsson.otp.erlang.OtpErlangLong;
import com.ericsson.otp.erlang.OtpErlangObject;
import com.ericsson.otp.erlang.OtpErlangTuple;

public class EddState {

	private OtpErlangObject erlState;
	
	private List<Integer> vertexList;
	private List<Integer> correctList;
	private List<Integer> notCorrectList;
	private List<Integer> unknownList;

	public EddState(OtpErlangObject erlState) {
		this.erlState = erlState;
		
		initialize();
	}

	private void initialize() {
		OtpErlangTuple stateTuple = (OtpErlangTuple) erlState;
		if (stateTuple.arity() == 4) {
			OtpErlangList vertex = (OtpErlangList) stateTuple.elementAt(0);
			vertexList = processElements(vertex);
//			System.out.println("1: " + Arrays.toString(vertex.elements()) + "\n      " + Arrays.toString(vertexList.toArray()));
			
			OtpErlangList correct = (OtpErlangList) stateTuple.elementAt(1);
			correctList = processElements(correct);
//			System.out.println("2: " + Arrays.toString(correct.elements()) + "\n     " + Arrays.toString(correctList.toArray()));
			
			OtpErlangList notCorrect = (OtpErlangList) stateTuple.elementAt(2);
			notCorrectList = processElements(notCorrect);
//			System.out.println("3: " + Arrays.toString(notCorrect.elements()) + "\n      " + Arrays.toString(notCorrectList.toArray()));
			
			OtpErlangList unknown = (OtpErlangList) stateTuple.elementAt(3);
			unknownList = processElements(unknown);
//			System.out.println("4: " + Arrays.toString(unknown.elements()) + "\n      " + Arrays.toString(unknownList.toArray()));
		}
	}
	
	private List<Integer> processElements(OtpErlangList list) {
		List<Integer> elementsList = new LinkedList<Integer>();
		for (OtpErlangObject otpErlangObject : list) {
			if (otpErlangObject instanceof OtpErlangAtom) {
				OtpErlangAtom atom = (OtpErlangAtom) otpErlangObject;
				if (atom.atomValue().equals("a")) {
					continue;
				}
				else {
					System.out.println("The tuple is malformmed: " + Arrays.toString(list.elements()));
				}
			}
			else if (otpErlangObject instanceof OtpErlangLong) {
				OtpErlangLong node = (OtpErlangLong) otpErlangObject;
				elementsList.add((int) node.longValue());
			}
		}
		
		return elementsList;
	}

	public List<Integer> getVertexList() {
		return vertexList;
	}

	public void setVertexList(List<Integer> vertexList) {
		this.vertexList = vertexList;
	}

	public List<Integer> getCorrectList() {
		return correctList;
	}

	public void setCorrectList(List<Integer> correctList) {
		this.correctList = correctList;
	}

	public List<Integer> getNotCorrectList() {
		return notCorrectList;
	}

	public void setNotCorrectList(List<Integer> notCorrectList) {
		this.notCorrectList = notCorrectList;
	}

	public List<Integer> getUnknownList() {
		return unknownList;
	}

	public void setUnknownList(List<Integer> unknownList) {
		this.unknownList = unknownList;
	}
}
