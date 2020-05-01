# fx

#### RUN MYSQL Docker
docker pull mysql/mysql-server:latest
docker run --name mysql -d mysql/mysql-server:latest

# MYSQL LOGS 
docker logs mysql

-- You can find generated Password copy this to use later

# DOCKER STOP MYSQL
docker stop/kill <container-id>

# DOCKER SET ROOT PASSWORD
docker exex -it <container-name> mysql -uroot -p
--enter the password copied from container log

>ALTER USER 'root'@'localhost' IDENTIFIED BY 'newpassword';
>exit