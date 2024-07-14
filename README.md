## Certification App - by NLW
A system where users can receive a certification according to their answers to a test related to a technology of their choice.

## Used Technologies

- Java JDK 17 or higher
- Spring Boot 3.x (managed via Maven through Spring Initializer (https://start.spring.io)
- Maven
- Docker
- Docker Compose
- PostgreSQL
- Lombok
- Hibernate

### Prerequisites

* Java;
* Maven;
* Docker;
* Docker Compose;

## How to install and setup

- Build the project with Maven:
  `mvn clean install`

- Build and start the database with PostgreSQL:
  `docker-compose up -d`

- Start the application with:
  `mvn spring-boot:run`

## Usage

After all the setup, the application will be avalable at `http://localhost:8085` (tested with https://hoppscotch.io).

## HTTP

## POST - `/students/verifyIfHasCertificationP`

Verify if a student already has a certification.

```json
{  
  "email": "teste@teste.com",
  "technology": "JAVA"	
}
```

## GET - `/questions/technology/{technology` (only JAVA is implemented so far)

If the student doesn't have a certification already, then it displays the questions related to the passed technology.

## POST - `/students/certification/answer`

The studen should pass the answers to the questions.

```json
{
  "email": "goku@teste.com",
  "technology": "JAVA",
  "questionsAnswers": [
    {
      "questionID":"c5f02721-6dc3-4fa6-b46d-6f2e8dca9c66",
      "alternativeID": "bafdf631-6edf-482a-bda9-7dce1efb1890"
    },
    {
      "questionID":"f85e9434-1711-4e02-9f9e-7831aa5c743a",
      "alternativeID": "d3e51a56-9b97-4bb8-9827-8bcf89f4b276"
    }
  ]
}
```

## GET - `/ranking/top10`

Rank the top 10 students who has got the most answers correct.


Enjoy! =)

