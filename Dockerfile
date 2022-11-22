FROM maven:3.8.6-jdk-11-slim
COPY . .
RUN mvn install -DskipTests
ARG JAR_FILE=target/*.jar
RUN cp ${JAR_FILE} app.jar
ENTRYPOINT [ "java", "-jar", "app.jar"]