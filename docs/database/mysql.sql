create database fxmysql; -- Creates the new database
create user 'fxmysqluser'@'%' identified by 'fxmysqlpassword'; -- Creates the user
grant all on fxmysql.* to 'fxmysqluser'@'%'; -- Gives all privileges to the new user on the newly created database