FROM maven:3.9-eclipse-temurin-21 AS builder

ARG APP_DIR=/src

WORKDIR ${APP_DIR}

COPY mvnw .
COPY mvnw.cmd .
COPY pom.xml .

COPY .mvn .mvn
COPY src src

RUN mvn package -Dmaven.test.skip=true

FROM openjdk:21-jdk

ARG APP_DIR2=/JD
WORKDIR ${APP_DIR2}

ENV PORT=8080 SPRING_REDIS_HOST=localhost SPRING_REDIS_PORT=6379
ENV SPRING_REDIS_USERNAME=NOT_SET SPRING_REDIS_PASSWORD=NOT_SET

COPY --from=builder /src/target/vttp_mini_project_1-0.0.1-SNAPSHOT.jar SolarSG.jar

EXPOSE ${PORT}

ENTRYPOINT SERVER_PORT=${PORT} java -jar SolarSG.jar
