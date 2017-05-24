package es.ucm.fdi.edd.core.exception;

/**
 * EDD Exception.
 */
public class EDDException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public EDDException() {
		super();
	}

	public EDDException(String message) {
		super(message);
	}

	public EDDException(Throwable cause) {
		super(cause);
	}

	public EDDException(String message, Throwable cause) {
		super(message, cause);
	}

	public EDDException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}