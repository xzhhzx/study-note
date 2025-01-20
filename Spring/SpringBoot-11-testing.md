# Spring Testing

### *@WebMvcTest* vs. *@SpringBootTest*

* *@WebMvcTest* is designed for MVC-related tests, focusing on the web layer and providing easy testing for specific controllers
* *@SpringBootTest* creates a test environment by loading a full application context, including *@Components*, DB connections, and *@Service*, making it suitable for integration and system testing, similar to the production environment.

|                 | *@WebMvcTest*                 | *@SpringBootTest*                                            |
| --------------- | ----------------------------- | ------------------------------------------------------------ |
| Testing type    | unit testing (sliced testing) | integration testing                                          |
| Weight          | lightweight                   | heavyweight                                                  |
| What is mocked? | basically everything          | nothing except `MockMvc` (requests can also be real by using `TestRestTemplate`/`WebTestClient`) |



### MockMVC

> It performs full Spring MVC request handling but via mock request and response objects instead of a running server.

<img src="../images/spring-MockMVC.png" style="zoom:50%;" />





### Integration test - Demo

#####  Test-specific annotations

* `@SpringBootTest`
* `@TestPropertySource`
* `@TestConfiguration` + `@Import`



```java
@SpringBootTest(
        classes = DemoApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
@TestPropertySource(
        locations = {
                // Note: only support .properties file, do NOT support YAML!
                "classpath:application-localtest.properties",
        })
@ActiveProfiles({"dev", "localtest"})
// Note: using @Import is a good practice for adding test-specific configurations annotated with @TestConfiguration
@Import(MyTestConfig.class)
public class DemoApplicationTests {
    @Test
    public void randomTest() {
        assert 1 == 1;
    }
}
```



```java
// Note: Unlike @Configuration, @TestConfiguration is  excluded from component scanning!
@TestConfiguration
public class MyTestConfig {
    
    @Bean
    public MyService myService() {
        return new MyServiceImpl();
    }
}
```





### Ref

* [Testing in Spring Boot | Baeldung](https://www.baeldung.com/spring-boot-testing)
