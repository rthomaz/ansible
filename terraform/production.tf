# Configure the Docker provider
provider "docker" {
  host = "tcp://127.0.0.1:2375/"
}

# create wordpress container
resource "docker_container" "wordpress" {
  name  = "wordpress"
  image = "wordpress:latest"
  restart = "always"
}
