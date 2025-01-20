# Java多线程-异步处理

### 同步 vs. 异步

同步需要等待被调用任务执行完成；异步不需要等待被调用任务执行完成。（同步等价于阻塞，异步等价于非阻塞）



### M1：启动异步处理线程/线程池

这种方法不用多说，使用多线程（`Thread` 或 `Runnable`）可以实现异步处理。

扩展：可使用线程池代替手动创建线程

问题来了，异步线程无法返回任何结果给主线程，怎么办呢？使用下面的方法2 `Future` 即可



### M2：`Future`/`CompletableFuture`

相对方式1，使用 `Future` 能够**取到异步线程的返回值**。之所以 `Future` 能提供这种额外能力，本质是因为：
* 异步线程使用的是 `java.lang.Runnable` 的 `void run()` 方法，没有返回值
* `Future`接口使用的是 JDK 1.5 新引入的 `java.util.concurrent.Callable<V>` 的 `V call()` 方法，有返回值（类型为`V`）
* `CompletableFuture` 类是 JDK 1.8 新引入的：

> `CompletableFuture` are Futures that also allow you to **string tasks together in a chain**. You can use them to tell some worker thread to "go do some task X, and when you're done, go do this other thing using the result of X". Using `CompletableFutures`, you can do something (e.g. some callback logic) with the result of the operation without actually blocking a thread to wait for the result.
>
> Ref: https://stackoverflow.com/a/35347215



1. `Future` 代码示例：

```java
import java.util.concurrent.*;

public class ExampleAsyncWithFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("=== Example 1: Vanilla thread ===");
        futureUsingVanillaThread();
        System.out.println("=== Example 2: Submitting tasks with Future via thread pool ===");
        futureUsingThreadPool();
    }

    public static void futureUsingVanillaThread() throws ExecutionException, InterruptedException {
        FutureTask<Integer> asyncTask = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("executing async task...");
                Thread.sleep(3000);
                System.out.println("async task DONE!");
                return 42;
            }
        });

        new Thread(asyncTask).start();

        // Main thread will be blocked here!
        Integer integer1 = asyncTask.get();
        System.out.println("From main task: async task result is " + integer1);
    }

    public static void futureUsingThreadPool() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(1);

        Future<Integer> asyncTask = executor.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("executing async task...");
                Thread.sleep(3000);
                System.out.println("async task DONE!");
                return 42;
            }
        });

        // Main thread will be blocked here!
        Integer integer1 = asyncTask.get();
        System.out.println("From main task: async task result is " + integer1);

        executor.shutdown();
    }
}
```

> Ref: [Java高并发编程中Future的使用及详细介绍-刘宇_java.util.concurrent.futuretask.get(futuretask.jav-CSDN博客](https://blog.csdn.net/liuyu973971883/article/details/108049656)



2. `CompletableFuture` 代码示例：

（见 https://www.baeldung.com/java-callbacks-listenablefuture-completablefuture#callback-in-completablefuture）





### M3：Spring `@Async`

Spring 框架提供了异步处理注解 `@Async` 和 `@EnableAsync`，本质上也是线程/线程池。以下为代码示例：

Asynchronous service:

```java
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncService {
    @Async
    public void asyncMethod() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Executing asyncMethod");
    }
}
```

Spring Boot application（`@EnableAsync` 是一个开关，如果不打开的话所有的 `@Async` 方法可以按同步方式运行）:

```java
@SpringBootApplication
@EnableAsync
public class AsyncApplication {
	public static void main(String[] args) {
		ApplicationContext ac = SpringApplication.run(AsyncApplication.class, args);
		AsyncService asyncService = ac.getBean("asyncService", AsyncService.class);
		System.out.println("=== In main method 1");
		asyncService.asyncMethod();
		System.out.println("=== In main method 2");
	}
}
```

`@Async` 默认会把当前方法交给 `SimpleAsyncTaskExecutor` 执行。`SimpleAsyncTaskExecutor` 并不是严格意义的线程池，底层实际上和新建了一个线程去执行该异步方法是一样的，达不到线程复用的功能（假的线程池）。所以实际项目中，一般会使用其它的Spring线程池实现，包括：

* `SimpleAsyncTaskExecutor`：不是真的线程池，这个类不重用线程，默认每次调用都会创建一个新的线程。
* `SyncTaskExecutor`：这个类没有实现异步调用，只是一个同步操作。只适用于不需要多线程的地方。
* `ConcurrentTaskExecutor`：Executor的适配类，不推荐使用。如果ThreadPoolTaskExecutor不满足要求时，才用考虑使用这个类。
* `SimpleThreadPoolTaskExecutor`：是Quartz的SimpleThreadPool的类。线程池同时被quartz和非quartz使用，才需要使用此类。
* `ThreadPoolTaskExecutor`：最常使用，推荐。 其实质是对java.util.concurrent.ThreadPoolExecutor的包装。



>  Ref: [Springboot之@Async异步指定自定义线程池使用_@enableasync 和线程池-CSDN博客](https://blog.csdn.net/weixin_47390965/article/details/129368573)



代码示例-修改 `@Async` 方法使用的线程池为自定义的 `myTaskExecutor`：

```java
@Async("myTaskExecutor")
public void asyncMethod() {
    // some async execution...
}
```



另外，Spring 应用中也经常会搭配retry模块实现重试机制。



### M4：消息队列





### 几种方法的对比

其实本质上来说，前三种方法是类似的，都是通过异步线程（或线程池）解决。所以对比的是异步线程 vs. 消息队列：

* 异步线程（池）在大量并发请求的情况下会影响系统性能，同时导致异步处理任务的堆积。试想100个并发请求，每个请求又需要一个额外的异步线程，那这样最多就有200个线程同时运行，大量线程在同时运行，系统性能会下降。如果使用线程池，还存在线程池是否会因为任务过多而拒绝异步任务的问题：如果拒绝，那需要异步处理的业务逻辑不会被执行；如果不拒绝，那这么多任务导致等待队列大量积压，甚至OOM。而使用Kafka 则解决了这个问题。**本质上，使用异步线程方式的任务堆积是在内存中的，而使用Kafka方式的任务堆积是在磁盘中的，这就很好避免了OOM的问题，也缓解了CPU压力。**





