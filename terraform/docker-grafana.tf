# create grafana container

variable "grafana_admin_password" {
  type = string
  default = "12345"
}

locals {
  fluentd_address = "localhost:24224"
  grafana_admin_password = "12345"
}

resource "docker_container" "grafana" {
  name  = "grafana"
  image = "grafana/grafana"
  restart = "always"
  count = 1

  user = "root"

  env = [
    "GF_SECURITY_ADMIN_PASSWORD=${var.grafana_admin_password}"
  ]

  mounts {
    target = "/var/lib/grafana"
    source = "/rthomaz/docker-projects/volumes/grafana"
    type = "bind"
  }

  ports {
    internal = 3000
    external = 3000
  }

  networks_advanced {
    name = "rthomaz-network"
  }
  
  log_driver = "fluentd"
  log_opts = {
    fluentd-address = "${local.fluentd_address}"
    tag = "grafana"
  }

}