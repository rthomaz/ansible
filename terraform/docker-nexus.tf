
locals {  
  nexus_port_internal = 8081
  nexus_port_external = 8081
  nexus_volume_source = "${var.docker_volumes_folder_path}/nexus"
  nexus_fluentd_tag = "nexus" 
}

resource "docker_container" "nexus" {
  name  = "nexus"
  image = "sonatype/nexus3"
  restart = "always"
  count = 1

  mounts {
    target = "/nexus-data"
    source = "${local.nexus_volume_source}"
    type = "bind"
  }

  ports {
    internal = "${local.nexus_port_internal}"
    external = "${local.nexus_port_external}"
  }

  networks_advanced {
    name = "${docker_network.private_network.name}"
  }
  
  # log_driver = "fluentd"
  # log_opts = {
  #   fluentd-address = "${var.fluentd_address}"
  #   tag = "${local.nexus_fluentd_tag}"
  # }

  # provisioner "local-exec" {
  #   command = "ansible-playbook -i /rthomaz/ansible-codes/production /rthomaz/ansible-codes/docker-nexus.yml"
  # }

}