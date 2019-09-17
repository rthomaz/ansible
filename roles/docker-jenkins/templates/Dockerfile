FROM jenkins/jenkins:lts

LABEL maintainer="rodrigothomaz@msn.com"

# jenkins

COPY jenkins_home/config.xml /usr/share/jenkins/ref/config.xml
COPY jenkins_home/jenkins.model.JenkinsLocationConfiguration.xml /usr/share/jenkins/ref/jenkins.model.JenkinsLocationConfiguration.xml
COPY jenkins_home/org.jenkinsci.plugins.pipeline.modeldefinition.config.GlobalConfig.xml /usr/share/jenkins/ref/org.jenkinsci.plugins.pipeline.modeldefinition.config.GlobalConfig.xml

COPY jenkins_home/init.groovy.d/executors.groovy /usr/share/jenkins/ref/init.groovy.d/executors.groovy

COPY jenkins_home/init.groovy.d/credentials.groovy /usr/share/jenkins/ref/init.groovy.d/credentials.groovy

# --------------------------------------------------------------------------------

# SSH

USER root

# Authorize SSH Host
RUN mkdir -p jenkins_home/.ssh && \
    chmod 0700 jenkins_home/.ssh && \
    ssh-keyscan 192.168.1.5 > jenkins_home/.ssh/known_hosts

# Add the keys and set permissions
RUN echo jenkins_home/.ssh/id_rsa > jenkins_home/.ssh/id_rsa && \
    echo jenkins_home/.ssh/id_rsa.pub > jenkins_home/.ssh/id_rsa.pub && \
    chmod 600 jenkins_home/.ssh/id_rsa && \
    chmod 600 jenkins_home/.ssh/id_rsa.pub

RUN ssh-add

#RUN mkdir -p ~/.ssh 

#COPY jenkins_home/.ssh/id_rsa jenkins_home/.ssh/id_rsa
#COPY jenkins_home/.ssh/id_rsa.pub jenkins_home/.ssh/id_rsa.pub
# RUN cat jenkins_home/.ssh/id_rsa.pub >> jenkins_home/.ssh/authorized_keys

#RUN chown jenkins:jenkins jenkins_home/.ssh/id_rsa
#RUN chown jenkins:jenkins jenkins_home/.ssh/id_rsa.pub
#RUN chown jenkins:jenkins jenkins_home/.ssh/authorized_keys

#RUN chmod 700 jenkins_home/.ssh
#RUN chmod 400 jenkins_home/.ssh/id_rsa
#RUN chmod 644 jenkins_home/.ssh/id_rsa.pub
#RUN chmod 644 jenkins_home/.ssh/authorized_keys

USER jenkins 

#RUN eval `ssh-agent -s`
#RUN ssh-add

# --------------------------------------------------------------------------------


COPY jenkins_home/users/users.xml /usr/share/jenkins/ref/users/users.xml
COPY jenkins_home/users/rodrigo_8564643631287127554-config.xml /usr/share/jenkins/ref/users/rodrigo_8564643631287127554/config.xml

COPY jenkins_home/nodes/docker01-node/config.xml /usr/share/jenkins/ref/nodes/docker01-node/config.xml

COPY jenkins_home/jobs/elasticsearch/docker-elasticsearch-ci.config.xml /usr/share/jenkins/ref/jobs/docker-elasticsearch-ci/config.xml
COPY jenkins_home/jobs/elasticsearch/docker-elasticsearch-cd.config.xml /usr/share/jenkins/ref/jobs/docker-elasticsearch-cd/config.xml

COPY jenkins_home/jobs/fluentd/docker-fluentd-ci.config.xml /usr/share/jenkins/ref/jobs/docker-fluentd-ci/config.xml
COPY jenkins_home/jobs/fluentd/docker-fluentd-cd.config.xml /usr/share/jenkins/ref/jobs/docker-fluentd-cd/config.xml

COPY jenkins_home/jobs/grafana/docker-grafana-ci.config.xml /usr/share/jenkins/ref/jobs/docker-grafana-ci/config.xml
COPY jenkins_home/jobs/grafana/docker-grafana-cd.config.xml /usr/share/jenkins/ref/jobs/docker-grafana-cd/config.xml

COPY jenkins_home/jobs/influxdb/docker-influxdb-ci.config.xml /usr/share/jenkins/ref/jobs/docker-influxdb-ci/config.xml
COPY jenkins_home/jobs/influxdb/docker-influxdb-cd.config.xml /usr/share/jenkins/ref/jobs/docker-influxdb-cd/config.xml

COPY jenkins_home/jobs/jenkins/docker-jenkins-ci.config.xml /usr/share/jenkins/ref/jobs/docker-jenkins-ci/config.xml
COPY jenkins_home/jobs/jenkins/docker-jenkins-cd.config.xml /usr/share/jenkins/ref/jobs/docker-jenkins-cd/config.xml

COPY jenkins_home/jobs/kibana/docker-kibana-ci.config.xml /usr/share/jenkins/ref/jobs/docker-kibana-ci/config.xml
COPY jenkins_home/jobs/kibana/docker-kibana-cd.config.xml /usr/share/jenkins/ref/jobs/docker-kibana-cd/config.xml

COPY jenkins_home/jobs/nexus/docker-nexus-ci.config.xml /usr/share/jenkins/ref/jobs/docker-nexus-ci/config.xml
COPY jenkins_home/jobs/nexus/docker-nexus-cd.config.xml /usr/share/jenkins/ref/jobs/docker-nexus-cd/config.xml

COPY jenkins_home/jobs/rabbitmq/docker-rabbitmq-ci.config.xml /usr/share/jenkins/ref/jobs/docker-rabbitmq-ci/config.xml
COPY jenkins_home/jobs/rabbitmq/docker-rabbitmq-cd.config.xml /usr/share/jenkins/ref/jobs/docker-rabbitmq-cd/config.xml

COPY jenkins_home/plugins.txt /usr/share/jenkins/ref/plugins.txt
 RUN /usr/local/bin/install-plugins.sh </usr/share/jenkins/ref/plugins.txt

RUN echo 2.0 > /usr/share/jenkins/ref/jenkins.install.InstallUtil.lastExecVersion


# plugins

USER root    

ENV TERRAFORM_VERSION 0.12.8
ENV PACKER_VERSION 1.4.3

# Install Dependencies
RUN  echo "===> Installing Dependencies..." \
 && apt-get update \   
 && apt-get install -y \
    wget unzip

# Install Packer
# RUN echo "===> Installing Packer ${PACKER_VERSION} ..." \
#  && wget https://releases.hashicorp.com/packer/${PACKER_VERSION}/packer_${PACKER_VERSION}_linux_amd64.zip \
#  && unzip packer_${PACKER_VERSION}_linux_amd64.zip  \
#  && mv packer /usr/local/bin/packer \
#  && rm packer_${PACKER_VERSION}_linux_amd64.zip 

# Install Terraform
# RUN echo "===> Installing Terraform ${TERRAFORM_VERSION}..." \
#  && wget https://releases.hashicorp.com/terraform/${TERRAFORM_VERSION}/terraform_${TERRAFORM_VERSION}_linux_amd64.zip \
#  &&	unzip terraform_${TERRAFORM_VERSION}_linux_amd64.zip \
#  && mv terraform /usr/local/bin/terraform \
#  && rm terraform_${TERRAFORM_VERSION}_linux_amd64.zip 
	
# # Install Ansible
# RUN  echo "===> Installing Ansible..." \
#  && apt-get update \   
#  && apt-get install -y --no-install-recommends \
#     ansible \      
#  && rm -rf /var/lib/apt/lists/*      \ 
#  && echo "===> Adding hosts for convenience..." \
#  && mkdir -p /etc/ansible                       \
#  && echo 'localhost' > /etc/ansible/hosts

 
# Install Ansible
# RUN echo "===> Preparing Ansible installation..."                                                   
# RUN apt-get update && apt-get upgrade -y
# RUN echo "Installing dependencies..."  
# RUN apt-get install -y dirmngr                                                                     
# RUN echo "Configuring apt source list..."                                                           
# RUN echo 'deb http://ppa.launchpad.net/ansible/ansible/ubuntu trusty main' >> /etc/apt/sources.list 
# RUN echo "Inserting key in apt for Ansible..."                                                      
# RUN apt-key adv --keyserver keyserver.ubuntu.com --recv-keys 93C4A3FD7BB9C367                       
# RUN echo "Updating apt..."                                                                          
# RUN apt-get update && apt-get upgrade -y
# RUN echo "Installing Ansible..."                                                                    
# RUN apt-get install -y ansible 

# Install Docker CE

# RUN echo "===> Preparing Docker CE installation..."                                                   
# RUN apt-get install -y apt-transport-https ca-certificates curl gnupg2 software-properties-common
# RUN echo "Adicionar chave GPG oficial do Docker..."  
# RUN curl -fsSL https://download.docker.com/linux/debian/gpg | apt-key add -           
# RUN echo "configurar o repositório estável..."  
# RUN add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/debian $(lsb_release -cs) stable"
# RUN echo "Atualizar apt..."  
# RUN apt-get update        
# RUN echo "Instalar Docker..."  
# RUN apt-get install -y docker-ce          


USER jenkins    

#RUN eval `ssh-agent -s`
#RUN ssh-add