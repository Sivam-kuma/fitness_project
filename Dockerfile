# Use OpenJDK base image
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
COPY build/libs/demo-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose port 8080 (or the port your app uses)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]