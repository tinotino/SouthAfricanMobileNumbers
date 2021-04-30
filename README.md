# SouthAfricanMobileNumbers
## Building and Running the application
* building the application:
```
./mvn clean package
```
* running the application (from the jar, after having built it):
```
java -jar target/SouthAfricanMobileNumbers-0.0.1-SNAPSHOT.jar
```

Once the application is running, you can:
* send csv file to <http://localhost:8080/api/upload>
* see consumed data at <http://localhost:8080/api/upload>
* open <http://localhost:8080/> and you should see the user form to test a single number

