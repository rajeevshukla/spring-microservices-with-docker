package com.microservices.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class RabbitMQConfig {

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

	}

}
