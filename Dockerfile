FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/newspaper-app-1.0.0.jar newspaper-app-1.0.0.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "newspaper-app-1.0.0.jar"]