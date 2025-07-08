FROM ubuntu:latest
LABEL authors="Colby"

FROM openjdk:17
ARG JAR_FILE=target/eureka-server.jar
COPY ${JAR_FILE} eureka-server.jar
ENTRYPOINT ["java", "-jar", "eureka-server.jar"]
EXPOSE 8761
# This Dockerfile is for the Eureka server, which is a service registry for microservices.