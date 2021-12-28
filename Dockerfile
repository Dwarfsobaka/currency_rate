FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=build/libs/currency-rate-0.0.1-SNAPSHOT-plain.jar
COPY ${JAR_FILE} application.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/application.jar"]