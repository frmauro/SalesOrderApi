FROM adoptopenjdk:11-jre-hotspot
ADD target/salesorder-0.0.1-SNAPSHOT.jar salesorder-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENV ACTIVE_PROFILE=dev
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","salesorder-0.0.1-SNAPSHOT.jar"]