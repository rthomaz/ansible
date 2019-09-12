
locals {  
  azure_pipeline_agent_custom_account = "rthomaz"
  azure_pipeline_agent_custom_token = "7yiyoxrttnmme5tccxm36xbzylleqagj7vef5xkvngh7vfhplo3q"
  azure_pipeline_agent_custom_name = "docker-azure-pipeline-agent-custom"
  azure_pipeline_agent_custom_pool = "RThomaz"
  azure_pipeline_agent_custom_work = "/var/vsts/$VSTS_AGENT"
  azure_pipeline_agent_custom_volume_source = "${var.docker_volumes_folder_path}/azure_pipeline_agent"  
  azure_pipeline_agent_custom_fluentd_tag = "azure-pipeline-agent" 
}

resource "docker_container" "azure_pipeline_agent_custom" {
  name  = "azure_pipeline_agent_custom"
  image = "rthomaz/docker-azure-pipeline-agent-custom:0.0.1"
  restart = "always"
  count = 1

  env = [
    "VSTS_ACCOUNT=${local.azure_pipeline_agent_custom_account}", 
    "VSTS_TOKEN=${local.azure_pipeline_agent_custom_token}", 
    "VSTS_AGENT=${local.azure_pipeline_agent_custom_name}", 
    "VSTS_POOL=${local.azure_pipeline_agent_custom_pool}", 
    "VSTS_WORK=${local.azure_pipeline_agent_custom_work}"
  ]

#   mounts {
#     target = "/var/vsts"
#     source = "${local.azure_pipeline_agent_custom_volume_source}"
#     type = "bind"
#   }

  networks_advanced {
    name = "${docker_network.private_network.name}"
  }

  # log_driver = "fluentd"
  # log_opts = {
  #   fluentd-address = "${var.fluentd_address}"
  #   tag = "${local.azure_pipeline_agent_custom_fluentd_tag}"
  # }

  # provisioner "local-exec" {
  #   command = "ansible-playbook -i /rthomaz/ansible-codes/production /rthomaz/ansible-codes/docker-azure-pipeline-agent-custom.yml"
  # }

}
