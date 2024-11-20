# Stage 1: Build stage
FROM maven:3.9.9-eclipse-temurin-23 AS builder

WORKDIR /app

# Copy the pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the source code and build the application
COPY src ./src
RUN mvn package -DskipTests

# Stage 2: Runtime stage
FROM eclipse-temurin:23-jre-alpine

WORKDIR /app

# Copy the built JAR from the builder stage
COPY --from=builder /app/target/devicemanagement-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 9091

# Run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
