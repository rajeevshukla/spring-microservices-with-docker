package com.microservices.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class RabbitMQConfig {
	
	@Autowired
	Environment environment;
	
/*
	@Autowired
	Environment environment;

	public final static String USER_QUEUE_CREATED = "user.queue.created";

	@Bean
	Queue userCreatedQueue() {
		return QueueBuilder.durable(USER_QUEUE_CREATED).build();
	}

	@Bean
	Exchange userRegistrationExchange() {
		return ExchangeBuilder.topicExchange("user-registrations").durable(true).build();
	}

	@Bean
	Binding binding() {
		return BindingBuilder.bind(userCreatedQueue()).to(userRegistrationExchange()).with("user.created").noargs();
	}

	ConnectionFactory connectionFactory() {

		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(
				environment.getProperty("spring.rabbitmq.host"));
		cachingConnectionFactory.setUsername(environment.getProperty("spring.rabbitmq.username"));
		cachingConnectionFactory.setPassword(environment.getProperty("spring.rabbitmq.password"));
		return cachingConnectionFactory;

	}*/
	
	

	@Bean
	ConnectionFactory connectionFactory () { 
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory(environment.getProperty("spring.rabbitmq.host"));
		connectionFactory.setUsername("guest");
		connectionFactory.setPassword("guest");
		return connectionFactory;
	}
	
	@Bean
	RabbitTemplate  rabbitTemplate(ConnectionFactory connectionFactory) { 
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jsonMessageConverter());
		return rabbitTemplate;
	}
	
	MessageConverter jsonMessageConverter() { 
		return new Jackson2JsonMessageConverter();
	}

}
