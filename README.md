# Logging HTTP Requests & Responses in Spring Boot with Logbook

This project contains a simple API controller that demonstrates how all HTTP requests and responses can be logged in Spring using [Logbook](https://github.com/zalando/logbook).

The application demonstrates the following key areas of technical interest:
* Spring Boot `@Bean` configuration of LogBook. See `LogbookConfiguration.java` in the project for an example. The demo uses `SplunkHttpLogFormatter`, but others options are available (see `JsonHttpLogFormatter`, `CurlHttpLogFormatter` in the [Logbook documentation](https://github.com/zalando/logbook))
* Use of `QueryFilter`, `HeaderFilter` and `BodyFilter` to strip sensitive information from appearing the log output
* LogBook can also be configured using Spring's `application.properties`. This project includes some example configuration using this method, but the values have been commented out.
* API documentation generated with [Swagger UI](https://swagger.io/tools/swagger-ui/).

## Available Scripts

To run the web server, execute the following command in terminal:

`./gradlew clean bootRun`

This will launch the DemoService application locally and expose an API at `http://localhost:8080/api/hello` with both GET and POST endpoints.

You can interact and test the available HTTP methods by visiting  `http://localhost:8080/swagger-ui.html` in your browser.

As you make requests, all HTTP Requests and Responses will appear in the terminal window. All `password` parameters and the HTTP header `x-secret` will be replaced with the string `<secret>` in the log output.  

You can make a request to the API using curl. Below are functioning GET amd POST requests:

`curl -X GET "http://localhost:8080/api/hello?age=42&name=Chris&password=mypassword" -H "x-secret: mysecretheaderpassword"`

`curl -X POST "http://localhost:8080/api/hello" -H "Content-Type: application/json" -d "{ \"age\": 42, \"name\": \"Chris\", \"password\": \"mypassword\"}"`

