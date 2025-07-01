# Maven

### Maven scopes

| Scope                   | Compile Classpath | Test Classpath | Runtime Classpath                    | Included in Final JAR/WAR?                           | Transitive Dependencies Included?      |
| :---------------------- | :---------------- | :------------- | :----------------------------------- | :--------------------------------------------------- | :------------------------------------- |
| **`compile`** (Default) | ✅ Yes             | ✅ Yes          | ✅ Yes                                | ✅ Yes                                                | ✅ Yes                                  |
| **`provided`**          | ✅ Yes             | ✅ Yes          | ❌ No (provided externally)           | ❌ No                                                 | ✅ Yes                                  |
| **`runtime`**           | ❌ No              | ✅ Yes          | ✅ Yes                                | ✅ Yes                                                | ✅ Yes                                  |
| **`test`**              | ❌ No              | ✅ Yes          | ❌ No                                 | ❌ No                                                 | ❌ No                                   |
| **`system`**            | ✅ Yes             | ✅ Yes          | :warning: No (unless manually added) | :warning: No (Unless `<includeSystemScope>` is true) | ❌ No                                   |
| **`import`** (BOM)      | ❌ N/A             | ❌ N/A          | ❌ N/A                                | ❌ N/A                                                | ✅ Yes (only for dependency management) |

- **`compile`** → Default, needed everywhere.

- **`provided`** → Supplied by the runtime (e.g., Tomcat Servlet). 参与编译，但是不在runtime或者jar包里，是为了防止和运行环境（如 Servlet）提供的jar包有冲突。

- **`runtime`** → Needed only at runtime (e.g. MySQL JDBC connector). 无需参与编译。



#### `provided` 和 `runtime` 的适用场景
`provided` 和 `runtime` 的区别（个人理解）：`provided` 三方包里**API是需要在自己的项目中调用的**，如果不参与编译，会失败的；但是又只想在编译阶段提供，不打入最终的项目jar包里，只是为了成功通过编译（当然，必须保证实际运行时的另外那个三方包，也包含 provided 包里的那些 API。此时，编译时API和运行时API的关系，好比Java里的接口和实现类的关系）。而  `runtime` 三方包里**API没有直接在项目中调用**，因此无需参与编译（***不过就算参与编译是不是也可以？***）



* `runtime` 使用场景示例：Spring Boot 集成 H2数据库

```xml
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <versionId>1.4.200</versionId>
    <scope>runtime</scope>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
    <versionId>2.3.4.RELEASE</versionId>
</dependency>
```



#### 为何 `system` 不推荐使用以及取代方案

* 原因：不推荐使用是因为写死了路径
* 取代方案：把对应的jar包上传远程仓库