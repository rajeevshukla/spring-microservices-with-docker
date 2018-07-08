package com.email.service.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import com.email.service.sender.UserEmailSenderService;

public class UserEventListener {

	@Autowired
	UserEmailSenderService userEmailSenderService;
	
	
	@RabbitListener(concurrency="3-5", queues="user.queue.created")
	public void userCreateListener(Message message) { 
		System.out.println("User Created Event recieved ====:" + message);
		 userEmailSenderService.sendRegistrationEmail(new String(message.getBody()));	
	}
	
	
	@RabbitListener(concurrency="3-5", queues="user.queue.updated")
	public void userUpdateListener(Message message) { 
		System.out.println("User Updated Event recieved ===="+ message);
		 userEmailSenderService.sendRegistrationEmail(new String(message.getBody()));
	}
	

	@RabbitListener(concurrency="3-5", queues="user.queue.deleted")
	public void userDeleteListener(Message message) { 
		System.out.println("User Deleted Event recieved ===="+ message);
		 userEmailSenderService.sendRegistrationEmail(new String(message.getBody()));
	}
	
	
}
