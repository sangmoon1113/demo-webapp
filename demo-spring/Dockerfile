# syntax=docker/dockerfile:1
FROM gradle:jdk21-alpine AS builder
WORKDIR /src

COPY --chown=gradle:gradle . /src
USER root
RUN chown -R gradle /src

COPY . .
RUN gradle clean bootJar

FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=builder  /src/build/libs/*.jar  app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

# docker build --tag demo-spring .
# docker build -t demo-spring .
#
# docker run --name demo-spring -p 8080:8080 demo-spring