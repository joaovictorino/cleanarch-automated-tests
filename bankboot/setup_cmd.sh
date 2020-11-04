#run docker mysql
docker run --name some-mysql -e MYSQL_ROOT_PASSWORD=123456 -p 3306:3306 -d mysql

#connect mysql on azure
mysql -h ordertest.mysql.database.azure.com -u joao@ordertest -p

#get ip
curl http://ipinfo.io

#run application
./mvnw spring-boot:run