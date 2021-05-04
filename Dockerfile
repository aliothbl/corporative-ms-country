FROM openjdk:8

ADD . /corporative-ms-country
WORKDIR /corporative-ms-country

RUN apt-get update && \
    apt-get install -y maven

RUN mvn clean package compile

EXPOSE 8080

ENTRYPOINT ["java","-jar","./target/ms-country.jar"]

