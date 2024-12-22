## Spring Boot - configuring the web server

### Setting context path
(Ref: https://www.baeldung.com/spring-boot-application-configuration)

Setting context path is simply setting a URL prefix. This can be done by either:
1. Adding config to `application.properties` (recommended, see `application.properties` file)
2. Adding config programmatically (see `CustomizationBean` class)

(Note: if both methods are used, the latter method will override)

> **Test**: http://localhost:8080/myapp2/hello

### Setting custom error path
(Ref: https://www.baeldung.com/spring-boot-application-configuration)

Spring Boot automatically registers a `BasicErrorController` bean for the white label error page.
This can be override by a custom error page controller/].

> **Test**: http://localhost:8080/myapp2/wrongURL 


### Setting embedded server
(Ref: https://docs.spring.io/spring-boot/docs/2.1.9.RELEASE/reference/html/howto-embedded-web-servers.html#howto-use-another-web-server)
`spring-boot-starter-web` includes Tomcat by including `spring-boot-starter-tomcat`, but you can use `spring-boot-starter-jetty` instead, by modifying the POM:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <exclusions>
        <!-- Exclude the Tomcat dependency -->
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
        </exclusion>
    </exclusions>
</dependency>
<!-- Use Jetty instead -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jetty</artifactId>
</dependency>
```

### Server port configuration
`server.port=8080`: configure to 8080
`server.port=-1`: disable HTTP endpoints but still create a WebApplicationContext
`server.port=0`: assign a random unoccupied port

### HTTP response compression configuration
(link: https://docs.spring.io/spring-boot/docs/2.1.9.RELEASE/reference/html/howto-embedded-web-servers.html#how-to-enable-http-response-compression)
(link: https://developer.mozilla.org/en-US/docs/Web/HTTP/Compression)
`server.compression.enabled=true` (enable)
`server.compression.min-response-size=1024` (set min size)


### HTTPS 
(Ref: https://www.thomasvitale.com/https-spring-boot-ssl-certificate/)
(Ref: https://docs.spring.io/spring-boot/docs/2.1.9.RELEASE/reference/html/howto-embedded-web-servers.html#howto-configure-ssl)


### Conclusion
Some tips for configuring the server:
1. Use one of the many available configuration keys (by adding them to the `application.properties`) if possible
2. If a configuration key doesn’t exist for your use case, then look at `WebServerFactoryCustomizer` for more flexible configurations
3. The above-mentioned point 2 may override point 1
4. If both 1 and 2 are needed (e.g. enabling HTTP and SSL at the same time), then use 1 to configure the "more difficult" one (SSL), and use 2 to configure the "less difficult" one (HTTP)
