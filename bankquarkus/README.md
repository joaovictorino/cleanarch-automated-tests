# Quarkus

Criar o projeto usando Maven
````sh
mvn io.quarkus:quarkus-maven-plugin:1.12.2.Final:create -DprojectGroupId="com.bank.service.quarkus" -DprojectArtifactId="bankquarkus" -DclassName="com.bank.service.quarkus.controller.AccountResource"
````

Rodar o banco de dados MongoDB com Docker
````sh
docker run -p 27017:27017 -d mongo
````

Rodar a aplicação
````sh
./mvnw install -pl bankquarkus -am && ./mvnw quarkus:dev -pl bankquarkus
````
