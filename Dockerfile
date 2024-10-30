FROM eclipse-temurin:21-alpine

# Set the working directory
WORKDIR /app

# Copy the pom.xml and any other necessary files first to leverage caching
COPY pom.xml .
COPY src ./src

# Run Maven to build the project
RUN mvn clean install

# Copy the JAR file to the image
ARG JAR_FILE=target/coletor-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

VOLUME /tmp
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]

