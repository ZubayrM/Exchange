FROM openjdk:8
EXPOSE 80
ADD target/Exchange-1.0-SNAPSHOT.jar Exchange-1.0-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/Exchange-1.0-SNAPSHOT.jar"]