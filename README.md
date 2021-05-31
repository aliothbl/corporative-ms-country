# corporative-ms-country

## Introduction
Simple microservice created with Spring Boot and MondoDB. It is for consulting country data such as name, calling code and flag icon.

This project supports a case study that aims to show the difficulty of developing a microservice when Spring Boot is chosen for its construction.

[![CircleCI](https://circleci.com/gh/circleci/circleci-docs.svg?style=shield)](https://app.circleci.com/pipelines/github/aliothbl/corporative-ms-country?branch=main)
 
## Prerequisites
 * Apache Maven 3.6.2 or later
 * JDK 8 or later
 * Git GUI for GitHub Client
 * MongoDb 

## Instalation  

### Database

 You can use [MongoDB Community Server](https://www.mongodb.com/try/download/community) or [MongoDb Atlas (Cloud)](https://www.mongodb.com/cloud/atlas). I use MongoDb Atlas.
 You can create the database user as 'app-user-ms-country' and the database name as 'corporative'.
 
### Project

 1 - Clone the repo, I use [GUI Client](https://git-scm.com/) for that
 ```sh
    git clone https://github.com/aliothbl/corporative-ms-country.git
 ```

### Configuration

In the file 'application.properties' settings your MongoDb.
 ```sh      
      corporative.ms.country.app-db-user=${APP_DB_USER:<user>}
      corporative.ms.country.app-db-password=${APP_DB_PASSWORD:<passoword>}
      corporative.ms.country.db-server=${DB_SERVER:<database server>}
      corporative.ms.country.db-name=${DB_NAME:<database name>}
   ```
If you want start the microservice in specific port, please change it.
 
 ```sh  
    server.port=${SERVER_PORT:8080}
 ```

## Run 

### Start up 

 1 - Go to the destiny folder
  ```sh
      cd /path/to/root-project-folder
   ```
 2 - Execute maven command
 ```sh
     mvn clean install
  ```
 3 - Start the microservice
 ```sh
     java -jar target/ms-country.jar
  ```
### Checking the microservice health    

Access the [Spring Boot Actuator](http://localhost:8080/actuator/health)   

##  Using the microservice

Open you browse in 'http://localhost:8080/swagger-ui.html#' or [click here](http://localhost:8080/swagger-ui.html#).<br>
Using the resource 'POST /v1/corporative/countries' add your first country to the database with the data below:
 ```json
     {
         "code": "+55",
         "simpleName": "Brasil",
         "fullName": "República Federativa do Brasil",
         "flagImageUrl": "https://s3.amazonaws.com/media.latourtec.com/img/br-flag-4x3.svg"     
     }
  ```
The response body must be:
```json
    {
      "metadata": {},
      "data": {
        "code": "+55",
        "simpleName": "Brasil",
        "fullName": "República Federativa do Brasil",
        "flagImageUrl": "https://s3.amazonaws.com/media.latourtec.com/img/br-flag-4x3.svg",
        "uuid": "60b4c112442d2d6544e1da64"
      }
    }
```
And the response headers must be:

```
 accept-language: en-US, pt-BR, es-ES 
 connection: keep-alive 
 content-language: en-US 
 content-type: application/json 
 date: Mon, 31 May 2021 10:57:22 GMT 
 keep-alive: timeout=60 
 transfer-encoding: chunked 
 vary: Origin, Access-Control-Request-Method, Access-Control-Request-Headers
```

## Resources
[GUI Client](https://git-scm.com/) <br>
[Apache Maven](https://maven.apache.org/index.html) <br>
[MongoDb Atlas](https://www.mongodb.com/cloud/atlas) <br>