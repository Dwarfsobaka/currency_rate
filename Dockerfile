FROM openjdk:14-jdk-alpine
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} application.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/application.jar"]