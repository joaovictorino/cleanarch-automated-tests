#run docker mysql
docker run --name some-mysql -e MYSQL_ROOT_PASSWORD=123456 -p 3306:3306 -d mysql

#connect mysql on azure
mysql -h accounttest.mysql.database.azure.com -u joao@accounttest -p

#get ip
curl http://ipinfo.io

#run application
./mvnw spring-boot:run