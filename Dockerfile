FROM eclipse-temurin:21-alpine

RUN mvn clean install

VOLUME /tmp
EXPOSE 8080

ARG JAR_FILE=/tmp/target/coletor-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
