# ansible-codes


cd /rthomaz

git clone git://github.com/rthomaz/ansible-codes.git

Cacheando suas credenciais

git config --global credential.helper wincred

cd /rthomaz/ansible-codes/

vagrant up

vagrant up dc01

ansible-playbook -i production site.yml 

ansible-playbook -i production domain-member-servers.yml 

ansible-playbook -i production docker-servers.yml 






