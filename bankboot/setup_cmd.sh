# run docker mysql
docker run --name some-mysql -e MYSQL_ROOT_PASSWORD=123456 -p 3306:3306 -d mysql

# run application
./mvnw spring-boot:run