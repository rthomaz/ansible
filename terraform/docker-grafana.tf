# create grafana container

locals {
  address = "localhost:24226"
  port    = 24224  
}

resource "docker_container" "grafana" {
  name  = "grafana"
  image = "grafana/grafana"
  restart = "always"
  count = 1

  user = "root"

  env = [
    "GF_SECURITY_ADMIN_PASSWORD=12345"
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
    fluentd-address = local.address
    tag = "grafana"
  }

}