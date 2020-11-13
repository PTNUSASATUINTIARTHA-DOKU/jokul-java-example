FROM docker.doku.com/doku-jdk11-centos7

ARG project_name=devex-demo-java-lib
ARG application_name=$project_name.jar

RUN echo $project_name
RUN echo $application_name


#-------------------------------------------------------------
# Prepare file and application
#-------------------------------------------------------------
RUN mkdir -p /apps/devex/$project_name/
RUN mkdir -p /apps/devex/_temp/application-log

ADD docker-cmd.sh /apps/devex/$project_name/
ADD target/deploy/$application_name /apps/devex/$project_name/application.jar

#-------------------------------------------------------------
# Set Permission in OS
#-------------------------------------------------------------
USER root
RUN chown -R 3000:3000 /apps/devex/$project_name
RUN cd /apps && chmod +rwx devex/$project_name/application.jar && chmod +rx devex/$project_name && chmod +rx devex/$project_name/docker-cmd.sh

#-------------------------------------------------------------
# Set default working dir 
#-------------------------------------------------------------
WORKDIR	 /apps/devex/$project_name
EXPOSE 8080
USER 3000


CMD ./docker-cmd.sh