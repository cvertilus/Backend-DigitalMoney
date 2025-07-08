FROM ubuntu:latest
LABEL authors="Colby"

FROM openjdk:17
ARG JAR_FILE=target/config-server.jar
COPY ${JAR_FILE} config-server.jar
ENTRYPOINT ["java", "-jar", "config-server.jar"]
EXPOSE 8888
# This Dockerfile is for the Config Server, which provides configuration management for microservices.
# It allows microservices to retrieve their configuration from a central location.

