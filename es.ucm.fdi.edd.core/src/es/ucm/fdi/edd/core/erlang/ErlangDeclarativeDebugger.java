package es.ucm.fdi.edd.core.erlang;

import java.io.IOException;

import es.ucm.fdi.edd.core.json.model.JsonDocument;
import es.ucm.fdi.edd.core.json.utils.JsonHelper;
import es.ucm.fdi.edd.core.json.utils.JsonUtils;

/**
 * Lazy instantiation of Singleton Pattern.
 */
public class ErlangDeclarativeDebugger {

	/** The private singleton instance. */
	private static ErlangDeclarativeDebugger edd;
	
	private JsonDocument document;
	
	private String debugTree;
	private String question;
	private String questionResponse = "n";
	private long questionIndex;

	/**
	 * The private singleton constructor.
	 */
	private ErlangDeclarativeDebugger() {
		// empty
	}

	/**
	 * Gets the singleton instance. 
	 * 
	 * @return
	 * 		the instance.
	 */
	public static ErlangDeclarativeDebugger getInstance() {
		if (edd == null) {
			synchronized (ErlangDeclarativeDebugger.class) {
				if (edd == null) {
					// instance will be created at request time
					edd = new ErlangDeclarativeDebugger();
				}
			}
		}
		
		return edd;
	}

/*	
	public String getDebugTree() {
		return debugTree;
	}
	
	public void setDebugTree(String debugTree) {
		this.debugTree = debugTree;
	}


	public long getQuestionIndex() {
		return questionIndex;
	}

	public void setQuestionIndex(long questionIndex) {
		this.questionIndex = questionIndex;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getQuestionResponse() {
		return questionResponse;
	}

	public void setQuestionResponse(String questionResponse) {
		this.questionResponse = questionResponse;
	}
	
	public JsonDocument getJsonDocument() {
		try {
			document = (JsonDocument) JsonHelper.readJsonFromString(debugTree, JsonDocument.class);	
			return document;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void writeJsonDocument(String path) {
		try {
			JsonUtils jsu = new JsonUtils();
			jsu.toFile(document, path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
*/
}