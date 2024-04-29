FROM openjdk:17
COPY library-server/target/library-server-0.0.1.jar /app/app.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "/app/app.jar"]