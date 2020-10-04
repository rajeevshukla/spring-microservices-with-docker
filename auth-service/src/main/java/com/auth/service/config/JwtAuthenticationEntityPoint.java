package com.auth.service.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class JwtAuthenticationEntityPoint implements AuthenticationEntryPoint{
	
	
	@Autowired
	private HandlerExceptionResolver handlerExceptionResolver;

	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		log.error("Unautheticated Request recieved !!");
		 handlerExceptionResolver.resolveException(request, response, null, authException);
	}
	
}
