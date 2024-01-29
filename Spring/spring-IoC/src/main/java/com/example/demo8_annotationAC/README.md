### Container: AnnotationConfigApplicationContext

The Spring Framework core is, simply put, an IoC container used to manage beans.

There are two basic types of containers in Spring – the **Bean Factory** and the **Application Context**. The former provides basic functionalities, which is not introduced here; the latter is a superset of the former and is most widely used.

`ApplicationContext` is an interface in the `org.springframework.context` package and it has several implementations, which initializes container and manages Beans.

Another way of defining `ApplicationContext` is by using annotations. This demo shows how developers can initialize an `AnnotationConfigApplicationContext` in three methods. 