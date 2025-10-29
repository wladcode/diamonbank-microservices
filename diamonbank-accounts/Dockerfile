# Variables
ARG builder_image=openjdk:17-slim
ARG runtimer_image=openjdk:17-slim

FROM ${builder_image} as builder
WORKDIR /home
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM ${runtimer_image} AS runtime
ARG home_path=/home/diamoncode
RUN mkdir $home_path


RUN echo 'Creating group'
RUN groupadd -r gp_diamoncode
RUN echo 'Adding user to group in the directory, using shell and with a comment'
RUN useradd -r -g gp_diamoncode usr_diamoncode
RUN echo 'Adding OWNERSHIP'
RUN chown usr_diamoncode:gp_diamoncode $home_path


WORKDIR $home_path

USER usr_diamoncode
COPY --chown=usr_diamoncode:gp_diamoncode run_app.sh .
COPY --from=builder --chown=usr_diamoncode:gp_diamoncode home/dependencies/ ./
COPY --from=builder --chown=usr_diamoncode:gp_diamoncode home/snapshot-dependencies/ ./
COPY --from=builder --chown=usr_diamoncode:gp_diamoncode home/spring-boot-loader/ ./
COPY --from=builder --chown=usr_diamoncode:gp_diamoncode home/application/ ./

ENTRYPOINT sh run_app.sh


