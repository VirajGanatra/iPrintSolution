# iPrintSolution
iPrintSolution CompuLynx - currently just a REST API without a frontend

## Requirements
- Java 8
- Spring Boot
- Maven
- Postman
- PostgreSQL
- [jsonserv](https://github.com/VirajGanatra/jsonserv)
- [NSSM](nssm.cc)




To run the project , we need to create the PostgreSQL database (since this is still in the testing stage). Thus, Postgres must be installed. To do this, we first need to go to the PSQL command line utility using ```sudo -u postgres psql```. The postgres website can also be used if GUI tools are preferred. Then, run ```ALTER USER postgres PASSWORD 'default'; ``` to set a password. Alternatively, go into the application.properties file in src/resources and set the password (spring.datasource.password) to the current password for the postgres user.

Then, we need to create the databases using the backup.sql file. Clone the project into a directory of choice. Run ```CREATE USER iprint``` and ```CREATE DATABASE iPrintSolution``` from the psql command-line utility, then navigate to it using ```\c iPrintSolution```. Then simply run ```\i backup.sql``` to import the database.

 Then, navigate to the root iPrintSolution folder. Ensure that jsonserv is running (see its ReadME for instructions). The JAR can be created using `mvn clean package`. To run this JAR as a Windows Service, use NSSM. Run ``nssm install``
 , and in the resulting GUI window, set the path to your java.exe file, Startup Directory to the "target" folder where the JAR is contained, and arguments to `-jar "<path:to:jar>"` (where path:to:jar is the actual path to the jar).

Otherwise, build the project by running the ```mvn spring-boot:run``` goal from this folder. It will be available at http://localhost:8080/. POSTMAN can then be used to send requests to it. The list of available requests and parameters is available at http://localhost:8080/swagger-ui/index.html. Additionally, to some requests a JWT token will need to be included in the header (which will be in the reply to the login request). THis JWT will authorize the request based on a username, password, and their role/authority.

There are 4 different roles: ROLE_SUPERADMIN, ROLE_ADMIN, ROLE_USER, and PRINT. The superadmin can send all requests, while a user can only login and create a printer, and an admin can only login and create a user.

Additionally, to interface with the DLL, a non-secured `/update` end-point was created. To use it, simply pass a JSON in the request body like so: 
```json
{
    "accName":"name",
    "accNo":"1234"
}
```





