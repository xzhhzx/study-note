### Example 1: scopes of Bean & `@Configuration`
* "singleton" (default): only creates one instance. `getBean()` always gets the same object 
* "prototype": creates a new instance of the Bean each time `getBean()` is invoked
* Also note this example passes a Config class to the `AnnotationConfigApplicationContext`, unlike the other demos. `@Configuration` and `@Bean` are used.  

Ref: https://www.baeldung.com/spring-bean