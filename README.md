[![CircleCI](https://circleci.com/gh/rajeevshukla/spring-microservices-with-docker/tree/master.svg?style=svg)](https://circleci.com/gh/rajeevshukla/spring-microservices-with-docker/tree/master)

# Overview

This project is a demo project designed and developed using spring framework and docker+swarm with considering microservices architecture  pattern in mind.

# spring-microservices-with-docker

  Technologies used in this project : <br>

- Spring Cloud
- Spring Fiegn
- Spring Ribbon
- Netflix Eureka
- Circuit Breaker Pattern (Hystrix)<br>
- Netflix Zuul <br>
- Spring Sleuth<br>
- Spring Boot 2.0 <br>
- Rabbit MQ 3.2
- MySQL 5.6
- Spring Data JPA 
- Zipkin
 
# How to run?

Make sure that you have installed docker on your machine and docker swarm is up and running with at least 1 master and 2 worker. 

 Then Follow Below Steps to run it in docker environment using docker-swarm .

 1. Checkout the project using command  `# git clone https://github.com/rajeevshukla/spring-microservices-with-docker.git`
 2. Navigate inside it `# cd spring-microservices-with-docker`
 3. Deploy it on docker-swarm cluster `# docker  stack deploy --compose-file docker-compose-swarm.yml mystack` and enter. 

This will spawn up all the services and deploy it on all connected node.  

# Architecture 

![Architecture image](https://github.com/rajeevshukla/spring-microservices-with-docker/blob/master/Architecture.png)
