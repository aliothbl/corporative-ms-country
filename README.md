# corporative-ms-country

## Introduction
Corporative Country is a simple microservice demo application created with Spring Boot and MondoDB for consulting country data such as name, calling code and flag icon.

This project supports a case study that aims to show the difficulty of developing a microservice when Spring Boot is chosen for its construction.

[![CircleCI](https://circleci.com/gh/circleci/circleci-docs.svg?style=shield)](https://app.circleci.com/pipelines/github/aliothbl/corporative-ms-country?branch=main)
 
## Prerequisites
 * Apache Maven 3.6.2 or higher 
 * JDK 8 or higher
 * Git GUI for GitHub Client
 * MongoDb 

## Installation  

### Database

 You can use [MongoDB Community Server](https://www.mongodb.com/try/download/community) or [MongoDb Atlas (Cloud)](https://www.mongodb.com/cloud/atlas). I used MongoDb Atlas to create the database. 
 You can create the user database as 'app-user-ms-country' and the database name as 'corporative' and you won’t have to change these properties on the application.properties file.
 
### Project

 Clone the repo, I used [GUI Client](https://git-scm.com/)
 ```sh
    git clone https://github.com/aliothbl/corporative-ms-country.git
 ```

### Configuration

In the file 'application.properties' configure your MongoDb.
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

 1 - Go to the destination folder
  ```sh
      cd /path/to/root-project-folder
   ```
 2 - Execute the maven command
 ```sh
     mvn clean install
  ```
 3 - Start the microservice
 ```sh
     java -jar target/ms-country.jar
  ```
### Checking the health of the microservice     

Access the [Spring Boot Actuator](http://localhost:8080/actuator/health)   

The response must be:
 ```json
  {
     "status":"UP",
     "components":{
        "diskSpace":{
           "status":"UP",
           "details":{
              "total":402672611328,
              "free":278237704192,
              "threshold":10485760,
              "exists":true
           }
        },
        "mongo":{
           "status":"UP",
           "details":{
              "version":"4.4.6"
           }
        },
        "ping":{
           "status":"UP"
        }
     }
  }
```
##  Using the microservice

Open you browser in 'http://localhost:8080/swagger-ui.html#' or [click here](http://localhost:8080/swagger-ui.html#).<br>
Using the resource 'POST /v1/corporative/countries' to add your first country to the database with the data below:
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

## Useful links
[How do I install Java](https://www.java.com/en/download/help/download_options.html) <br>
[Installing Apache Maven](https://maven.apache.org/install.html) <br>
[GUI Client](https://git-scm.com/) <br>
[Install MongoDB](https://docs.mongodb.com/manual/installation/) <br>