#create project
mvn org.apache.maven.plugins:maven-archetype-plugin:3.1.2:generate -DarchetypeArtifactId="maven-archetype-quickstart" -DarchetypeGroupId="org.apache.maven.archetypes" -DarchetypeVersion="1.4" -DgroupId="com.bank.account.restassured" -DartifactId="restassured"

#download dependencies
mvn install

#generate jar
mvn package

#clean build files
mvn clean 

#run tests
mvn test