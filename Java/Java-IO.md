# Java IO

### 计算机基础知识：什么是IO？

> 我们常说的输入输出，比较直观的意思就是计算机的输入输出，计算机就是主体。大家是否还记得，大学学计算机组成原理的时候，有个**冯.诺依曼结构**，它将计算机分成分为5个部分：运算器、控制器、存储器、输入设备、输出设备。例如你在鼠标键盘敲几下，它就会把你的指令数据，传给主机，这个过程就是 **Input**；主机通过运算后，把返回的数据信息，输出到显示器，这个过程就是 **Output**。



<img src="../images/java-io-os-0.png" style="zoom:50%;" />

简而言之，**计算机从输入设备获取数据放入内存的过程就是输入（Input）；计算机从内存获取数据发向输出设备的过程就是输出（Output）**。IO 很常见，比如在文件传输（磁盘IO）、Socket通信（网络IO）中都会用到。



* 操作系统的一次IO过程

<img src="../images/java-io-os.png" style="zoom:40%;" />

***引用***：[看一遍就理解：IO模型详解 - 知乎 (zhihu.com)](https://zhuanlan.zhihu.com/p/439770090)



### 操作系统基础知识：四种主要的IO模型总结

简而言之，**四种方式逐渐递减，从数据准备、数据拷贝两阶段都需要阻塞，变成整个过程都不需要阻塞**。

* 同步阻塞IO（Blocking IO）—— 数据准备&数据拷贝都需要用户空间调用方阻塞等待，Java IO 用的方法
* 同步非阻塞IO（Non-blocking IO）—— 数据准备可以使用轮询方式而不阻塞，数据拷贝需要用户空间调用方阻塞等待
* IO多路复用（IO Multiplexing）—— 数据准备不需要阻塞（不再使用轮询而是使用**文件描述符fd**和`epoll`来实现），数据拷贝需要用户空间调用方阻塞等待。这也是 Java NIO 用的方法
* 异步IO（Asynchronous IO）—— 数据准备&数据拷贝都**不需要**用户空间调用方阻塞等待



***详见引用***：

* [看一遍就理解：IO模型详解 - 知乎 (zhihu.com)](https://zhuanlan.zhihu.com/p/439770090)
* [四种主要的IO模型_哪些是同步io,哪些是异步io-CSDN博客](https://blog.csdn.net/qq_41051923/article/details/110082229#:~:text=四种主要的IO模型 1 1、同步阻塞IO（Blocking IO） 2 2、同步非阻塞IO（Non-blocking,IO） 3 3、IO多路复用模型（IO Multiplexing） 4 4、异步IO模型（Asynchronous IO）)



### Java IO

> 注意：以下的 Java IO 指的均是 OIO（Old IO），也即同步阻塞方式的BIO；Java NIO 指的是多路复用方式下的新IO

在 Java IO中，以输入输出**流** (stream) 的形式存在，一个流就是数据的有序序列。同时，Java IO 是同步阻塞的，也即主线程在进行操作系统IO调用的过程中必须阻塞等待，不能干别的事情。



### Java IO 示例：文件传输

[Java基础之IO&NIO操作文件流_nio 读取写入流-CSDN博客](https://blog.csdn.net/Mr_YanMingXin/article/details/115225309)



### Java IO 示例：Socket 通信

* Connection Per Thread 模式

  1. 单个Socket连接单次通信
  2. 单个Socket连接多次通信（在1的基础上加上while循环，不断接收消息）
  3. 多个Socket连接多次通信（在2的基础上再加上while循环，不断创建新的线程来服务新的Socket连接）
  4. 多个Socket连接多次通信（在3的基础上，改用线程池实现）

  

  * https://www.baeldung.com/a-guide-to-java-sockets
  * [Java NIO （图解+秒懂+史上最全）_java nio 图解-CSDN博客](https://blog.csdn.net/crazymakercircle/article/details/120946903)
  
  > 这是一个经典的每连接每线程的模型——Connection Per Thread模式。这种模型，在活动连接数不是特别高（小于单机1000）的情况下是不错的，可以让每一个连接专注于自己的I/O并且编程模型简单，也不用过多考虑系统的过载、限流等问题。此模型往往会结合线程池使用，线程池本身就是一个天然漏斗，可以缓冲一些系统处理不了的连接或请求。
  >
  > 但是，Connection Per Thread 模式最本质的缺点在于，每一个Socket连接都要占用一个线程。假如按此模式开发一个即时通讯软件，那么1000个人同时在线就会占用服务器1000个线程。

这个缺点可以通过 Java NIO 解决。



### Java NIO

以上的示例都是 Java 传统的 BIO，从 JDK 1.0 就存在。在 JDK 1.4 中引入了 NIO（New IO，也有人把它解释为 Non-blocking IO，因为 New IO 主要新增的特性就是非阻塞式的 IO）

|              | Java IO                            | Java NIO                      |
| ------------ | ---------------------------------- | ----------------------------- |
| IO模型       | 同步阻塞IO                         | 多路复用IO                    |
| 数据处理方式 | 面向字节/字符流（Stream-oriented） | 面向缓冲区（Buffer-oriented） |
| 数据读取     | 顺序读取                           | 随机读取（random access）     |



Java NIO 的组成：

* Channel（类似 Java IO 中 stream 的概念，但是 channel 是双向的，stream 是单向的）
  * `FileChannel`
  * `SocketChannel`
  * `DatagramChannel`
* Buffer
* Selector



**Java NIO 的性能优势**：一个线程运行一个 Selector ，而一个 Selector 可以管理多个 Channel。这样就可以实现一个线程管理多个IO（还记得上面说的，对于BIO每一个IO或Socket连接都要占用一个线程，严重占用系统资源）

<img src="../images/java-io-selector.png" style="zoom:40%;" />



### Java NIO 示例：文件传输

[Java基础之IO&NIO操作文件流_nio 读取写入流-CSDN博客](https://blog.csdn.net/Mr_YanMingXin/article/details/115225309)



### Java NIO 示例：Socket 通信