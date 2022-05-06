# Spring Boot
Para criar projeto Spring Boot, usar Spring Initialzr

Baixar dependências do pom.xml
````sh
mvn install
````

Rodar MySQL no docker
````sh
docker run -e MYSQL_ROOT_PASSWORD=123456 -e MYSQL_DATABASE=account -p 3306:3306 -d mysql
````

Rodar a aplicação
````sh
./mvnw clean spring-boot:run
````
