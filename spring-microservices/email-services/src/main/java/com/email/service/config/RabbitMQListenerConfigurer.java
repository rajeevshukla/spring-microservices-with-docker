package com.email.service.config;

import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

@Configuration
public class RabbitMQListenerConfigurer implements RabbitListenerConfigurer {

	@Override
	public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
		registrar.setMessageHandlerMethodFactory(myCustomMessageHandlerFactory());
		System.out.println("=== Custom message converter registered === ");
	}

	@Bean(name="myCustomMessageHandlerFactory")
	public DefaultMessageHandlerMethodFactory myCustomMessageHandlerFactory() { 
		DefaultMessageHandlerMethodFactory defaultMessageHandlerMethodFactory = new DefaultMessageHandlerMethodFactory();
		defaultMessageHandlerMethodFactory.setMessageConverter(new MappingJackson2MessageConverter());
		return defaultMessageHandlerMethodFactory;
	}
	
  
}
