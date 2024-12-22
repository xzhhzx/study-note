### 饿汉式
```java
/* 饿汉式单例模式 */
public class SingletonEager {

    private static final SingletonEager instance = new SingletonEager();

    private SingletonEager() {
        System.out.println("I'm created :)");
    }

    public static SingletonEager getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        System.out.println("=== Start main() ===");
        SingletonEager.getInstance();
        SingletonEager.getInstance();
    }
}
```


### 懒汉式 1
```java
/* 懒汉式单例模式（非线程安全） */
public class SingletonLazy {

    private static SingletonLazy instance;

    private SingletonLazy() {
        System.out.println("I'm created :)");
    }

    public static SingletonLazy getInstance() {
        if (instance == null) {
            instance = new SingletonLazy();
        }
        return instance;
    }

    public static void main(String[] args) {
        System.out.println("=== Start main() ===");
        SingletonLazy.getInstance();
        SingletonLazy.getInstance();
        SingletonLazy.getInstance();
    }
}
```



### 懒汉式 1 (线程安全版)

在上段代码中添加 `synchronized` 关键字。

```java
public static synchronized SingletonLazy getInstance() {
    if (instance == null) {
        instance = new SingletonLazy();
    }
    return instance;
}
```

但是这引入了性能overhead：对于创建好后的单例对象，每次取都需要加锁。可以继续改进



### 懒汉式 1 (线程安全+性能改进版)

改进方法：

1. 在 `getInstance()` 方法体内使用 `synchronized` 关键字，而不是将 `synchronized` 标记在方法上
2. 之后，再在 `synchronized` 代码块的外部，套一层 `if (instance == null)`（两次判空，外层判空不加锁，内层判空加锁）

```java
public static SingletonLazy getInstance() {
    if (instance == null) {
        synchronized(SingletonLazy.class) {
            if (instance == null) {
                instance = new SingletonLazy();
            }
        }
    }
    return instance;
}
```

**不过这种实现还有问题**，JVM 内部可能有**指令重排**的优化。对于 `instance = new SingletonLazy();`，JVM底层拆分为如下三步：

1. 分配堆内存
2. 初始化对象 `new SingletonLazy()`
3. 将引用  `instance` 指向新创建的对象

在JVM的优化下，步骤2和3有可能交换执行顺序，导致当线程A对 `instance` 进行创建时，线程B 也同时获取 `instance`，此时 `instance` 已经有所指向（但实际上对象还未真正初始化）。这样的话就导致线程B 获取的 `instance` 是有问题的（未完全创建好）。因此，可以通过添加 `volatile` 关键字的方式解决（适用于 JRE 1.5 以上版本）：

```java
/* 懒汉式单例模式（使用volatile） */
public class SingletonLazy {

    private static volatile SingletonLazy instance;

    private SingletonLazy() {
        System.out.println("I'm created :)");
    }

    public static SingletonLazy getInstance() {
        if (instance == null) {
            synchronized(SingletonLazy.class) {
                if (instance == null) {
                    instance = new SingletonLazy();
                }
            }
        }
        return instance;
    }
}
```





### 懒汉式 2

```java
/* Singleton implementation with inner static class */
public class SingletonLazyWithInnerClass {

    private SingletonLazyWithInnerClass() {
        System.out.println("I'm created :)");
    }

     /* 这里有个很关键的问题（决定了这种方式的但是是饿汉式还是懒汉式），就是Java里静态内部类是什么时候创建的：
      * 在Java中，外部类加载时伴随着：
          1. 静态成员变量的初始化
          2. 静态方法的加载
          3. 静态代码块的执行
        但是不会加载内部类和静态内部类。
      * 所以用这种巧妙的方式实现了懒汉式。当main方法中调用getInstance，访问 MySingletonHolder.instance时，
        静态内部类才被加载，静态内部类的静态成员也随之初始化。
      * 而且这种方法也是**线程安全**的，这是由JVM保障的。当静态内部类MySingletonHolder被创建时，JVM保证了只有一个
      	线程能创建。这样也不会有性能overhead
      */
    private static final class MySingletonHolder {
        static final SingletonLazyWithInnerClass instance = new SingletonLazyWithInnerClass();
    }

    public static SingletonLazyWithInnerClass getInstance() {
        return MySingletonHolder.instance;
    }

    public static void main(String[] args) {
        System.out.println("=== main() ===");
        SingletonLazyWithInnerClass.getInstance();
        SingletonLazyWithInnerClass.getInstance();
    }
}
```



### 总结

在 *Effective Java 2* 中的 Item 71 也都有提到上述方法：

* 懒汉式 1 (线程安全+性能改进版) 被称为 *double-check idiom*
* 懒汉式 2 被称为 *lazy initialization holder class idiom*





### Ref

* Leetcode - 设计模式系列：如何制作一杯珍珠奶茶
* *Effective Java 2* - Item 71
* https://stackoverflow.com/questions/14927183/effective-java-conundrum-why-is-volatile-required-in-this-concurrent-code
* http://www.cs.umd.edu/~pugh/java/memoryModel/DoubleCheckedLocking.html