#Download docker image for mysql
docker pull mysql/mysql-server:latest

#Create custom docker network
docker create network fx-network

#To start container run the below command
docker run --name fx-mysql --net fx-network --detach -e MYSQL_ROOT_PASSWORD=mysqlrootpassword mysql/mysql-server:latest
