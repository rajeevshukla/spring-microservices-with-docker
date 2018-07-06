package com.email.service.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import com.email.service.sender.UserEmailSenderService;

public class UserEventListener {

	@Autowired
	UserEmailSenderService userEmailSenderService;
	
	
	@RabbitListener(concurrency="3-5", queues="user.queue.created")
	public void userCreateListener(String message) { 
		System.out.println("User Created Event recieved ====");
		 userEmailSenderService.sendRegistrationEmail("User Created");	
	}
	
	
	@RabbitListener(concurrency="3-5", queues="user.queue.updated")
	public void userUpdateListener(String message) { 
		System.out.println("User Updated Event recieved ====");
		 userEmailSenderService.sendRegistrationEmail("User Updated");
	}
	

	@RabbitListener(concurrency="3-5", queues="user.queue.deleted")
	public void userDeleteListener(String message) { 
		System.out.println("User Deleted Event recieved ====");
		 userEmailSenderService.sendRegistrationEmail("User Deleted");
	}
	
	
}
