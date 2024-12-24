# Spring 参数校验
> Spring 参数校验指的是外部调用Controller接口时，对传入的RequestBody参数进行校验（如是否空值、是否满足字符串长度等）

Java编程中，可以使用 [`javax.validation`](https://docs.jboss.org/hibernate/stable/beanvalidation/api/javax/validation/package-summary.html) 包，实现 Bean Validation。该包提供了两种参数校验方法：
1. 使用 runtime artifacts 相关类 (`Validation`, `ConstraintViolation`等类)
```java
// programmatic validation API (这种方法偏底层)
ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
Validator validator = factory.getValidator();
Set<ConstraintViolation<User>> violations = validator.validate(user);
```
2. 使用 constraint declaration artifacts 相关类 (`@Valid`注解)
```java
// annotated validation API (这种方法代码比较简洁，也是Spring开发中常用到的)
@RequestMapping("/param_valid")
public ResponseEntity<String> addUser(@Valid @RequestBody User user) {
    return ResponseEntity.ok(paramValidationService.addUser(user));
}
```
还有其他定义入参校验的方法，如拓展为xml文件的方式。



## 常用校验约束条件
约束条件注解定义在 `javax.validation.constraints` 包中，具体包含：
* `@NotNull`, `@NotBlank`
* `@Size`
* `@Min`, `@Max`
* `@Negative`, `@Positive`
* `@Past`, `@Future`
* `@Email`, `@Pattern`
* ......
* 还可以自定义注解，比如验证身份证号，自定义一个 `@IdCardNo`

对于 Spring 应用，一般在Controller层接口方法入参的位置加上 `@Valid` 注解，在Entity类的各个属性上，根据业务逻辑加上 `@Min`,`@NotNull` 等注解。



##  Comparison: `@Valid` vs. `@Validated`

* `@Valid` 属于 `javax.validation` 包（一般通过 `spring-boot-starter-validation` 的 Maven依赖引入）
* `@Validated` 属于 `org.springframework.validation.annotation` 包
* `@Validated` 用于需要分组的情况。如对于新增API，请求需要提供用户姓名、年龄、身份证号；而对于更新API，请求只需要提供身份证号，这种情况下就需要把需要校验的字段根据不同的API接口就行分组（有的时候需要校验身份证号，而有时不用）



> Ref: [Differences in @Valid and @Validated Annotations in Spring | Baeldung](https://www.baeldung.com/spring-valid-vs-validated)




## 校验异常处理
当入参校验失败时，Spring默认会打印日志，但无法给调用方提供反馈信息。如果想返回更准确的信息，需要自定义额外的异常处理方法，这样就可以在校验失败时，捕获Spring抛出的 `MethodArgumentNotValidException` 异常并进行处理。示例代码中，定义在了 `GlobalExceptionHandler` 类的 `handleValidationExceptions` 方法中。关于Spring的统一异常处理，具体详见： [统一异常处理.md](./exception_handling.md))



## 完整代码示例
* Controller: `ParamValidationController`
* Service: `ParamValidationService`
* Unit tests: TODO

