FROM jenkins/jenkins:lts

LABEL maintainer="rodrigothomaz@msn.com"

COPY executors.groovy /usr/share/jenkins/ref/init.groovy.d/executors.groovy

COPY users/users.xml /usr/share/jenkins/ref/users/users.xml
COPY users/rodrigo_8564643631287127554-config.xml /usr/share/jenkins/ref/users/rodrigo_8564643631287127554/config.xml

COPY jobs/docker-image-influxdb-job-config.xml /usr/share/jenkins/ref/jobs/docker-image-influxdb-job/config.xml
COPY jobs/docker-image-grafana-job-config.xml /usr/share/jenkins/ref/jobs/docker-image-grafana-job/config.xml

COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
 RUN /usr/local/bin/install-plugins.sh </usr/share/jenkins/ref/plugins.txt

RUN echo 2.0 > /usr/share/jenkins/ref/jenkins.install.InstallUtil.lastExecVersion