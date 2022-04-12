#create project
mvn org.apache.maven.plugins:maven-archetype-plugin:3.1.2:generate -DarchetypeArtifactId="maven-archetype-quickstart" -DarchetypeGroupId="org.apache.maven.archetypes" -DarchetypeVersion="1.4" -DgroupId="com.bank.account" -DartifactId="bank"

#download dependencies
mvn install

#generate jar
mvn package

#clean build files
mvn clean 

#run tests
mvn test

# jacoco
mvn jacoco:prepare-agent install jacoco:report

# pit mutation tests
mvn test org.pitest:pitest-maven:mutationCoverage

# Sonar
docker run -d --name sonarqube -e SONAR_ES_BOOTSTRAP_CHECKS_DISABLE=true -p 9000:9000 sonarqube:latest

admin/admin

mvn sonar:sonar \
  -Dsonar.projectKey=miniddd \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.login=d820bc5f1822b463704c4f327b6e67ed0ab4a1a8