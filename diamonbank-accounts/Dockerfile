#Start with a base image containing Java Runtime
FROM openjdk:11-slim as build

#Information around who maintains the image
MAINTAINER diamondcode

#Add the application's jar to the container
COPY target/diamonbank-accounts-1.0.0-SNAPSHOT.jar diamonbank-accounts.jar

#Execute the applitaion
ENTRYPOINT ["java", "-jar", "diamonbank-accounts.jar"]

