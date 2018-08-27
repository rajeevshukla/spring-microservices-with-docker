FROM openjdk:8-jre-alpine
WORKDIR /usr/source/
COPY ./target/auth-service-0.0.1-SNAPSHOT.jar /usr/source/
ADD wait-for-it.sh /usr/source/
RUN  chmod 777 /usr/source/wait-for-it.sh
RUN apk add --no-cache bash
EXPOSE 8089
#ENTRYPOINT ["java", "-jar", "auth-service-0.0.1-SNAPSHOT.jar"]
