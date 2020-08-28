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

# Container Monitoring

Monitoring is one of the cross cutting concern that every microservice infra structure has to handle. There are various container monitoring tools available in the market. One of the best open source monitoring tool is **Prometheous**.

But  this won't be enough as it provides information in textual statistics. So we need to represent this information in UI dashbaord format. To do this we need [**Grafana**](https://grafana.com/) . 

We also need [**cAdviser**](https://github.com/google/cadvisor) which will analyze and exposes resource usage and performance data from running containers. cAdvisor exposes Prometheus metrics out of the box. 
 We will using [**Grafana**](https://grafana.com/) as UI dashboard which allows us to query, visualize, alert on and understand your metrics no matter where they stored.  You can create, customize, dashbaord with your team and foster a data driven culture. 

 So we will using this monitoring tool in below flow-  

**cAdvisor --> Prometheus -->  Grafana**
