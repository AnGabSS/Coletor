FROM eclipse-temurin:21-alpine

# Copie o arquivo pom.xml e instale as dependências
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copie o restante do código e compile
COPY src ./src
RUN mvn install

VOLUME /tmp
EXPOSE 8080

ARG JAR_FILE=target/coletor-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
