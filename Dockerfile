#stage 1
FROM gradle:jdk21 as builder

WORKDIR /app

COPY ./build.gradle .
COPY ./settings.gradle .

COPY src ./src

RUN gradle build --no-daemon

#stage 2
FROM openjdk:21-jdk-slim

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar discografia.jar

EXPOSE 443

CMD ["java","-jar","discografia"]