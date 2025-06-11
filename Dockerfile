FROM docker.io/eclipse-temurin:21 AS build
ARG APP_DIR
WORKDIR /app
COPY ${APP_DIR} ./appdir
WORKDIR /app/appdir
RUN chmod +x ./gradlew
RUN ./gradlew clean bootJar

FROM docker.io/eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/appdir/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
