package com.auth.service.exception;

public class UserAlreadyExistsException extends RuntimeException{
	
	private static final long serialVersionUID = -4370687290630213481L;

	public UserAlreadyExistsException() {

	}

	public UserAlreadyExistsException(String message) {
		super(message);
	}

	public UserAlreadyExistsException(Throwable t) {
		super(t);
	}

	public UserAlreadyExistsException(String message, Throwable t) {
		super(message, t);
	}
}
