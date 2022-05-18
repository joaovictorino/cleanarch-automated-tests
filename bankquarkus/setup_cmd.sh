# create quarkus project
mvn io.quarkus:quarkus-maven-plugin:1.12.2.Final:create -DprojectGroupId="com.bank.service.quarkus" -DprojectArtifactId="bankquarkus" -DclassName="com.bank.service.quarkus.controller.AccountResource"

# run database mysql
docker run -p 27017:27017 -d mongo

# run application
./mvnw compile quarkus:dev

# sub modules
./mvnw install -pl bankquarkus -am && ./mvnw quarkus:dev -pl bankquarkus