package com.microservices.user.service.proxy.fallback.factory;

import org.springframework.stereotype.Component;

import com.microservices.user.service.proxy.EmailServiceProxy;
import com.microservices.user.service.proxy.fallback.EmailServiceProxyFallback;

import feign.hystrix.FallbackFactory;

@Component
public class EmailServiceProxyFallbackFactory implements FallbackFactory<EmailServiceProxy> {

	@Override
	public EmailServiceProxy create(Throwable cause) {
		return new EmailServiceProxyFallback(cause);
	}
}
