FROM openjdk:22-jdk-slim
WORKDIR /app
COPY target/Lab5-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
