# Linux based image with OpenJDK JRE only
FROM openjdk:8-jre-alpine

# Use user privilege (not root)
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# copy executable jar in container
COPY target/*.jar app.jar

# spring boot app listens on port 8080
EXPOSE 8080

# entry point : invoke java jar app
ENTRYPOINT ["java","-jar","/app.jar"]