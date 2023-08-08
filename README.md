# iPrintSolution
iPrintSolution CompuLynx - currently just a REST API without a frontend

To run the project , we need to create the PostgreSQL database (since this is stil in the testing stage). To do this, we first need to go to the PSQL command line utility using ```sudo -u postgres psql```. Then, run ```CREATE USER username WITH PASSWORD 'default'; ``` to create the user.

Then, we need to create the databases using the following. First, the users:
```CREATE TABLE users(id BIGINT PRIMARY KEY, username VARCHAR(100), password VARCHAR(100));```. Then, the printers:
```CREATE TABLE printers(id BIGINT PRIMARY KEY, name VARCHAR(100), ip VARCHAR(100));```

After this we must ensure that the log directory already exists. THen, simply build the project within the IDE or using the mvn spring-boot:run goal. It will be at http://localhost:8080/. POSTMAN can then be used to send requests to it.
