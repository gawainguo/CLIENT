FROM openjdk:12
COPY target/*.jar client.jar
ENTRYPOINT ["java", "-jar", "/client.jar"]