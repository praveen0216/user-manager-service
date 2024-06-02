FROM eclipse-temurin:17-jdk-alpine

VOLUME /tmp

LABEL authors="Praveen"

COPY target/*.jar user-management-service.jar

EXPOSE 9001

CMD ["java", "-jar", "user-management-service.jar"]


