FROM ubuntu:latest

RUN apt-get update && apt-get install -y openjdk-11-jre-headless

WORKDIR /app
LABEL authors="Praveen"

COPY target/microservice-0.0.1-SNAPSHOT.jar /app/user-management-service.jar

EXPOSE 9001

CMD ["java", "-jar", "user-management-service.jar"]