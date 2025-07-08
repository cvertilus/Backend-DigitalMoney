FROM ubuntu:latest
LABEL authors="colby"

FROM openjdk:17
ARG JAR_FILE=target/cards-service.jar
COPY ${JAR_FILE} cards-service.jar
ENTRYPOINT ["java", "-jar", "cards-service.jar"]
EXPOSE 8080
# This Dockerfile is for the Cards Service, which manages user card information and transactions.
# It is part of a microservices architecture and communicates with other services like Account Service and API Gateway.
# The service handles card-related operations such as adding, updating, and retrieving card details.
