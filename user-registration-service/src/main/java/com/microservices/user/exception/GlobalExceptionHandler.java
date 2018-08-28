package com.microservices.user.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	public ResponseEntity<Object> handleGlobalException(Exception e, WebRequest request) {

		return new ResponseEntity<Object>("An excpetion has occured !", new HttpHeaders(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
