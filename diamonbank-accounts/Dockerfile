#Start with a base image containing Java Runtime
FROM openjdk:11-slim as build

#Information around who maintains the image
MAINTAINER diamondcode

#Add the application's jar to the container
COPY target/diamonbank-accounts.jar diamonbank-accounts.jar

#Execute the applitaion
ENTRYPOINT exec java $JAVA_OPTS -jar diamonbank-accounts.jar

