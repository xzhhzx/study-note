### Constructor injection
Constructor injection has the following advantages compared with field injection:
* The `@Autowired` can be omitted (
  As of Spring Framework 4.3, an `@Autowired` annotation on such a constructor is no longer necessary if the target bean defines only one constructor to begin with.)
* Allows you to finalize the bean with the `final` keyword

Actually, field injection is **recommended to be avoided**. See: https://stackoverflow.com/questions/39890849/what-exactly-is-field-injection-and-how-to-avoid-it

### Demo
This demo autowires `myBean` to `myService` via constructor. When running the code, notice that it will print a debugging line as follow:
> Autowiring by type from bean name 'myService' via constructor to bean named 'myBean'