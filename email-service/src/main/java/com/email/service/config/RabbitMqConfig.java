package com.email.service.config;

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

import com.email.service.listener.UserEventListener;

@Configuration
public class RabbitMqConfig {

	public final static String USER_QUEUE_CREATED = "user.queue.created";
	public final static String USER_QUEUE_UPDATED = "user.queue.updated";
	public final static String USER_QUEUE_DELETED = "user.queue.deleted";
	@Autowired
	Environment environment;


	/** 
	 * Creating queues using builder
	 * 
	 * */
	@Bean
	Queue userCreateQueue() {
		return	QueueBuilder.durable(USER_QUEUE_CREATED).build();
	}


	/** 
	 * Creating queues using builder
	 * 
	 * */
	@Bean
	Queue userUpdateQueue() {
		return	QueueBuilder.durable(USER_QUEUE_UPDATED).build();
	}

	/** 
	 * Creating queues using builder
	 * 
	 * */
	@Bean
	Queue userDeleteQueue() {
		return	QueueBuilder.durable(USER_QUEUE_DELETED).build();
	}

	@Bean
	Exchange myExchange() {
		return ExchangeBuilder.topicExchange("user-registrations").durable(true).build();
	}

	/** 
	 * 
	 * Creating binding between queues and Exchange with routing key "topic" with no other parameters
	 * 
	 * */
	@Bean
	Binding exchangeCreateQueueBinding() { 
		return	BindingBuilder.bind(userCreateQueue()).to(myExchange()).with("user.created").noargs();
	}
	
	/** 
	 * 
	 * Creating binding between queues and Exchange with routing key "topic" with no other parameters
	 * 
	 * */
	@Bean
	Binding exchangeUpdateQueueBinding() { 
		return	BindingBuilder.bind(userUpdateQueue()).to(myExchange()).with("user.updated").noargs();
	}
	/** 
	 * 
	 * Creating binding between queues and Exchange with routing key "topic" with no other parameters
	 * 
	 * */
	@Bean
	Binding exchangeDeleteQueueBinding() { 
		return	BindingBuilder.bind(userDeleteQueue()).to(myExchange()).with("user.deleted").noargs();
	}


	@Bean
	ConnectionFactory connectionFactory () { 
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory(environment.getProperty("spring.rabbitmq.host"));
		connectionFactory.setUsername("guest");
		connectionFactory.setPassword("guest");
		return connectionFactory;
	}
	/*
	@Bean
	MessageListenerContainer messageListenerContainer() {

		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory());
		//		container.setConcurrentConsumers(500);
		container.setQueues(myQueue());
//	container.setQueueNames("test.fanout.queue1","test.fanout.queue2","test.fanout.queue3");
		//container.setMessageListener(new RabbitMQListener());
		return container;
	}
	 */	

}
