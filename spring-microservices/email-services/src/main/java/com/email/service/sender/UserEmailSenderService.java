package com.email.service.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class UserEmailSenderService {

	@Autowired
	JavaMailSender javaMailSender;
	
	public boolean sendRegistrationEmail(String subject) {
		
		boolean isSent = false;
		
		System.out.println("Sending registration Email");
		

		SimpleMailMessage simpleMessage = new SimpleMailMessage();
		simpleMessage.setTo("mail2rajeevshukla@gmail.com");
		simpleMessage.setSubject(subject);
		simpleMessage.setText("Welcome");
		javaMailSender.send(simpleMessage);;
		
		return isSent;
	}
	
	
}
