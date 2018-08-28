package com.email.service.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class GlobalExcpetionHandler  extends ResponseEntityExceptionHandler{
	
	
	@ExceptionHandler({Exception.class})
	public ResponseEntity<Object> handleGlobalException(Exception ex, WebRequest request){
		return new ResponseEntity<Object>("An exception has occurred", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

}
