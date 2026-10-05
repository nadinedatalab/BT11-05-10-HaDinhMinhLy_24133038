# Build stage
FROM maven:3.8.6-jdk-11 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Run stage
FROM tomcat:9.0-jdk11-corretto
COPY --from=build /app/target/TestQT_60-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/TestQT_60.war
EXPOSE 8080
CMD ["catalina.sh", "run"]
