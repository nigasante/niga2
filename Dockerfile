FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/newspaper-app-1.0.0.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
