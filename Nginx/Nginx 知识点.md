# Nginx 知识点

### `server_name` 有什么作用？

用于匹配请求中的 `Host` HTTP头，对请求进一步分发给不同的Nginx虚拟服务器。

> In this configuration nginx tests only the **request’s header field “Host”** to determine which server the request should be routed to. If its value does not match any server name, or the request does not contain this header field at all, then nginx will route the request to the default server for this port.
>
> Refer: https://nginx.org/en/docs/http/request_processing.html



### 正则匹配

* Nginx 中的正则匹配功能，使用的是 **Perl Compatible Regular Expression (PCRE)** 库实现的

* 不错的总结：https://stackoverflow.com/questions/59846238/guide-on-how-to-use-regex-in-nginx-location-block-section

* 实际遇到的一个问题：

  ```nginx
  # 这里的正则表达式无法被解析
  if ($http_host !~* ^(127.0.0.1|$server_addr)) {
      # ......
  }
  ```

  * 原因是：In Nginx, you can't directly nest variables like `$server_addr` inside a regex pattern in an `if` condition. **The regex pattern is evaluated before variable interpolation.**
  * 解决方案：1. 使用嵌套 if，将需要 variable interpolation 的部分独立出来为内层if，需要解析 regex 的部分独立出来为外层if；2. 使用 `map`；3. 使用 `set` 率先完成 variable interpolation 

 
