
FROM maven:3.9.11-amazoncorretto-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package



FROM alpine/java:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar myapp-back.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "myapp-back.jar"]