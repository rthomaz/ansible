FROM jenkins/jenkins:lts

LABEL maintainer="rodrigothomaz@msn.com"

COPY executors.groovy /usr/share/jenkins/ref/init.groovy.d/executors.groovy

COPY pushGitRepo.groovy /usr/share/jenkins/ref/init.groovy.d/pushGitRepo.groovy
COPY artifactoryGradleBuild.groovy /usr/share/jenkins/ref/init.groovy.d/artifactoryGradleBuild.groovy

COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
 RUN /usr/local/bin/install-plugins.sh </usr/share/jenkins/ref/plugins.txt

RUN echo 2.0 > /usr/share/jenkins/ref/jenkins.install.InstallUtil.lastExecVersion