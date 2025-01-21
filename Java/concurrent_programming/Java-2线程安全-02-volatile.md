# `volatile` Keyword

### What is volatile?

***Short answer***: 如果我们将变量声明为 `volatile` ，这就指示 JVM，这个变量是共享且不稳定的，每次使用它都到主存中进行读取。

***Less short answer***:



|                     | 原子性                                            | 内存可见性                                                   | 有序性                                                       |
| ------------------- | ------------------------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 定义                | Which instructions must have indivisible effects? | Under what conditions the effects of one thread are  visible to another? | Under what conditions the effects of operations can  appear out of order to any given thread? |
| volatile 是否支持？ | :x:                                               | :heavy_check_mark:                                           | :heavy_check_mark:                                           |

