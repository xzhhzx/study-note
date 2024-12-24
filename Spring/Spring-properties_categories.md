# Spring - categories of application properties

### Basics: categories of properties inside a Spring application

<img src="..\images\spring_properties_0.PNG" style="zoom:50%;" />



##### OS environment variables

1. operating-system-level key/value pairs
2. typically set globally outside of the JVM ("globally" means it is shared by every running process )
3. `System.getenv()` returns a read-only Map. Setting environment variables inside the JVM can only be done via reflection ()



##### Java system properties

1. JVM-specific parameters
2. can be set by:
   * JVM itself during startup (e.g. `java.vm.version`)
   * Command line during startup (e.g. `java -Da.b.c="42" -jar app.jar`)
   * `System.setProperty()`
3. Get values by `System.getProperty()`



##### Spring properties

1. Key-value pairs that can be identified by the Spring framework

2. can be set by:

   * `.properties` file. Properties will be registered into the Spring `ApplicationContext` Environment by `@PropertySource` (do NOT support YAML)

     ```java
     @Configuration
     @PropertySource("classpath:foo.properties")
     public class PropertiesWithJavaConfig {
         //...
     }
     ```

     ```properties
     # Inside foo.properties
     a.b.c=42
     ```
   * Command line (e.g. `java -jar app.jar --a.b.c="42"`)
   
3. Spring properties can be fetched via:

   * Directly from Spring environment: `applicationContext.getEnvironment.getProperty()`
   * Injecting a property with `@Value` annotation
   * (For Spring Boot) Injecting hierarchical properties (as Java Bean) with `@ConfigurationProperties` (See more: [Externalized Configuration :: Spring Boot](https://docs.spring.io/spring-boot/reference/features/external-config.html#features.external-config.typesafe-configuration-properties.vs-value-annotation))

4. If you're using **Spring Boot** on top of Spring framework, more ways can be used to **set** properties:

   * Spring Boot has a default naming for `.properties` file: `application.properties`
   * Spring Boot supports environment-specific properties File
   * Support for YAML
   * Command line Spring properties: `java -jar app.jar --a.b.c="42"`
   * Java system properties: `java -Da.b.c="42" -jar app.jar`




#### Summary

|                          | Definition      | Set value                                                    | Get value                               |
| ------------------------ | --------------- | ------------------------------------------------------------ | --------------------------------------- |
| OS environment variables | OS-level        | should not be set by JVM                                     | `System.getenv()`                       |
| Java system properties   | JVM-specific    | `System.setProperty()`                                       | `System.getProperty()`                  |
| Spring properties        | Spring KV pairs | `.properties` file + `@PropertySource`                       | `environment.getProperty()` or `@Value` |
| Spring Boot properties   | Spring KV pairs | `application.properties` (and many more such as command line) | `@ConfigurationProperties`              |

##### When to use what?

* For setting Spring-specific configurations, I suggest using the 3rd and 4th methods
* For setting active Spring profiles, use the 2nd method as suggested by [Properties and Configuration :: Spring Boot](https://docs.spring.io/spring-boot/how-to/properties-and-configuration.html#howto.properties-and-configuration.set-active-spring-profiles)
* For setting Java-related configurations (not Spring-specific), I suggest using the 2nd method
* OS environment variables should be carefully used, since it affects all the processes running on the machine. (Of course, if containerization technique such as Docker is used then nothing much.)



### How does Spring Boot load different categories of properties?

> Example: `application.properties`

<img src="..\images\spring_properties_1.PNG" style="zoom:50%;" />

> Example: `application-dev.properties`

<img src="..\images\spring_properties_2.PNG" style="zoom:50%;" />

> Example: Java system properties and Spring properties

<img src="..\images\spring_properties_3.PNG" style="zoom:50%;" />



#### Step 1 - Java system properties & OS env vars

Spring Boot will create a new environment, containing 4 property sources. Java system properties and OS env variables are two of them.

<img src="..\images\spring_properties_4.PNG" style="zoom:20%;" />

Inside Java system properties, we can see the `school.name` property which is passed into the application through command line.

<img src="..\images\spring_properties_5.PNG" style="zoom:60%;" />



#### Step 2 - command line Spring property

Then, after executing line 193, a new the `SimpleCommandLinePropertySource` is added. 

<img src="..\images\spring_properties_6.PNG" style="zoom:60%;" />



Here, `spring.x.y.z=42` can be found.

<img src="..\images\spring_properties_7.PNG" style="zoom:50%;" />



#### Step 3 - `.properties` files

After executing line 195, more property sources are added (from index 6 to 10 in below image)

<img src="..\images\spring_properties_8.PNG" style="zoom:20%;" />

Index 9 and 10 are the property source loaded from `application-dev.properties` and  `application.properties` respectively.

<img src="..\images\spring_properties_9.PNG" style="zoom:50%;" />

<img src="..\images\spring_properties_10.PNG" style="zoom:50%;" />



### Relaxed binding

***IMPORTANT NOTE***:

1. For ***setting*** properties into Spring container environment, multiple forms of naming can be used, for which **relaxed-binding** means: 
      ``` properties
      spring.jpa.database-platform=mysql
      spring.jpa.databasePlatform=mysql
      spring.jpa.database_platform=mysql
      ```


2. For ***getting*** properties from Spring container environment, only the **uniform** naming can be used: 
      ``` java
      ac.getEnvironment().getProperty("spring.jpa.database-platform"); 	// Correct
      ac.getEnvironment().getProperty("spring.jpa.databasePlatform"); 	// WRONG!
      ac.getEnvironment().getProperty("spring.jpa.database_platform"); 	// WRONG!
      ```








> References:
>
> * [Java System.getProperty vs System.getenv | Baeldung](https://www.baeldung.com/java-system-get-property-vs-system-getenv)
> * [Properties with Spring and Spring Boot | Baeldung](https://www.baeldung.com/properties-with-spring)
> * Spring Boot reference: [Externalized Configuration :: Spring Boot](https://docs.spring.io/spring-boot/reference/features/external-config.html)
> * Spring Boot how-to guide: [Properties and Configuration :: Spring Boot](https://docs.spring.io/spring-boot/how-to/properties-and-configuration.html#howto.properties-and-configuration.discover-build-in-options-for-external-properties)
> * Spring Boot common properties: [Common Application Properties :: Spring Boot](https://docs.spring.io/spring-boot/appendix/application-properties/index.html#appendix.application-properties.cache)
> * Relaxed Binding: Ref: [github.com](https://github.com/spring-projects/spring-boot/wiki/Relaxed-Binding-2.0)
