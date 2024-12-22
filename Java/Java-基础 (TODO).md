### Language Features

* Java Double Brace Initialization: https://www.baeldung.com/java-double-brace-initialization

* Cannot invoke extra methods in an Anonymous Inner Class in Java: https://stackoverflow.com/questions/67242898/cannot-define-extra-methods-in-an-anonymous-inner-class-in-java
  
  * 对匿名内部类进行“功能拓展”实际上没有任何用，因为指向匿名内部类对象的引用，永远是其父类的引用。而父类中不包含子类（也即匿名内部类）的“功能拓展”
  
* Default interface method
  * 作用1：backward compatibility (adding a new default method doesn't break the code, while adding a new interface method requires all existing implementations to override the method) 
  * 作用2：wrapper for an existing interface method (in fact still being an interface method but just look like a regular method with implementation)



### 匿名内部类为什么是内部类 ?

> 来源：https://stackoverflow.com/a/51904359



> Where does the 'inner' come from?

Inner has a technical meaning in Java. It means two things.

- It means that the class is declared inside another class.
- It means that the class is permitted to refer to the `this` of an instance of the enclosing class.

See the nice taxonomy in @Andreas's answer.

------

Historical footnote.

**In fact, the official terminology is Anonymous Class**. Indeed, Sun used the terminology "Anonymous Class" rather than "Anonymous Inner Class" way back in Java 1.1 when the construct was added to the language. For example, the ["Inner Class Specification"](http://www.cs.ukzn.ac.za/~hughm/java/jdk1.1.4/docs/guide/innerclasses/spec/innerclasses.doc1.html#19641) from the Java 1.1.4 release notes refers to them as "Anonymous Classes" ... most of the time.

I suspect that what happened was that there was some inconsistency in earlier Sun presentations or papers, and various non-Sun authors latched onto the "anonymous inner class" version in their writings. The Sun team tried to quietly correct this by using "Anonymous Class" in the official Java Language Spec and Tutorial. But it was too late. The books were in the bookshops, the articles were on the web.

