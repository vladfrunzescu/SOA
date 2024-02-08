# Build the JAR file with Maven
FROM maven:3.8.4-openjdk-17 AS build
RUN mkdir -p /java_organizer
WORKDIR /java_organizer
COPY pom.xml /java_organizer
COPY src /java_organizer/src
RUN mvn -f pom.xml clean package

# Use the JAR file to compile the project
FROM openjdk:17-jdk-slim
RUN mkdir -p /java_organizer
WORKDIR /java_organizer
COPY --from=build java_organizer/target/java_organizer-0.0.1-SNAPSHOT.jar java_organizer-0.0.1-SNAPSHOT.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","java_organizer-0.0.1-SNAPSHOT.jar"]
