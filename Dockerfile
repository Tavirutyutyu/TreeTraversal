FROM eclipse-temurin:21-jdk as build

ARG USERNAME
WORKDIR /app

COPY . ./

WORKDIR /app/${APP_DIR}
RUN chmod +x ./gradlew
RUN ./gradlew clean bootJar

FROM eclipse-temurin:21-jdk

ARG USERNAME
ENV USERNAME=${USERNAME}

WORKDIR /app
COPY --from=build /app/${APP_DIR}/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
