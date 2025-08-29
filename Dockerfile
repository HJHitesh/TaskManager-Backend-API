# Use OpenJDK image as base
FROM openjdk:17-jdk-slim

# Set working directory inside container
WORKDIR /app

# Copy the Spring Boot jar into container
COPY target/taskapi-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your Spring Boot app runs on
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
