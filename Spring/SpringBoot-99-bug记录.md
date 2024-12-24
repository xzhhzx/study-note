> Ref: https://stackoverflow.com/questions/42907553/field-required-a-bean-of-type-that-could-not-be-found-error-spring-restful-ap



**问题报错**：Field xxx required a bean of type that could not be found.

**解决方法**：

1. 检查是否跨包引用，导致 SpringBootApplication 没有扫描上对应的类。如果是，在 ComponentScan 注解手动加上包名
2. 检查是否对应的 bean类是否有 @Service, @Component, @Repository 等注解。如没有，则加上
3. 对于类，需要加 @Autowire；对于接口，(有时) 不应该加 @Autowire
4. 如果是**Mapper类**扫描不上，有可能是 MyBatis 的问题，尝试：
   * 在启动类上加 MyBatis 的注解 `@MapperScan` (https://www.baeldung.com/spring-mybatis)