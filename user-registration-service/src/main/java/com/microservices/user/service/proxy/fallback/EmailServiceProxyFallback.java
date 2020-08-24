package com.microservices.user.service.proxy.fallback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.microservices.user.service.proxy.EmailServiceProxy;

import feign.FeignException;

@Component
public class EmailServiceProxyFallback implements EmailServiceProxy {

	private static final Logger log = LoggerFactory.getLogger(EmailServiceProxyFallback.class);
	
	private Throwable cause;
	public EmailServiceProxyFallback(Throwable  cause) {
		this.cause = cause;
	}
	
	@Override
	public boolean sendUserEmail(String userJson) {
		log.error("Fallback request recieved !"+ userJson +" Just logging this request and returning false");
		if(this.cause instanceof FeignException) {
			log.error("Error in calling sendUserEmail Proxy response status :{}",((FeignException)cause).status());
		}
		
		return false;
	}
}
