
locals {  
  influxdb_db = "openhab"
  influxdb_admin_user = "openhab"
  influxdb_admin_password = "openhab"
  influxdb_user = "openhab"
  influxdb_user_password = "openhab"
  influxdb_port_internal = 8086
  influxdb_port_external = 8086
  influxdb_volume_source = "${var.docker_volumes_folder_path}/influxdb"  
  influxdb_fluentd_tag = "influxdb" 
}

resource "docker_container" "influxdb" {
  name  = "influxdb"
  image = "${docker_image.influxdb.latest}"
  restart = "always"
  count = 1

  env = [
    "INFLUXDB_DB=${local.influxdb_db}", 
    "INFLUXDB_ADMIN_USER=${local.influxdb_admin_user}", 
    "INFLUXDB_ADMIN_PASSWORD=${local.influxdb_admin_password}", 
    "INFLUXDB_USER=${local.influxdb_user}", 
    "INFLUXDB_USER_PASSWORD=${local.influxdb_user_password}"
  ]

  mounts {
    target = "/var/lib/influxdb"
    source = "${local.influxdb_volume_source}"
    type = "bind"
  }

  ports {
    internal = "${local.influxdb_port_internal}"
    external = "${local.influxdb_port_external}"
  }

  networks_advanced {
    name = "${var.docker_network}"
  }

  log_driver = "fluentd"
  log_opts = {
    fluentd-address = "${var.fluentd_address}"
    tag = "${local.influxdb_fluentd_tag}"
  }

  provisioner "local-exec" {
    command = "sleep 20; ansible-playbook -i /rthomaz/ansible-codes/production /rthomaz/ansible-codes/grafana.yml"
  }

}

resource "docker_image" "influxdb" {
  name = "influxdb"
}