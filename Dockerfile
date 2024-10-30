FROM eclipse-temurin:21-alpine

# Copie o arquivo pom.xml e instale as dependências
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copie o restante do código e compile
COPY src ./src
RUN mvn install

VOLUME /tmp
EXPOSE 8080

WORKDIR /app

# Copie o jar do build anterior
COPY --from=build /app/target/coletor-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
