# Use a imagem oficial do Eclipse Temurin para a etapa de build e runtime
FROM eclipse-temurin:21-alpine AS build

# Defina o diretório de trabalho
WORKDIR /app

# Copie o arquivo pom.xml e qualquer outro arquivo necessário primeiro para aproveitar o caching
COPY pom.xml .

# Instale as dependências do Maven offline
RUN apk add --no-cache maven
RUN mvn dependency:go-offline -B

# Copie o restante do código e compile o projeto
COPY src ./src
RUN mvn clean install -DskipTests

# Use uma imagem base do OpenJDK para a etapa de runtime
FROM eclipse-temurin:21-alpine
WORKDIR /app

# Copie o jar da etapa de build
ARG JAR_FILE=target/coletor-0.0.1-SNAPSHOT.jar
COPY --from=build /app/${JAR_FILE} app.jar

# Defina o volume temporário
VOLUME /tmp

# Exponha a porta que o Spring Boot usará
EXPOSE 8080

# Comando para rodar o aplicativo
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
