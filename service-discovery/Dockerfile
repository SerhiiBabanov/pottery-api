FROM bellsoft/liberica-openjdk-alpine:17-x86_64 AS build
WORKDIR /app
COPY . .
RUN ./gradlew build -x test

FROM bellsoft/liberica-openjdk-alpine:17-x86_64
MAINTAINER Serhii Babanov <serhii.babanov@gmail.com>
COPY --from=build /app/build/libs/*.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
