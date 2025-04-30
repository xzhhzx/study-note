### 什么是 Servlet？

Servlet 本质只是一个Java接口，包含5个方法：

```Java
public interface Servlet {
    void init(ServletConfig var1) throws ServletException;

    ServletConfig getServletConfig();

    void service(ServletRequest var1, ServletResponse var2) throws ServletException, IOException;

    String getServletInfo();

    void destroy();
}
```



<img src="..\..\images\servlet.png" style="zoom:50%;" />

1. 浏览器发出 http://localhost:8080/web-demo/demo1 请求，从请求中可以解析出三部分内容，分别是localhost:8080、web-demo、demo1：
   - 根据localhost:8080可以找到要访问的Tomcat Web服务器
   - 根据web-demo可以找到部署在Tomcat服务器上的web-demo项目
   - 根据demo1可以找到要访问的是项目中的哪个Servlet类，根据@WebServlet后面的值进行匹配
2. 找到ServletDemo1这个类后，Tomcat Web服务器就会为ServletDemo1这个类创建一个对象，然后调用对象中的service方法
3. ServletDemo1实现了Servlet接口，所以类中必然会重写service方法供Tomcat Web服务器进行调用
4. service方法中有ServletRequest和ServletResponse两个参数，ServletRequest封装的是请求数据，ServletResponse封装的是响应数据。



### Servlet 的初始化

* 默认情况下，当Servlet第一次被访问时，由容器创建Servlet对象。

```
2025-01-15 09:39:18.223  INFO 50028 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
```

* loadOnstartup的取值有两类情况：

  - 负整数: 第一次访问时创建Servlet对象

  - 0或正整数: 服务器启动时创建Servlet对象，数字越小优先级越高

* 但是如果创建Servlet比较耗时的话，那么第一个访问的人等待的时间就比较长，用户的体验就比较差，可以修改loadOnStartup配置 -> @WebServlet(urlPatterns = "/demo1",loadOnStartup = 1)





### HttpServlet

HttpServlet 是 Servlet 的抽象实现类



### DispatcherServlet

在Spring框架中，DispatcherServlet 进一步继承了 HttpServlet（是 HttpServlet 的实现类）





### Ref

* [一文带你了解什么是servlet](https://mp.weixin.qq.com/s/mVF4wNPQKeokj6vwG6eWJQ)
