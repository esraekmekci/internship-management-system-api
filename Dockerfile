FROM openjdk:17-jdk-alpine
COPY internship-management-system-api-0.0.1-SNAPSHOT.jar ims-api.jar
ENTRYPOINT ["java","-jar","ims-api.jar"]