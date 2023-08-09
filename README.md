# iPrintSolution
iPrintSolution CompuLynx - currently just a REST API without a frontend

## Requirements
- Java 8
- Spring Boot
- Maven
- Postman
- PostgreSQL



To run the project , we need to create the PostgreSQL database (since this is stil in the testing stage). Thus, Postgres must be installed. To do this, we first need to go to the PSQL command line utility using ```sudo -u postgres psql```. Then, run ```ALTER USER postgres PASSWORD 'default'; ``` to set a password. Alternatively, go into the application.properties file in src/resources and set the password to the current password.

Then, we need to create the databases using the following. First, the users:
```CREATE TABLE users(id SERIAL PRIMARY KEY, username VARCHAR(100), password VARCHAR(100));```. Then, the printers:
```CREATE TABLE printers(id SERIAL PRIMARY KEY, name VARCHAR(100), ip VARCHAR(100));```

Build the project using the mvn spring-boot:run goal. It will be available at http://localhost:8080/. POSTMAN can then be used to send requests to it.

When sending requests via POSTMAN, ensure a JSON body is added to each request. Upon successful sign-in, the JWT Bearer Token will be given in the response to the POST request.

## Available Requests
- POST /session (login)
- POST /user (signup)
- POST /printer (add printer)
