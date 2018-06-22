package com.email.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmailServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailServicesApplication.class, args); 
		System.out.println("Running email application");
	}
}
