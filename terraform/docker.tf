# Configure the Docker provider

provider "docker" {
  host = "tcp://127.0.0.1:2375/"
}

# Configure the Docker network

resource "docker_network" "private_network" {
  name = "rthomaz-network"
}

# Configure the Docker volume

variable "docker_volumes_folder_path" {
  type = string
  default = "/rthomaz/docker-projects/volumes"
}

