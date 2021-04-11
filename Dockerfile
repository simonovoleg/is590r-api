FROM openjdk:11-jdk-slim
COPY target/api-1.0.0.jar app.jar
EXPOSE 8080/tcp
ENTRYPOINT ["java","-jar","/app.jar"]