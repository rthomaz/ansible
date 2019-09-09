
locals {  
  grafana_admin_password = "12345"
  grafana_port_internal = 3000
  grafana_port_external = 3000
  grafana_volume_source = "${var.docker_volumes_folder_path}/grafana"
  grafana_fluentd_tag = "grafana" 
}

resource "docker_container" "grafana" {
  name  = "grafana"
  image = "grafana/grafana"
  restart = "always"
  count = 1

  user = "root"

  env = [
    "GF_SECURITY_ADMIN_PASSWORD=${local.grafana_admin_password}"
  ]

  mounts {
    target = "/var/lib/grafana"
    source = "${local.grafana_volume_source}"
    type = "bind"
  }

  ports {
    internal = "${local.grafana_port_internal}"
    external = "${local.grafana_port_external}"
  }

  networks_advanced {
    name = "${var.docker_network}"
  }
  
  log_driver = "fluentd"
  log_opts = {
    fluentd-address = "${var.fluentd_address}"
    tag = "${local.grafana_fluentd_tag}"
  }

  provisioner "local-exec" {
    command = "ansible-playbook -i /rthomaz/ansible-codes/production /rthomaz/ansible-codes/docker-grafana.yml"
  }

}