# FROM eclipse-temurin:17-jdk-jammy
# ARG JAR_FILE=target/*.jar
# COPY ${JAR_FILE} app.jar
# ENTRYPOINT ["java","-jar","/app.jar"]
# FROM eclipse-temurin:17-jdk-jammy
# COPY target/demo-0.0.1-SNAPSHOT.jar app.jar
# ENTRYPOINT ["java","-jar","/app.jar"]
# Stage 1: build jar
# Stage 1: build jar
FROM maven:3.9.3-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: run jar
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
# COPY --from=build /app/target/*.jar app.jar
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
