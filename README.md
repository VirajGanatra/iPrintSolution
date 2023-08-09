# iPrintSolution
iPrintSolution CompuLynx - currently just a REST API without a frontend

## Requirements
- Java 8
- Spring Boot
- Maven
- Postman
- PostgreSQL



To run the project , we need to create the PostgreSQL database (since this is stil in the testing stage). Thus, Postgres must be installed. To do this, we first need to go to the PSQL command line utility using ```sudo -u postgres psql```. Then, run ```ALTER USER postgres PASSWORD 'default'; ``` to set a password. Alternatively, go into the application.properties file in src/resources and set the password (spring.datasource.password) to the current password for the postgres user.

Then, we need to create the databases using the following. First, the users:
```CREATE TABLE users(id SERIAL PRIMARY KEY, username VARCHAR(100), password VARCHAR(100));```. Then, the printers:
```CREATE TABLE printers(id SERIAL PRIMARY KEY, name VARCHAR(100), ip VARCHAR(100));```. These are hosted at the standard http://localhost:5432 - if not, this will need to be updated in the application.properties file.

Clone the project into a directory of choice. Then, navigate to the root iPrintSolution folder. Then build the project by running the ```mv spring-boot:run``` goal from this folder. It will be available at http://localhost:8080/. POSTMAN can then be used to send requests to it.

When sending requests via POSTMAN, ensure a JSON body is added to each request. Upon successful sign-in, the JWT Bearer Token will be given in the response to the POST request.

## Available Requests
- POST /session (login); params username/password
- POST /user (signup); params username/password
- POST /printer (add printer); params name/ip
