# Comandos para criar e executar o projeto

Criar o projeto usando apenas Maven
```sh
mvn org.apache.maven.plugins:maven-archetype-plugin:3.1.2:generate -DarchetypeArtifactId="maven-archetype-quickstart" -DarchetypeGroupId="org.apache.maven.archetypes" -DarchetypeVersion="1.4" -DgroupId="com.bank.account" -DartifactId="bank"
```

Baixar dependências
```sh
mvn install
```

Gerar o JAR
```sh
mvn package
```

Limpar arquivos de build
````sh
mvn clean 
````

Executar testes
````sh
mvn test
````

Executar JaCoCo
````sh
mvn jacoco:prepare-agent install jacoco:report
````

Executar testes de mutação com PIT
````sh
mvn test org.pitest:pitest-maven:mutationCoverage
````

# Executar SonarQube com Docker
````sh
docker run -d --name sonarqube -e SONAR_ES_BOOTSTRAP_CHECKS_DISABLE=true -p 9000:9000 sonarqube:latest
````

Usuário e senha padrão
``admin/admin``

Executar o Sonar dentro do projeto Java
````sh
mvn sonar:sonar \
  -Dsonar.projectKey=miniddd \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.login=d820bc5f1822b463704c4f327b6e67ed0ab4a1a8
````
