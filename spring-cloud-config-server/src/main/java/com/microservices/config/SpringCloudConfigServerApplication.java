package com.microservices.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class SpringCloudConfigServerApplication  implements CommandLineRunner{

	@Value("${spring.profiles.active}")
	String activeProfile;
	public static void main(String[] args) {
		SpringApplication.run(SpringCloudConfigServerApplication.class, args);
		System.out.println("============== Cloud Config Server is UP ========== ");
	}
	
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Active profile is :"+activeProfile);
	}
	
}
