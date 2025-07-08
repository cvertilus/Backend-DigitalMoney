FROM openjdk:17
ARG JAR_FILE=target/login-service.jar
COPY ${JAR_FILE} login-service.jar
ENTRYPOINT ["java", "-jar", "login-service.jar"]
EXPOSE 8083
