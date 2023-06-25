package com.example.protocol.exception;

public class ParseException extends Exception {
	private static final long serialVersionUID = 7141673275252683916L;

	public ParseException() {
	}

	public ParseException(String message) {
		super(message);
	}

	public ParseException(Throwable cause) {
		super(cause);
	}

	public ParseException(String message, Throwable cause) {
		super(message, cause);
	}
}