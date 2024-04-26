FROM openjdk:17
COPY eureka-server/target/eureka-server-0.0.1.jar /app/app.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "/app/app.jar"]