FROM openjdk:8-jre-alpine
COPY ./target/spring-cloud-config-server-0.0.1-SNAPSHOT.jar /usr/source/
WORKDIR /usr/source/
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "spring-cloud-config-server-0.0.1-SNAPSHOT.jar"]