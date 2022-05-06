# Rest-Assured

Criar o projeto apenas com Maven
````sh
mvn org.apache.maven.plugins:maven-archetype-plugin:3.1.2:generate -DarchetypeArtifactId="maven-archetype-quickstart" -DarchetypeGroupId="org.apache.maven.archetypes" -DarchetypeVersion="1.4" -DgroupId="com.bank.account.restassured" -DartifactId="restassured"
````

Baixar dependências
````sh
mvn install
````

Gerar JAR
````sh
mvn package
````

Limpar arquivos de build
````sh
mvn clean
````

Rodar testes
````sh
mvn test
````
