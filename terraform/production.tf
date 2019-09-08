# Configure the Docker provider
provider "docker" {
  host = "tcp://127.0.0.1:2375/"
}

# create db container
resource "docker_container" "db" {
  name  = "db"
  image = "mysql:5.7"
  restart = "always"
}

# create wordpress container
resource "docker_container" "wordpress" {
  name  = "wordpress"
  image = "wordpress:latest"
  restart = "always"
}
