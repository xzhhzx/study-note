# Spring Boot - Testing


## Unit test

* As for unit tests, it should be **fast**
* A Servlet/Tomcat/Spring application context is **NOT** started, no HTTP request/response is actually received/sent
* Thus unit tests of Spring Boot application actually does **NOT** use Spring at all
* Therefore, the following should **not** appear in unit tests:
  * `@SpringBootTest` (for whole integration test, loading the complete Spring application context)
  * `@WebMvcTest` (for slice test - testing the web controller layer for Spring MVC)
  * `@WebFluxTest` (for slice test - testing the web controller layer for Spring WebFlux)
  * `@DataJpaTest` (for slice test - testing the persistence layer)



### Useful packages/libraries

* Mockito: a package for mocking.

* AssertJ: a package for writing assertations
* JaCoCo:  generate code coverage reports



| ref                                                       |
| --------------------------------------------------------- |
| https://reflectoring.io/unit-testing-spring-boot/         |
| https://www.baeldung.com/java-unit-testing-best-practices |



## Slice test

​	The  granularity of slice test is somewhere between unit test and integration test. It tests the Spring application layer by layer, such as the web controller layer and the persistence layer. Usually, a mock instance is needed (e.g. `MockMvc` to simulate HTTP requests and verify response). 

​	Note how slice test is **different from unit test** is that, HTTP requests are simulated and received by the controller layer. In unit tests, there is no HTTP communications involved at all, only the inside logic.  Therefore, if you want to test whether the controller method correctly received the HTTP request, or if the bean validation is performed correctly, you need a slice test. (An counter-example is shown in `Demo8ApplicationUnitTests.insertInvalidTest`)

Slice test example:

* `@WebMvcTest` + `MockMvc` (for Spring MVC web controller layer) 
* `@WebFluxTest` + `WebTestClient` (for Spring Webflux web controller layer)
* `@DataJpaTest`
* More others: https://docs.spring.io/spring-boot/docs/current/reference/html/test-auto-configuration.html



| ref                                               |
| ------------------------------------------------- |
| https://reflectoring.io/spring-boot-web-controller-test/ |
| https://reflectoring.io/spring-boot-data-jpa-test/ |



## Integration test

Integration test is an end-to-end test. 

* `@SpringBootTest`



| ref                                      |
| ---------------------------------------- |
| https://reflectoring.io/spring-boot-test |





## Conclusion

|                                                  | Unit Test                 | Slice Test                            | Integration Test                    |
| ------------------------------------------------ | ------------------------- | ------------------------------------- | ----------------------------------- |
| Test goal                                        | each unit                 | each layer                            | end-to-end                          |
| Speed                                            | fast                      | slow                                  | very slow                           |
| Does the test method relies on Spring framework? | :x:                       | :heavy_check_mark:                    | :heavy_check_mark:                  |
| If start Spring `ApplicationContext`?            | :x:                       | :heavy_check_mark:                    | :heavy_check_mark:                  |
| If start Tomcat?                                 | :x:                       | :x:                                   | :grey_question: (depends on config) |
| What need to be mocked? (TODO)                   | dependencies (by `@Mock`) | dependencies (by `@MockBean`) & upper | upper                               |



## Take-aways
1. Unit tests does not use Spring framework!
2. `@WebMvcTest` vs. `@SpringBootTest`:

* @WebMvcTest: instantiates only the web layer rather than the whole context
* @SpringBootTest: end-to-end


> Spring Boot provides the @SpringBootTest annotation which we can use to create an application context containing all the objects we need for both unit test and integration test. Note, however, that overusing @SpringBootTest might lead to very long-running test suites.
> src: https://reflectoring.io/spring-boot-test/#integration-tests-vs-unit-tests





#### Refs
* https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.testing
* https://docs.spring.io/spring-boot/docs/2.1.x/reference/html/boot-features-testing.html#boot-features-testing
* https://rieckpil.de/spring-boot-testing-mockmvc-vs-webtestclient-vs-testresttemplate/