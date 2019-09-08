# Configure the Docker provider
provider "docker" {
  host = "tcp://127.0.0.1:2375/"
}

# create influxdb container
resource "docker_container" "influxdb" {
  name  = "influxdb-2"
  image = "influxdb"
  restart = "always"
  mounts = {
    target = "/var/lib/influxdb"
    source = "influxdbconfig"
    type = "bind"
  }
}

# create wordpress container
resource "docker_container" "wordpress" {
  name  = "wordpress"
  image = "wordpress:latest"
  restart = "always"
}
