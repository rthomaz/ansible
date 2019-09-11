FROM jenkins/jenkins:lts

LABEL maintainer="rodrigothomaz@msn.com"

# jenkins

COPY jenkins_home/config.xml /usr/share/jenkins/ref/config.xml
COPY jenkins_home/jenkins.model.JenkinsLocationConfiguration.xml /usr/share/jenkins/ref/jenkins.model.JenkinsLocationConfiguration.xml

COPY jenkins_home/init.groovy.d/executors.groovy /usr/share/jenkins/ref/init.groovy.d/executors.groovy

COPY jenkins_home/users/users.xml /usr/share/jenkins/ref/users/users.xml
COPY jenkins_home/users/rodrigo_8564643631287127554-config.xml /usr/share/jenkins/ref/users/rodrigo_8564643631287127554/config.xml

COPY jenkins_home/jobs/docker-image-influxdb-job-config.xml /usr/share/jenkins/ref/jobs/docker-image-influxdb-job/config.xml
COPY jenkins_home/jobs/docker-image-grafana-job-config.xml /usr/share/jenkins/ref/jobs/docker-image-grafana-job/config.xml

COPY jenkins_home/plugins.txt /usr/share/jenkins/ref/plugins.txt
 RUN /usr/local/bin/install-plugins.sh </usr/share/jenkins/ref/plugins.txt

RUN echo 2.0 > /usr/share/jenkins/ref/jenkins.install.InstallUtil.lastExecVersion

# ansible

RUN echo "===> Preparing Ansible installation..."                                           && \
    apt-get update                                                                              && \
    \                               
    \                               
    echo "Installing dependencies..."                                                       && \
    apt install dirmngr                                                                     && \
    \                               
    \                               
    echo "Configuring apt source list..."                                                   && \
    add-apt-repository "deb http://ppa.launchpad.net/ansible/ansible/ubuntu trusty main"    && \
    \                    
    \                    
    echo "Inserting key in apt for Ansible..."                                              && \
    apt-key adv --keyserver keyserver.ubuntu.com --recv-keys 93C4A3FD7BB9C367               && \
    \           
    \           
    echo "Updating apt..."                                                                  && \
    apt update                                                                              && \
    apt upgrade                                                                             && \
    \                               
    \                               
    echo "Installing Ansible..."                                                            && \
    apt install ansible 