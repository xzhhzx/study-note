# Lombok



## @Data

`@Data` is a convenient shortcut annotation that bundles the features of [`@ToString`](https://projectlombok.org/features/ToString), [`@EqualsAndHashCode`](https://projectlombok.org/features/EqualsAndHashCode), [`@Getter` / `@Setter`](https://projectlombok.org/features/GetterSetter) and [`@RequiredArgsConstructor`](https://projectlombok.org/features/constructor)



## @RequiredArgsConstructor

以下也是一个很好的例子，不但很好地解释了这个注解，也同时解释了以下几个Java特性：

1. 什么是 required argument
2. 怎么使用静态工厂方法（of）代替构造方法（从 `new ConstructorExample<String>()`  变成 `ConstructorExample<String>.of()` ）
3. 静态工厂方法如何使用泛型（这样就能简化调用代码，从 `ConstructorExample<String>.of()` 变成 `ConstructorExample.of()`）



With Lombok:

```Java
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ConstructorExample<T> {
  private int x, y;
  @NonNull private T description;
  
  @NoArgsConstructor
  public static class NoArgsExample {
    @NonNull private String field;
  }
}
```



Vanilla Java

```Java
public class ConstructorExample<T> {
  private int x, y;
  @NonNull private T description;
  
  private ConstructorExample(T description) {
    if (description == null) throw new NullPointerException("description");
    this.description = description;
  }
  
  public static <T> ConstructorExample<T> of(T description) {
    return new ConstructorExample<T>(description);
  }
  
  @java.beans.ConstructorProperties({"x", "y", "description"})
  protected ConstructorExample(int x, int y, T description) {
    if (description == null) throw new NullPointerException("description");
    this.x = x;
    this.y = y;
    this.description = description;
  }
  
  public static class NoArgsExample {
    @NonNull private String field;
    
    public NoArgsExample() {
    }
  }
}
```