# Base build image
FROM maven:3.9.4-eclipse-temurin-17-alpine AS builder
LABEL maintainer="Иван Иванов"
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Runtime image
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
ENV ENVIRONMENT stage

# Create a non-root user and change to that user
RUN adduser -D myuser
USER myuser

ENTRYPOINT ["java", "-jar", "app.jar"]
