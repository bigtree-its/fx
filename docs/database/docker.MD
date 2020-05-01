# Docker commands
## MYSQL
### Start MYSQL Container
To start container run the below command
docker run --name fx-mysql --net fx-network --detach -e MYSQL_ROOT_PASSWORD=mysqlrootpassword mysql/mysql-server:latest

Inspect container
docker inspect <container-id>

Find IP address of mysql container
docker inspect <container-id> | grep -i 'ipaddress'

Execute process on container
docker exec -it <container-id> mysql -uroot -p
