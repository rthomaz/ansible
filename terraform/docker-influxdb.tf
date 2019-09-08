# Configure the Docker provider
provider "docker" {
  host = "tcp://127.0.0.1:2375/"
}

# Creates a docker volume "influxdbconfig".
resource "docker_volume" "influxdbconfig" {
  name = "influxdbconfig"
}

# create influxdb container
resource "docker_container" "influxdb" {
  name  = "influxdb-1"
  image = "influxdb"
  restart = "always"

  env = ["INFLUXDB_DB=openhab", "INFLUXDB_ADMIN_USER=openhab", "INFLUXDB_ADMIN_PASSWORD=openhab", "INFLUXDB_USER=openhab", "INFLUXDB_USER_PASSWORD=openhab"]

  mounts {
    target = "/var/lib/influxdb"
    source = "influxdbconfig"
    type = "volume"
  }

  ports {
    internal = 8086
    external = 8086
  }

  networks_advanced {
    name = "rthomaz-network"
  }

  log_driver = "fluentd"
  log_opts = {
    fluentd-address = "localhost:24224"
    tag = "influxdb"
  }

}