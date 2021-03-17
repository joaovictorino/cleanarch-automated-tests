# create quarkus project
mvn io.quarkus:quarkus-maven-plugin:1.12.2.Final:create \
    -DprojectGroupId=org.acme \
    -DprojectArtifactId=getting-started \
    -DclassName="org.acme.getting.started.GreetingResource" \
    -Dpath="/hello"

# run database mysql
docker run --name some-mongodb -p 27017:27017 -d mongo

# run application
./mvnw compile quarkus:dev