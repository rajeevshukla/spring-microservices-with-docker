FROM openjdk:8-jre-alpine
COPY ./target/user-registration-service-0.0.1-SNAPSHOT.jar /usr/source/
COPY  wait-for-it.sh /usr/source/
WORKDIR /usr/source/
EXPOSE 8080
ENTRYPOINT ["./wait-for-it.sh", " spring_cloud_config_server:8888","&&","./wait-for-it.sh", " netflix_eureka_naming_server:8761","&&", " java", "-jar", "user-registration-service-0.0.1-SNAPSHOT.jar"]