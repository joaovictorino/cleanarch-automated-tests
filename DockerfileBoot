FROM maven:3.6.3-openjdk-17-slim as BUILD

COPY bank/ /src/bank
WORKDIR /src/bank
RUN mvn clean package -DskipTests

COPY bankboot/ /src/bankboot
RUN rm /src/bankboot/src/main/resources/application.properties
RUN mv /src/bankboot/src/main/resources/application.docker.properties /src/bankboot/src/main/resources/application.properties
WORKDIR /src/bankboot
RUN mvn clean package -DskipTests

FROM openjdk:17-slim
EXPOSE 8080
COPY --from=BUILD /src/bankboot/target/bankboot-0.0.1-SNAPSHOT.jar /app/bankboot-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-Dloader.path=\"/app/lib\"", "-jar","/app/bankboot-0.0.1-SNAPSHOT.jar"]