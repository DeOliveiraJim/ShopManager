FROM maven:3.8.6-jdk-11-slim
COPY . .
RUN mvn install
ARG JAR_FILE=target/*.jar
RUN mv ${JAR_FILE} target/app.jar
ENTRYPOINT [ "java", "-jar", "target/app.jar"]