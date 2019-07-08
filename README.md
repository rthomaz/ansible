# ansible-codes


cd /rthomaz

git clone git://github.com/rthomaz/ansible-codes.git

cd /rthomaz/ansible-codes/

vagrant up

ansible-playbook -i hosts site.yml 

ansible-playbook -i hosts domain-member-servers.yml 

ansible-playbook -i hosts docker-servers.yml 






