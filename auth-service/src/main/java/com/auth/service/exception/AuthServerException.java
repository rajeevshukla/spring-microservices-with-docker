package com.auth.service.exception;

public class AuthServerException extends RuntimeException {
	private static final long serialVersionUID = 392741407144198620L;

	public AuthServerException() {

	}

	public AuthServerException(String message) {
		super(message);
	}

	public AuthServerException(Throwable t) {
		super(t);
	}

	public AuthServerException(String message, Throwable t) {
		super(message, t);
	}
}
