# 分布式锁

### 1. 什么是分布式锁?

#### 定义

个人认为，“分布式锁 ”是一个很迷惑的称呼，因为其锁本身并不是分布式的，而 “分布式锁” 实际指的是 “在分布式环境下的锁”。总结了一个比较准确的定义：**在分布式（多进程）环境下的共享资源互斥机制**。

> **Distributed locks** are a very useful primitive in many environments where different processes must operate with shared resources in a mutually exclusive way.
>
> Ref: https://redis.io/docs/manual/patterns/distributed-locks/



>  **分布式锁是控制分布式系统之间同步访问共享资源的一种方式，通过互斥来保持一致性。**
>
> Ref: https://mp.weixin.qq.com/s/wL9MRnx8HVXNFOt6ZTWELw



#### 线程锁 vs. 进程锁 vs. 分布式锁

<img src="../images/distribute_lock_3.png" style="zoom:40%;" />



### 2. 场景应用示例

两台服务器同时访问数据库中的相同一行（即前文所述的共享资源），并做修改：

<img src="../images/distribute_lock_5.png" style="zoom:70%;" />



* 有个疑问，分布式锁 vs. SELECT FOR UPDATE 二者都可以实现上述场景，那为何还需要分布式锁呢？

> 区别：SELECT FOR UPDATE 是在**数据库内部**完成的锁行（或缩表，如若未命中索引），分布式锁在**数据库外部**额外维护了锁机制
>
> 个人理解：1.分布式锁还有其它更广泛的应用场景；2.分布式锁往往由Redis实现，相比数据库内部的锁性能更好



其它场景：

* 如两台服务器同时 DROP TABLE（这种情况数据库内部的锁貌似无法实现了）
* 两台服务器同时访问 Service B 的某个接口，而该接口需要加锁（访问除数据库以外的其他资源）



### 3. 实现方法

* Redis
  * `SETXN` 命令
  * Redisson 实现的分布式锁
* 数据库
* Zookeeper



### 4. 使用 `Redisson` 实现分布式锁

* **创建**一个分布式锁的引用（此时还未真正上锁，对Redis没有任何操作）：

```java
RLock rLock = redissonClient.getLock("lock-0");
```



* **上锁**。该操作会在 Redis中创建一个Hash类型的键值对，key为`lock-0`，hkey 为 `<随机生成的UUID>:<当前线程ID>`，hvalue 为 1（重入计次）：

```java
// M1: traditional lock method
lock.lock();

// M2: or acquire lock and automatically unlock it after 10 seconds
lock.lock(10, TimeUnit.SECONDS);

// M3: or wait for lock aquisition up to 100 seconds 
// and automatically unlock it after 10 seconds
boolean res = lock.tryLock(100, 10, TimeUnit.SECONDS);
if (res) {
   try {
     ...
   } finally {
       lock.unlock();
   }
}
```

​	结果如下：

<img src="../images/distribute_lock_4.png" style="zoom:100%;" />



* **解锁**：分为手动解锁和到期自动解锁。手动解锁如下：

```java
lock.unlock();
```





#### 完整代码示例：

```java
public class RedissonExampleCode {

    public static void main(String[] args) throws InterruptedException {

        RedissonClient client = Redisson.create();
        RLock rLock = client.getLock("lock-0");

        rLock.lock(60, TimeUnit.SECONDS);
        System.out.println("=== isLocked: " + rLock.isLocked());
        System.out.println("=== isHeldByCurrentThread: " + rLock.isHeldByCurrentThread());
        System.out.println("=== remainTimeToLive: " + rLock.remainTimeToLive() + " ms");
        System.out.println("=== getHoldCount: " + rLock.getHoldCount());

        // 均返回true（原因是在同一个线程中，锁具有可重入性）
        System.out.println("=== tryLock: " + rLock.tryLock(0, 9, TimeUnit.SECONDS));
        System.out.println("=== tryLock: " + rLock.tryLock(0, 6, TimeUnit.SECONDS));
        System.out.println("=== tryLock: " + rLock.tryLock(0, 3, TimeUnit.SECONDS));

        // 返回false（锁已经被占用，因此在不同线程中无法获取锁）
        new Thread(() -> {
            try {
                System.out.println("=== tryLock (from forked thread): " + rLock.tryLock(0, 1, TimeUnit.SECONDS));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        // TTL被覆盖为最后一次重入设定的值3s
        System.out.println("=== remainTimeToLive: " + rLock.remainTimeToLive() + " ms");
        // 锁的持有量变为4个
        System.out.println("=== getHoldCount: " + rLock.getHoldCount());
        TimeUnit.SECONDS.sleep(2);
        System.out.println("=== getHoldCount: " + rLock.getHoldCount());

        // 3s到期释放锁后，锁的持有量变为0
        TimeUnit.SECONDS.sleep(1);
        System.out.println("=== getHoldCount: " + rLock.getHoldCount());
        System.out.println("=== isLocked: " + rLock.isLocked());

        // 锁释放后，此时任意线程可获取锁
        new Thread(() -> {
            try {
                System.out.println("=== tryLock (from forked thread): " + rLock.tryLock(-1, 100, TimeUnit.SECONDS));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        // 此时主线程（或其他任意未占用锁的线程）调用unlock，都会失败抛异常
        System.out.println("isLocked (from main thread): " + rLock.isLocked());
        try {
            rLock.unlock();
        } catch (IllegalMonitorStateException e) {
            System.out.println("Current thread does not occupy this lock.");
        }
    }
}
```



