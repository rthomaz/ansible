# ansible-codes


cd /rthomaz

git clone git://github.com/rthomaz/ansible-codes.git

cd /rthomaz/ansible-codes/

vagrant up

ansible-playbook -i production site.yml 

ansible-playbook -i production domain-member-servers.yml 

ansible-playbook -i production docker-servers.yml 






