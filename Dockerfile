FROM openjdk:8-alpine
EXPOSE 8085
COPY /target/user-management-0.0.1-SNAPSHOT.jar CamUserMagmt.jar
ENTRYPOINT ["java","-jar","/CamUserMagmt.jar"]