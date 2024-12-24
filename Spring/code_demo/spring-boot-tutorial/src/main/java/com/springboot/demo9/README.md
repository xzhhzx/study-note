## Spring Boot - Spring Boot Actuator

(Ref: https://www.baeldung.com/spring-boot-actuators)

Inside `application.properties` file, enable actuators with the following configuration:
```
management.server.address=127.0.0.1
management.server.port=9001
management.endpoints.web.exposure.include=*
```

> **Test**:  http://localhost:9001/actuator/health


