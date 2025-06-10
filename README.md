# Spring Boot Issues

This project is to reproduce issues related to the spring boot project.

## Running the Application

In Spring Boot 3.4.x and earlier we could do :

```
MYAPP_MANAGEMENT_SERVER_PORT=9090 java -jar target/spring-boot-issues-local.jar
```

since Spring Boot 3.5.0, the above command will not work anymore, and we need to use the following command instead:

```
MANAGEMENT_SERVER_PORT=9090 java -jar target/spring-boot-issues-local.jar
```
