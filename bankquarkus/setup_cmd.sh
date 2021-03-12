# run database mysql
docker run --name some-mongodb -p 27017:27017 -d mongo

# run application
./mvnw compile quarkus:dev