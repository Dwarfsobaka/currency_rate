FROM openjdk:14-jdk-alpine
ARG JAR_FILE=build/libs/currency-rate-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]