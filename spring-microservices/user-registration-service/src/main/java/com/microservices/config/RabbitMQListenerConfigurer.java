package com.microservices.config;

import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

@Configuration
public class RabbitMQListenerConfigurer  implements RabbitListenerConfigurer{

	@Override
	public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
		registrar.setMessageHandlerMethodFactory(jsonMessageHandlerMethodFactory());
		System.out.println(" ======= JsonMessageConverted Registered   ====");
	}

	private  DefaultMessageHandlerMethodFactory jsonMessageHandlerMethodFactory() { 
		DefaultMessageHandlerMethodFactory messageHandlerMethodFactory = new DefaultMessageHandlerMethodFactory();
		messageHandlerMethodFactory.setMessageConverter(new MappingJackson2MessageConverter());
		return messageHandlerMethodFactory;
	}
	
}
