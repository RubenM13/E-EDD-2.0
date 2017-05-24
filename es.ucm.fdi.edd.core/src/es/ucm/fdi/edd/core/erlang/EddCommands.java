package es.ucm.fdi.edd.core.erlang;

/**
 * The EDD comands. 
 */
public enum EddCommands {
	
	READY(0, "ready"), 
	DBG_TREE(1, "dbg_tree"), 
	QUESTION(2, "question"), 
	BUGGY_NODE(3, "buggy_node"), 
	ABORTED(4, "aborted");

	private final int code;
	private final String value;

	/**
	 * @param code
	 *            The code associated to the enumeration.
	 */
	private EddCommands(int code, String value) {
		this.code = code;
		this.value = value;
	}

	/**
	 * The code associated to the enumeration.
	 * 
	 * @return the code.
	 */
	public int getCode() {
		return this.code;
	}
	
	/**
	 * The value associated to the enumeration.
	 * 
	 * @return the value.
	 */
	public String getValue() {
		return value;
	}
}