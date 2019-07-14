# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure("2") do |config|

  config.ssh.insert_key = false

  config.vm.define "dc01" do |dc01|
    dc01.vm.box = "debian/stretch64"
    dc01.vm.hostname = "dc01"  
      dc01.vm.network :public_network, :dev => "br0", :mode => "bridge", :type => "bridge", ip: "192.168.1.110", :netmask => "255.255.255.0"
    dc01.vm.synced_folder '.', '/vagrant', disabled: true
    dc01.vm.provider :libvirt do |provider|
      provider.memory = 1024
    end
    dc01.vm.provision "ansible" do |ansible|
      ansible.verbose = ""  
#     ansible.verbose = "v"
      ansible.compatibility_mode  = "2.0"
      ansible.playbook = "domain-controller-servers.yml"
      ansible.inventory_path = "production"
      ansible.limit = "192.168.1.110"
    end
  end

  config.vm.define "dm01" do |dm01|
    dm01.vm.box = "debian/stretch64"
    dm01.vm.hostname = "dm01"  
      dm01.vm.network :public_network, :dev => "br0", :mode => "bridge", :type => "bridge", ip: "192.168.1.112", :netmask => "255.255.255.0"
    dm01.vm.synced_folder '.', '/vagrant', disabled: true
    dm01.vm.provider :libvirt do |provider|
      provider.memory = 2048
    end
    dm01.vm.provision "ansible" do |ansible|
      ansible.verbose = ""  
#     ansible.verbose = "v"      
      ansible.compatibility_mode  = "2.0"
      ansible.playbook = "domain-member-servers.yml"
      ansible.inventory_path = "production"
      ansible.limit = "192.168.1.112"
    end    
  end

#  VAGRANT_COMMAND = ARGV[0]
#  if VAGRANT_COMMAND == "ssh"
#    config.ssh.username = 'rodrigo'
#  end

#  config.vm.provision "shell", inline: <<-SHELL
#
#   apt-get update
#    
#    ## add rodrigo
#    useradd -m -s /bin/bash -U rodrigo -u 666 --groups sudo
#    cp -pr /home/vagrant/.ssh /home/rodrigo/
#    chown -R rodrigo:rodrigo /home/rodrigo
#    echo "%rodrigo ALL=(ALL) NOPASSWD: ALL" > /etc/sudoers.d/rodrigo
#
#  SHELL
  
end