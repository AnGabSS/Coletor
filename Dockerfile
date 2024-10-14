FROM maven:3.9.4-eclipse-temurin-17-alpine AS build


LABEL authors="User"

WORKDIR /app

# Copy the pom.xml and download dependencies first for caching
COPY pom.xml .
RUN mvn dependency:go-offline

# Now copy the rest of the source code
COPY . .

# Build the application with Maven
RUN mvn package

# Use a smaller runtime image for the final stage
FROM eclipse-temurin:17-alpine

WORKDIR /app

# Copy the jar file from the build stage
COPY --from=build /app/target/coletor-0.0.1-SNAPSHOT.jar .

ENV PROFILE=test

EXPOSE 8080

ENTRYPOINT ["java", "-Dspring.profiles.active=${PROFILE}", "-jar", "app.jar"]
