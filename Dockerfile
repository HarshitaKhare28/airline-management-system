FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/airline-management-system-0.0.1-SNAPSHOT.jar /app/airline-management-system.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/airline-management-system.jar"]