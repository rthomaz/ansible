
locals {    
  jenkins_volume_source = "${var.docker_volumes_folder_path}/jenkins"  
  jenkins_fluentd_tag = "jenkins" 
}

resource "docker_container" "jenkins" {
  name  = "jenkins"
  image = "jenkins/jenkins:lts"
  restart = "always"
  count = 1

#    mounts {
#      target = "/var/jenkins_home"
#      source = "${local.jenkins_volume_source}"
#      type = "bind"
#    }

    ports {
        internal = "8080"
        external = "8080"
    }
    ports {
        internal = "50000"
        external = "50000"
    }
    

  networks_advanced {
    name = "${docker_network.private_network.name}"
  }

  # log_driver = "jenkins"
  # log_opts = {
  #   fluentd-address = "${var.fluentd_address}"
  #   tag = "${local.jenkins_fluentd_tag}"
  # }

  # provisioner "local-exec" {
  #   command = "ansible-playbook -i /rthomaz/ansible-codes/production /rthomaz/ansible-codes/docker-jenkins.yml"
  # }

}
