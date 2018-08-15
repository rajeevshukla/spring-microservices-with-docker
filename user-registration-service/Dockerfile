FROM openjdk:8-jre-alpine
COPY ./target/user-registration-service-0.0.1-SNAPSHOT.jar /usr/source/
COPY  wait-for-it.sh /usr/source/
WORKDIR /usr/source/
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "user-registration-service-0.0.1-SNAPSHOT.jar"]