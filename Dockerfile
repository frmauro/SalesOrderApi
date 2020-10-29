FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} salesorder-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/salesorder-0.0.1-SNAPSHOT.jar"