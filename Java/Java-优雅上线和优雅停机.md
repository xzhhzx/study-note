## 优雅上线

### 目的

* 提高发布的稳定性和可靠性（如健康检查、灰度发布）
* 服务预热（如加载缓存数据）



### 实例

#### Spring

可以使用 `ApplicationRunner` 或 `CommandLineRunner` 实现自定义的预热逻辑（如建立TCP长连接、加载Redis缓存、发送测试HTTP请求等等）

#### K8S 微服务

K8S 的健康检查中，有一个是 readiness 检查，这可以用来提高发布的稳定性和可靠性



## 优雅下线（Graceful shutdown）

### 目的

* 防止突然的关闭（如Web服务需要阻断新的请求，并执行完当前的请求处理）
* save data, close files, release resources, and perform other necessary tasks before exiting

### 实现原理
本质上是向 JVM进程发送 Unix 操作系统的 **SIGTERM** 信号（`kill  -15`）。注意不是 **SIGKILL** 信号（`kill -9`）。另外还要注意这是 Unix 操作系统特有的信号 (platform-dependent)。

> ```txt
> If no signal is specified, the TERM signal is sent. The default action for this signal is to terminate the process. This signal should be used in preference to the KILL signal (number 9), since a process may install a handler for the TERM signal in order to perform clean-up steps before terminating in an orderly fashion. If a process does not terminate after a TERM signal has been sent, then the KILL signal may be used; be aware that the latter signal cannot be caught, and so does not give the target process the opportunity to perform any clean-up before terminating.
> 
> (From Linux kill man page)
> ```

### 实例

#### Java

`shutdownHook` 可以用于接收 **SIGTERM** 信号，在JVM进程结束前，执行自定义操作。实际上，`shutdownHook` 不止在接收到 **SIGTERM** 信号会被调用，在以下情况下都会（除了 kill -9 强制杀进程，其他都可以）：

1. 程序正常执行完成
2. 使用 `System.exit()`
3. SIGINT 信号（如：终端使用Ctrl+C触发的中断）
4. SIGTERM 信号（如：使用kill -15 命令）

```java
public static void main(String[] args) throws InterruptedException {
    long pid = ProcessHandle.current().pid();
    System.out.println(pid);
    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
        System.out.println("Shutdown Hook executed!"); // 程序退出前打印
    }));

    Thread.sleep(20000);
    // 此时在Intellij点击"Stop"，Intellij会向JVM进程发送SIGINT信号。程序退出前会执行shutdownHook，最后打印出程序退出代码（exit code）为130
}
```



#### Tomcat

源码中利用了 Java 的`shutdownHook` 进行优雅关闭。日志如下：

```tex
2025-07-16T10:57:25.301+08:00  INFO 24804 --- [demo] [ionShutdownHook] o.s.b.w.e.tomcat.GracefulShutdown        : Commencing graceful shutdown. Waiting for active requests to complete
2025-07-16T10:57:25.340+08:00  INFO 24804 --- [demo] [tomcat-shutdown] o.s.b.w.e.tomcat.GracefulShutdown        : Graceful shutdown complete
```



#### Spring

Spring 有多种方式可以实现优雅停机：

* 在 Spring Boot 2.3.0 之后，可以通过配置设置开启 Spring Boot 的优雅停机功能：

  ```properties
  # 开启优雅停机，默认值：immediate 为立即关闭
  server.shutdown=graceful
  
  # 设置缓冲期，最大等待容忍时间，默认：30秒
  spring.lifecycle.timeout-per-shutdown-phase=60s
  ```
  
* 使用 **@PreDestroy** 注解可以实现一些自定义的优雅关闭逻辑（不过底层原理是 ApplicationContext 会扫描带有注解的方法，并非和 SIGTERM 有关，待确认）



#### Docker

* `docker stop` 发送 **SIGTERM**（`-t` 参数指定缓冲时间）
* `docker kill` 发送 **SIGKILL**



#### K8S 微服务

K8S 在进行pod下线操作时，也会有优雅停机的逻辑。如执行 `kubectl delete pod`，k8s 会先向 pod 的1号进程发送 **SIGTERM** 信号，并最多等待 `terminationGracePeriodSeconds` 的时间（默认30s）后，再发送 **SIGKILL** 信号。`terminationGracePeriodSeconds` 跟上面 Spring 的一样，可以理解为优雅停机的最大容忍时间，超过了就不等待了，直接强制删除。（Ref: https://cloud.google.com/blog/products/containers-kubernetes/kubernetes-best-practices-terminating-with-grace）



## Take-home message

1. 

2. 各种优雅下线（优雅停机）都是依赖于向进程发送 SIGTERM 信号，通知进程准备将其关闭，让其执行自定义的清理逻辑；同时往往会设置一个最大容忍时间（缓冲时间），到了容忍时间后直接强制关闭。
