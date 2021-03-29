# Sonar
docker run -d --name sonarqube -e SONAR_ES_BOOTSTRAP_CHECKS_DISABLE=true -p 9000:9000 sonarqube:latest

mvn sonar:sonar \
  -Dsonar.projectKey=miniddd \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.login=d820bc5f1822b463704c4f327b6e67ed0ab4a1a8