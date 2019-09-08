# Configure the Docker provider
provider "docker" {
  host = "tcp://127.0.0.1:2375/"
}

# Creates a docker volume "grafanaconfig".
resource "docker_volume" "grafanaconfig" {
  name = "grafanaconfig"
}

# create grafana container
resource "docker_container" "grafana" {
  name  = "grafana-1"
  image = "grafana/grafana"
  restart = "always"

  env = ["GF_SECURITY_ADMIN_PASSWORD=12345"]

  mounts {
    target = "/var/lib/grafana"
    source = "grafanaconfig"
    type = "volume"
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
    fluentd-address = "localhost:24224"
    tag = "grafana"
  }

}