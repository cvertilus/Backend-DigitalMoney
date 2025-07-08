FROM ubuntu:latest
LABEL authors="Colby"
LABEL description="API Gateway for the microservices architecture"
FROM openjdk:17
ARG JAR_FILE=target/api-gateway.jar
COPY ${JAR_FILE} api-gateway.jar
ENTRYPOINT ["java", "-jar", "api-gateway.jar"]
EXPOSE 8081


