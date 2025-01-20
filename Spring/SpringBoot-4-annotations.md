# Spring 常用注解及对比

### `@Autowired`

根据源码，可以用于五个地方：

1. `@Target(ElementType.CONSTRUCTOR)`  -> 构造器注入 *（可省略）*

2. `@Target(ElementType.METHOD)` -> setter注入

3. `@Target(ElementType.PARAMETER)` -> 字段注入

4. `@Target(ElementType.FIELD)` *（可省略）*

    ```java
    @Bean
    public String ans(@Autowired int num) {
        return String.valueOf(num);
    }
    ```

5. `@Target(ElementType.ANNOTATION_TYPE)`



### `@Autowired`/`@Resource`/`@Inject`

> Ref: [Wiring in Spring: @Autowired, @Resource and @Inject | Baeldung](https://www.baeldung.com/spring-annotations-resource-inject-autowire)

When to use what?

|                           Scenario                           | @Resource | @Inject | @Autowired |
| :----------------------------------------------------------: | :-------: | :-----: | :--------: |
| Application-wide use of singletons through polymorphism (inject by interface or an abstract class type) |    :x:    |    ✔    |     ✔      |
| Fine-grained application behavior configuration through polymorphism (inject by concrete class type) |     ✔     |   :x:   |    :x:     |
| Dependency injection should be handled solely by the Jakarta EE platform |     ✔     |    ✔    |    :x:     |
| Dependency injection should be handled solely by the Spring Framework |    :x:    |   :x:   |     ✔      |



### `@Import` vs. `@ComponentScan`

In the context of Spring Boot (convention-over-configuration), `@ComponentScan` is more like convention, while `@Import` looks like configuration.



### `@ConfigurationProperties` 和 `@EnableConfigurationProperties`

其实很简单，`@ConfigurationProperties` 相当于是 `@Value` 注解的合集版，用来封装`application.properties` 中的外部配置（external configuration），类似一个POJO类。

比如可以自定义一个属性类，用于配置 Redis：

```java
@ConfigurationProperties(prefix="my-redis-config")	// prefix不能是驼峰式，而要用"-"连接
@Setter		// Setters are needed for Spring Boot to "inject" external configs from .properties file
@Getter
public class MyRedisProperties {
    private String url = "127.0.0.1";
    private int database = 0;
    private String username;
    private String password;
}
```

以上代码等同于：

```java
@Setter
@Getter
public class MyRedisProperties {
    @Value("${my-redis-config.url:127.0.0.1}")
    private String url;
    
    @Value("${my-redis-config.database:0}")
    private int database;
    
    @Value("${my-redis-config.username}")
    private String username;
    
    @Value("${my-redis-config.password}")
    private String password;
}
```



接下来需要把 `application.properties` 的外部配置引入到项目中。`@EnableConfigurationProperties` 实现了这一点，其作用原理大致如下：

1. 获取 `application.properties` 文件中以 `{prefix}` 为前缀的配置项的值，封装在`MyRedisProperties` 类型的对象中
2. 把上一步的 `MyRedisProperties` 对象作为Bean 注册在Spring 容器中，Bean的名字为 `{prefix}-{包名}.MyRedisProperties`



这样就可以像其它 Bean 一样使用了。代码如下，通过 `@Autowired` 注入配置类并在 `printRedisConfig()` 方法中使用：

```java
@Configuration
@EnableConfigurationProperties(MyRedisProperties.class)
public class MyRedisConnectionConfig {

    @Autowired
    private MyRedisProperties myRedisProperties;

    public void printRedisConfig() {
        System.out.println("Connecting Redis with URL " + myRedisProperties.getUrl());
    }
}
```

此时在 `application.properties` 配置文件中定义：

```properties
my-redis-config.url=1.2.3.4		# Using remote Redis server, overriding default 127.0.0.1
my-redis-config.database=1		# Using db1, overriding default db0
my-redis-config.username=xzh		# Specifying username
my-redis-config.password=***		# Specifying password
```

`SpringBootApplication` 主类中调用 `printRedisConfig()` 可看到成功打印出 `Connecting Redis with URL 1.2.3.4`。



### 小结

* `@ConfigurationProperties` 用来告诉Spring 所注解的类是用于封装一组外部配置项的，如 Redis、Nacos 配置项
* `@EnableConfigurationProperties` 用来使 `@ConfigurationProperties` 注解的配置类实际地生效，加载配置文件中的相关配置项，并创建配置类对象作为Bean注册到Spring容器中
* 这两个注解对于 Spring Boot 的 auto-configuration 机制非常有用





> Ref：https://stackoverflow.com/a/53072151
>
> 例子：https://github.com/snicoll/spring-boot-master-auto-configuration/blob/main/hornetq-spring-boot-autoconfigure/src/main/java/hornetq/autoconfigure/HornetQAutoConfiguration.java





### 





