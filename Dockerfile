FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/airLine-management-system-0.0.1-SNAPSHOT.jar /app/airLine-management-system.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/airLine-management-system.jar"]