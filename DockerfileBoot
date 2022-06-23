FROM maven:3.6.3-openjdk-17-slim as BUILD

COPY . /src
WORKDIR /src

RUN rm /src/bankboot/src/main/resources/application.properties
RUN mv /src/bankboot/src/main/resources/application.docker.properties /src/bankboot/src/main/resources/application.properties

RUN mvn clean package -pl bankboot -am -DskipTests=true

FROM openjdk:17-slim
EXPOSE 8080
COPY --from=BUILD /src/bankboot/target/bankboot-1.0.0.jar /app/bankboot-1.0.0.jar
ENTRYPOINT ["java", "-Dloader.path=\"/app/lib\"", "-jar","/app/bankboot-1.0.0.jar"]