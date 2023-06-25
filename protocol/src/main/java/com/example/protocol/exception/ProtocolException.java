package com.example.protocol.exception;

public class ProtocolException extends Exception {
	private static final long serialVersionUID = -1122638297159830927L;

	public ProtocolException() {
	}

	public ProtocolException(String message) {
		super(message);
	}

	public ProtocolException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProtocolException(Throwable cause) {
		super(cause);
	}
}