package com.email.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootApplication
public class EmailServicesApplication implements CommandLineRunner {
     
	@Autowired
	JavaMailSender javaMailSender;
	
	public static void main(String[] args) {
		SpringApplication.run(EmailServicesApplication.class, args); 
		System.out.println(" === Email service is up and running.. ======");
	}
	
	@Override
	public void run(String... args) throws Exception {
	  
		SimpleMailMessage simpleMessage = new SimpleMailMessage();
		simpleMessage.setTo("mail2rajeevshukla@gmail.com");
		simpleMessage.setSubject("Test email");
		simpleMessage.setText("Welcome");
		javaMailSender.send(simpleMessage);;
	}
}
