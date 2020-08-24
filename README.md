[![CircleCI](https://circleci.com/gh/rajeevshukla/spring-microservices-with-docker/tree/master.svg?style=svg)](https://circleci.com/gh/rajeevshukla/spring-microservices-with-docker/tree/master)

# Overview 
This project is a demo project designed and developed using spring framework and docker+swarm with considering microservices architecture  pattern in mind. 

# spring-microservices-with-docker
  Technologies used in this project : <br>
 - Spring Cloud <br>
 - Spring Fiegn <br>
 - Spring Ribbon <br>
 - Netflix Eureka<br>
 - Circuit Breaker Pattern (Hystrix)<br>
 - Netflix Zuul <br>
 - Spring Sleuth<br>
 - Spring Boot 2.0 <br>
 - Rabbit MQ 3.2
 - MySQL 5.6
 - Spring Data JPA 
 - Zipkin
 
# How to run this project on your machine ? 
 Follow Below Steps to run it in docker environmetn using docker-swarm 
 Check out this project using git clone #repo URL
 1. `$git clone https://github.com/rajeevshukla/spring-microservices-with-docker.git` 
2. `cd spring-microservices-with-docker`
3. `#docker  stack deploy --compose-file docker-compose-swarm.yml mystack` and enter. 

This will spawn up all the services and deploy it on all connected node.  

# Architecture 

![Architecture image](https://github.com/rajeevshukla/spring-microservices-with-docker/blob/master/Architecture.png)
