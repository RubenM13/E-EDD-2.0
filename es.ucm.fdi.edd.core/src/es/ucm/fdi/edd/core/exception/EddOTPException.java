package es.ucm.fdi.edd.core.exception;

import com.ericsson.otp.erlang.OtpException;

/**
 * EDD OTP Exception.
 */
public class EddOTPException extends OtpException {
	
	private static final long serialVersionUID = 1L;

	public EddOTPException() {
		super();
	}

	public EddOTPException(String message) {
		super(message);
	}

	public EddOTPException(Throwable cause) {
//		super(cause);
	}

	public EddOTPException(String message, Throwable cause) {
//		super(message, cause);
	}

	public EddOTPException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
//		super(message, cause, enableSuppression, writableStackTrace);
	}
}