package com.jpmc.uk.trading.errorhandling;

public class ExceptionHandler extends Exception {

	private static final long serialVersionUID = -1254896948440951159L;

	public ExceptionHandler(final String message) {
		super(message);
	}

	public ExceptionHandler(final String message, final Exception exception) {
		super(message, exception);
	}

	public ExceptionHandler(final String message, final Throwable cause) {
		super(message, cause);
	}

}
