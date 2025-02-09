# Java-并发编程-锁

### 线程安全的三个性质

* 可见性（Visibility）
* 原子性（Atomic）
* 有序性（Order）



### 锁的设计思想

1. 悲观思想：认为发生并发的概率很大，每次**访问**共享资源都验锁、加锁
2. 乐观思想：认为发生并发的概率很小，每次**访问**共享资源时不会验锁、加锁，而是在发现确实有**更新冲突**时再重试（本质上**“乐观锁”不是锁**，因为它没有用到锁，而是用**重试机制**代替锁的功能）

（乐观思想的两种实现方式：1.CAS；2.版本号机制）



### 两种锁的基础原理

* 互斥锁
  * 无法获取锁则线程挂起（运行态RUNNING 转为睡眠态 SLEEPING）
  * 存在内核态的切换
  * 适用于阻塞等待时间长的场景
* 自旋锁：
  * 无法获取锁则线程忙等待
  * 不存在内核态的切换
  * 适用于阻塞等待时间短的场景
  * 自旋是通过while循环+CAS实现的





### 锁的性质

#### 公平性

是否先到先得，先到先得即为公平锁，无优先级随机给与锁即为非公平锁



#### 可重入性

**同一线程**是否可以对同一个锁进行多次重复持有



#### 共享性

**不同线程**是否可以对同一个锁进行持有



### synchronized 关键字 —— “自动挡”锁

在 JDK 1.6 之后，`synchronized` 关键字背后用到的锁机制有多种。其会检测**锁竞争的严重程度**，据此自动地选择某一种锁机制来实现对指定方法或代码块单一线程访问。（可以理解为是一种 all-in-one 的锁，因此类比为“自动挡”）

**锁升级**机制：随着锁竞争的程度逐渐严重，**偏向锁**（一种乐观锁） → **轻量级锁**（一种自旋锁） → **重量级锁**（一种互斥锁）



> 使用 `synchronized` 关键字是否性能不好？

随着 JDK 1.6 对锁升级机制的引入，`synchronized` 关键字的性能不会很差。



### Lock —— “手动挡”锁

(TODO)

`ReentrantLock` vs. `synchronized`: [面试官：说说 synchronized 和 ReentrantLock 的区别](https://mp.weixin.qq.com/s/T2YEQILZTaqaDtmTwMdmZg)

<img src="../../images/synchronized_vs_lock.png" style="zoom:50%;" />



### 总结：Java 里的锁

> 我们在Java里使用的各种锁，**几乎全都是悲观锁**。synchronized从偏向锁、轻量级锁到重量级锁，全是悲观锁。JDK提供的Lock实现类全是悲观锁。其实只要有“锁对象”出现，那么就一定是悲观锁。因为**乐观锁不是锁，而是一个在循环里尝试CAS的算法（或其它实现方式）。**
>
> 那JDK并发包里到底有没有乐观锁呢？有。`java.util.concurrent.atomic`包里面的**原子类**都是利用乐观锁实现的。
>
> Ref: https://zhuanlan.zhihu.com/p/71156910

<img src="../../images/lock_0.png" style="zoom:60%;" />

对于最开始说的三个线程安全的性质，Java中的“锁”都保证了这三个特性；而像 `volatile` 关键字就只能保证可见性和有序性



### Q&A

> **Q:** CAS 不是乐观锁吗，为什么基于 CAS 实现的自旋锁是悲观锁？
>
> **A:** 乐观锁是先修改同步资源，再验证有没有发生冲突。悲观锁是修改共享数据前，都要先加锁，防止竞争。CAS 是乐观锁没错，但是 CAS 和自旋锁不同之处，自旋锁基于 CAS 加了while 或者睡眠 CPU 的操作而产生自旋的效果，加锁失败会忙等待直到拿到锁，自旋锁是要需要事先拿到锁才能修改数据的，所以算悲观锁。
>
> **补充个人理解:** 乐观锁和自旋锁都用到了CAS这一原子性操作，但是有本质区别。**乐观锁是通过CAS实现了“锁”（实际上没加锁），而自旋锁是通过CAS和while循环实现了“自旋”忙等待**。自旋锁的“锁”却是实实在在已经加上的。
>
> 来源：小林coding



### Ref

* https://xiaolincoding.com/os/4_process/pessim_and_optimi_lock.html#%E4%BA%92%E6%96%A5%E9%94%81%E4%B8%8E%E8%87%AA%E6%97%8B%E9%94%81
* https://zhuanlan.zhihu.com/p/71156910