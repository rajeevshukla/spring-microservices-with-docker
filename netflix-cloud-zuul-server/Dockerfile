FROM openjdk:8-jre-alpine
COPY ./target/netflix-cloud-zuul-server-0.0.1-SNAPSHOT.jar /usr/source/
WORKDIR /usr/source/
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "netflix-cloud-zuul-server-0.0.1-SNAPSHOT.jar"]