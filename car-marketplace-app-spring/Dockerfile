FROM bellsoft/liberica-openjdk-alpine:17
COPY build/libs/car-marketplace-app-spring-1.0-SNAPSHOT-plain.jar /app/app.jar

ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-jar", "/app/app.jar"]
