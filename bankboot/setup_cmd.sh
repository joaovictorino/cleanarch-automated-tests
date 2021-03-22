# run docker mysql
docker run -e MYSQL_ROOT_PASSWORD=123456 -e MYSQL_DATABASE=account -p 3306:3306 -d mysql

# run application
./mvnw clean spring-boot:run