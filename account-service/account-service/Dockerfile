FROM ubuntu:latest
LABEL authors="Colby"

FROM openjdk:17
ARG JAR_FILE=target/account-service.jar
COPY ${JAR_FILE} account-service.jar
ENTRYPOINT ["java", "-jar", "account-service.jar"]
EXPOSE 8080
# This Dockerfile is for the Account Service, which manages user accounts and their associated data.