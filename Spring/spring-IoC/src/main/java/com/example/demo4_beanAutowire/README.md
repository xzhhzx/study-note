### Example 4: Bean autowiring
Bean autowiring is the annotation-driven method of Spring DI. It allows Spring to resolve and inject collaborating beans into our bean.
* Demonstrates the `@Autowired` annotation for designating  collaborators. Autowiring can be achieved by annotating:
  * Data field attribute (shown as `someCollaborator`)
  * Constructor (shown as `anotherCollaborator`)
  * Setter
* Demonstrates the `@Qualifier` annotation for specifying the actual implementation class when multiple sub-classes are available. This is to avoid disambiguation.

Ref: https://www.baeldung.com/spring-autowire