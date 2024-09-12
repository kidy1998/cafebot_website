FROM openjdk:17-jdk
ARG JAR_FILE=build/libs/*.war
COPY ${JAR_FILE} app.war
ENTRYPOINT ["java","-Dspring.profiles.active=docker", "-jar", "app.war"]

