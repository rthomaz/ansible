# create influxdb container

resource "docker_container" "influxdb" {
  name  = "influxdb"
  image = "influxdb"
  restart = "always"
  count = 1

  env = [
    "INFLUXDB_DB=openhab", 
    "INFLUXDB_ADMIN_USER=openhab", 
    "INFLUXDB_ADMIN_PASSWORD=openhab", 
    "INFLUXDB_USER=openhab", 
    "INFLUXDB_USER_PASSWORD=openhab"
  ]

  mounts {
    target = "/var/lib/influxdb"
    source = "/rthomaz/docker-projects/volumes/influxdb"
    type = "bind"
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