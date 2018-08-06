package com.book.exceptions;

public class InvalidParameterException extends RuntimeException {

	private static final long serialVersionUID = -1663631439510662225L;

	public InvalidParameterException() {
	}

	public InvalidParameterException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidParameterException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidParameterException(String message) {
		super(message);
	}

	public InvalidParameterException(Throwable cause) {
		super(cause);
}
}