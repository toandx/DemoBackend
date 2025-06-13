FROM eclipse-temurin:17-jdk-jammy

# Set the working directory in the container
WORKDIR /app

# Copy the jar file into the container
COPY target/demoBackend-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your app runs on
EXPOSE 8081

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]