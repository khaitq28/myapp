
FROM maven:3.8.6-openjdk-11 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package



FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/target/*.jar myapp-back.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "myapp-back.jar"]