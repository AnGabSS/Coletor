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

# Defina o diretório de trabalho
WORKDIR /coletor

# Copie o jar do estágio de build
COPY --from=build /app/out/artifacts/coletor_jar/coletor.jar .

ENV PROFILE=test

EXPOSE 8080

ENTRYPOINT ["java", "-Dspring.profiles.active=${PROFILE}", "-jar", "coletor.jar"]
