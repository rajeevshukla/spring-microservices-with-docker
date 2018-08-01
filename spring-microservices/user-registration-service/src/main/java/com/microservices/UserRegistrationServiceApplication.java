package com.microservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.microservices") // to enable feign rest client
@EnableDiscoveryClient
public class UserRegistrationServiceApplication implements CommandLineRunner{

	
	@Autowired
	DiscoveryClient discoveryClient;
	
	public static void main(String[] args) {
		SpringApplication.run(UserRegistrationServiceApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
	
		System.out.println(discoveryClient.getServices());
		
	}
}
