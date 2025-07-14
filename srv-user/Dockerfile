FROM maven:3.9.10-amazoncorretto-21 AS builder
COPY pom.xml /build/pom.xml
COPY src /build/src
WORKDIR /build
RUN mvn clean package

FROM openjdk:21
COPY --from=builder build/target/*.jar /app/my-app.jar
WORKDIR /app
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "my-app.jar"]
