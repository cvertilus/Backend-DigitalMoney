FROM ubuntu:latest
LABEL authors="LKD-Monitoreo"

FROM openjdk:17
ARG JAR_FILE=target/user-service.jar
COPY ${JAR_FILE} user-service.jar
ENTRYPOINT ["java", "-jar", "user-service.jar"]
EXPOSE 8080
# This Dockerfile is for the User Service, which manages user profiles and their associated data.