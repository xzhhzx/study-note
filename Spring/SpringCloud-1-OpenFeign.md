# Declarative RPC: OpenFeign



> 提问：像 apd-oln-service 那样对 127.0.0.1 使用 @FeignClient，每次通道请求相当于会额外占用一个Tomcat线程，这样会不会影响性能？
>
> 答：会影响性能。因此如果只是访问本地服务而非远程服务，直接访问对应的Service即可，不要使用OpenFeign。

<img src="../images/springCloud-OpenFeign.png" style="zoom:70%;" />