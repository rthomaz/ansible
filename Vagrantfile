# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure("2") do |config|
  
  config.vm.box = "debian/stretch64"
  config.vm.hostname = "deb-01"  
    config.vm.network :public_network, :dev => "br0", :mode => "bridge", :type => "bridge", ip: "192.168.1.112", :netmask => "255.255.255.0"
  config.vm.synced_folder '.', '/vagrant', disabled: true

  config.ssh.insert_key = false
    
  config.vm.provider :libvirt do |provider|
    provider.memory = 2048
  end

#  VAGRANT_COMMAND = ARGV[0]
#  if VAGRANT_COMMAND == "ssh"
#    config.ssh.username = 'rodrigo'
#  end

  config.vm.provision "shell", inline: <<-SHELL

#   apt-get update
    
#    ## add rodrigo
#    useradd -m -s /bin/bash -U rodrigo -u 666 --groups sudo
#    cp -pr /home/vagrant/.ssh /home/rodrigo/
#    chown -R rodrigo:rodrigo /home/rodrigo
#    echo "%rodrigo ALL=(ALL) NOPASSWD: ALL" > /etc/sudoers.d/rodrigo

  SHELL

  config.vm.provision "ansible" do |ansible|
    ansible.verbose = ""
#   ansible.verbose = "v"
#   ansible.verbose = "vvvv"
    ansible.compatibility_mode  = "2.0"
    ansible.playbook = "domain-member-servers.yml"
    ansible.inventory_path = "production"
    ansible.limit = "192.168.1.112"
  end
  
end