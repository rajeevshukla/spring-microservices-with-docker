package com.email.service.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.email.service.listener.RabbitMQListener;

@Configuration
public class RabbitMqConfig {

	
	/** 
	 * Creating queues using builder
	 * 
	 * */
	@Bean
	Queue myQueue() {
	 return	QueueBuilder.durable("SpringQueue").autoDelete().build();
	}

	
	@Bean
	Exchange myExchange() {
		return ExchangeBuilder.fanoutExchange("spring.fanout").autoDelete().durable(true).build();
	}
	
	/** 
	 * 
	 * Creating binding between queues and Exchange with routing key "topic" with no other parameters
	 * 
	 * */
	@Bean
	Binding exchangeQueueBinding() { 
		
	 return	BindingBuilder.bind(myQueue()).to(myExchange()).with("topic").noargs();
	}
	
	
	@Bean
	ConnectionFactory connectionFactory () { 
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory("rabbitmq");
		connectionFactory.setUsername("guest");
		connectionFactory.setPassword("guest");
		return connectionFactory;
	}

	@Bean
	MessageListenerContainer messageListenerContainer() {

		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory());
		//		container.setConcurrentConsumers(500);
		container.setQueues(myQueue());
//	container.setQueueNames("test.fanout.queue1","test.fanout.queue2","test.fanout.queue3");
		container.setMessageListener(new RabbitMQListener());
		return container;
	}
	
}
