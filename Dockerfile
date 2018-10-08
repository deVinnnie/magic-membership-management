FROM openjdk:8-jdk-alpine
COPY ./target/administration-1.0-SNAPSHOT.jar /app.jar

EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=production","-jar","/app.jar"]