# 1. Estágio de compilação
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# 2. Estágio de execução
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/gestao-licita-nc-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]