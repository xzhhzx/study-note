https://www.baeldung.com/spring-component-scanning#2-using-componentscan-in-aspring-boot-application)



### Spring Boot应用的启动过程

> `@SpringBootApplication` = 
> 	`@Configuration` +
> 	`@EnableAutoConfiguration` +
> 	`@ComponentScan`

The implicit `@EnableAutoConfiguration` annotation, which makes Spring Boot create many beans automatically, relying on the dependencies in pom.xml file.




### 如何降低Spring Boot应用启动时间？

> Ref:
>
> * https://mp.weixin.qq.com/s/khq5aTVyBe2DArOFpQ-Rvg
> * https://stackoverflow.com/questions/27230702/speed-up-spring-boot-startup-time
> * https://www.baeldung.com/spring-boot-startup-speed
> * https://dev.to/jackynote/efficiently-optimizing-spring-boot-applications-faster-startup-and-lower-memory-usage-hjo