## DI - Why avoid field injection?

> ref: 
>
> * https://stackoverflow.com/questions/39890849/what-exactly-is-field-injection-and-how-to-avoid-it
> * https://www.baeldung.com/constructor-injection-in-spring#pros-and-cons

**Injection guidelines**

A general guideline, [which is recommended by Spring](http://docs.spring.io/spring/docs/4.2.x/spring-framework-reference/html/beans.html) (see the sections on [Constructor-based DI](https://docs.spring.io/spring/docs/4.2.x/spring-framework-reference/html/beans.html#beans-constructor-injection) or [Setter-based DI](https://docs.spring.io/spring/docs/4.2.x/spring-framework-reference/html/beans.html#beans-setter-injection)) is the following:

- For mandatory dependencies or when aiming for immutability, use constructor injection
- For optional or changeable dependencies, use setter injection
- Avoid field injection in most cases





## `@Order` 作用

> Before Spring 4.0, the *@Order* annotation was used only for the AspectJ execution order. It means the highest order advice will run first.
>
> Since Spring 4.0, it supports the ordering of injected components to a collection. As a result, Spring will inject the auto-wired beans of the same type based on their order value.
>
> Ref: https://www.baeldung.com/spring-order

