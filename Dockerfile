FROM openjdk:17-jdk-slim

COPY target/customer-management-0.0.1-SNAPSHOT.jar /app/cms.jar

ENTRYPOINT ["java", "-jar", "/app/cms.jar"]