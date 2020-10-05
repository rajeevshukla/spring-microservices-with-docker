package com.email.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EmailServicesApplication implements CommandLineRunner {
     
	public static void main(String[] args) {
		SpringApplication.run(EmailServicesApplication.class, args); 
		System.out.println(" === Email service is up and running.. ======");
	}
	
	@Override
	public void run(String... args) throws Exception {
		
	}
}
