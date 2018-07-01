package com.email.service.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class UserEventListener {

	
	
	@RabbitListener(concurrency="3-5", queues="user.queue.created")
	public void userCreateListener(String message) { 
		System.out.println("User Created Event recieved ====");
	}
	
	
	@RabbitListener(concurrency="3-5", queues="user.queue.updated")
	public void userUpdateListener(String message) { 
		System.out.println("User Updated Event recieved ====");
	}
	

	@RabbitListener(concurrency="3-5", queues="user.queue.deleted")
	public void userDeleteListener(String message) { 
		System.out.println("User Deleted Event recieved ====");
	}
	
	
}
