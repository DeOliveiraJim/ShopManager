FROM maven:3.8.4-jdk-11

COPY target/shop-manager-0.0.1-SNAPSHOT.jar /shop-manager-0.0.1-SNAPSHOT.jar

ENTRYPOINT [ "java", "-jar", "/shop-manager-0.0.1-SNAPSHOT.jar"]