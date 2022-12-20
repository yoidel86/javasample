## Build and Run
Project can be build and run with maven commands.
```cmd
mvn clean install
mvn spring-boot:run
```

## Testing
Once compiled and running, the application will start on 8080 port.
You can send request through Postman (for example) or use swagger ui page.
at : http://localhost:8080/swagger-ui.html.
The application is provided with Unit Test and Integration Tests that you can run using maven commands
```cmd
mvn test
```

## Battery check Task
Executed every minute by default using a cron configuration in application.yml 
```yaml:
tasks:
  cron:
    battery-check: "1 * * * * *"
```
The result of this check is stored at Checks table and can be reviewed accessing in database and by
access to endpoint http://localhost:8080/v1/checks?page=0&size=10

## DataBase
 In-memory H2database is used configured in application.yml and can be accessed using http://localhost:8080/h2-console/login.jsp with JDBC URL 
```database.config
JDBC URL: jdbc:h2:mem:mydb
    user: sa
password: sa
``` 
Data is loaded with Flyway at application start. Scripts are in resources/db/migration:
- V0__create_tables.sql - create tables
- V1__load_data.sql - load data

### Data loaded 

In the information loaded by default exist 4 drones 2 medications and 2 loads. Drone #3 is already loaded with 2 medications
,Load #2 is waiting to be loaded and Drone #4 is ready to load, Dron #5 is ready to load but with low battery
l
