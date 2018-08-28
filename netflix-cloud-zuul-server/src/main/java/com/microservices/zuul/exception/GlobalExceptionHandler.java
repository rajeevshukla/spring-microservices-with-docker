package com.microservices.zuul.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleGlobalExcpetion(Exception e, WebRequest request) {
		return new ResponseEntity<>("An exception has occurred ", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
