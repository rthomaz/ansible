FROM jenkins/jenkins:lts

LABEL maintainer="rodrigothomaz@msn.com"

# jenkins

COPY jenkins_home/config.xml /usr/share/jenkins/ref/config.xml
COPY jenkins_home/jenkins.model.JenkinsLocationConfiguration.xml /usr/share/jenkins/ref/jenkins.model.JenkinsLocationConfiguration.xml

COPY jenkins_home/init.groovy.d/executors.groovy /usr/share/jenkins/ref/init.groovy.d/executors.groovy

COPY jenkins_home/users/users.xml /usr/share/jenkins/ref/users/users.xml
COPY jenkins_home/users/rodrigo_8564643631287127554-config.xml /usr/share/jenkins/ref/users/rodrigo_8564643631287127554/config.xml

COPY jenkins_home/jobs/docker-image-influxdb/docker-image-influxdb-job-config.xml /usr/share/jenkins/ref/jobs/docker-image-influxdb-job/config.xml
COPY jenkins_home/jobs/docker-image-influxdb/Jenkinsfile /usr/share/jenkins/ref/jobs/docker-image-influxdb-job/workspace@script/Jenkinsfile

COPY jenkins_home/jobs/docker-image-grafana/docker-image-grafana-job-config.xml /usr/share/jenkins/ref/jobs/docker-image-grafana-job/config.xml
COPY jenkins_home/jobs/docker-image-grafana/Jenkinsfile /usr/share/jenkins/ref/jobs/docker-image-grafana-job/workspace@script/Jenkinsfile

COPY jenkins_home/plugins.txt /usr/share/jenkins/ref/plugins.txt
 RUN /usr/local/bin/install-plugins.sh </usr/share/jenkins/ref/plugins.txt

RUN echo 2.0 > /usr/share/jenkins/ref/jenkins.install.InstallUtil.lastExecVersion

# ansible

USER root

RUN echo "===> Preparing Ansible installation..."                                                   
RUN apt-get update && apt-get upgrade -y

RUN echo "Installing dependencies..."  
RUN apt-get install -y dirmngr                                                                     

RUN echo "Configuring apt source list..."                                                           
RUN echo 'deb http://ppa.launchpad.net/ansible/ansible/ubuntu trusty main' >> /etc/apt/sources.list 

RUN echo "Inserting key in apt for Ansible..."                                                      
RUN apt-key adv --keyserver keyserver.ubuntu.com --recv-keys 93C4A3FD7BB9C367                       

RUN echo "Updating apt..."                                                                          
RUN apt-get update && apt-get upgrade -y

RUN echo "Installing Ansible..."                                                                    
RUN apt-get install -y ansible 

USER jenkins    