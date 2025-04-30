### Using Actuator

> TODO



### Securing Actuator

> Ref: [Securing Spring Boot Actuator Endpoints: Best Practices](https://www.appsdeveloperblog.com/securing-spring-boot-actuator-endpoints/)



1. Restrict Actuator endpoints

   ```properties
   management.endpoint.web.exposure.include=health,prometheus
   ```
2. (DEPRECATED, CANNOT FIND THIS IN SPRING DOC!) Restrict visitor IP address

   ```properties
   management.endpoint.health.allowed-ip=127.0.0.1
   ```
3. Authentication
   1. Basic Authentication:
      ```properties
      management.endpoints.web.exposure.include=* 
      spring.security.user.name=admin 
      spring.security.user.password=admin
      ```
   2. RBAC:
      ```properties
      management.endpoint.health.roles=ACTUATOR_ADMIN
      ```

