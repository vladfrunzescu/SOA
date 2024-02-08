# Build the JAR file with Maven
FROM maven:3.8.4-openjdk-17 AS build
RUN mkdir -p /java_grading
WORKDIR /java_grading
COPY pom.xml /java_grading
COPY src /java_grading/src
RUN mvn -f pom.xml clean package

# Use the JAR file to compile the project
FROM openjdk:17-jdk-slim
RUN mkdir -p /java_grading
WORKDIR /java_grading
COPY --from=build java_grading/target/java_grading-0.0.1-SNAPSHOT.jar java_grading-0.0.1-SNAPSHOT.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","java_grading-0.0.1-SNAPSHOT.jar"]
