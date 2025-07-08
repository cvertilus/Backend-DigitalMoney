FROM ubuntu:latest
LABEL authors="Colby"

FROM openjdk:17
ARG JAR_FILE=target/activities-service.jar
COPY ${JAR_FILE} activities-service.jar
ENTRYPOINT ["java", "-jar", "activities-service.jar"]
EXPOSE 8080
# This Dockerfile is for the Activities Service, which manages user activities and their associated data.
# It is part of a microservices architecture and communicates with other services like Account Service and API Gateway.

