### 概念澄清

* Cookie 只是载体，它可以承载 token、JWT、sessionId
* 除了Cookie，也有其他方式的载体，如HTTP URL参数或 `Authorization: Bearer <token>` 
* Session 是指会话，就是客户端无需反复认证自己，能多次请求服务提供方的一个抽象概念
* 具体来说，Session 是维护在服务端的用户-服务器有状态记录
* Session 本身和认证或鉴权没有任何关系
* 但是为了维持一个Session，就需要引入认证机制了。服务提供方会为每个 Session 分配一个 sessionId，客户端每次请求拿着 sessionId 去服务端证明自己的身份
* sessionId 往往被放在 cookie 里，因此二者组成了一种常用的鉴权方式





* Token 是一个模糊的概念，所以容易混淆。在某些语境下，token 等于 sessionId（抑或是包含了sessionId 以及 session 相关的其他附加信息）；这和 JWT 中的 token 是不一样的
* 严格来讲，token 只是指 HTTP 请求中的 `Authorization: Bearer <token>` 中的 token 字符串，至于它表示什么意思，那就没有规定了。所以 token 是个很宽泛的概念（所以最好别用），你往  HTTP 请求的 token 位置放上自己的姓名都可以
* 至于 JWT，与其说是 token 的一种，不如说是一个用于校验身份的完整逻辑架构。把它理解为架构/方法，会更有助于理解
* OAuth、OpenID 里也都有 token 的概念





* Cookie 和 Session 没有可比性，拿它俩去比较就好比用饭碗对比厨房，两者没有什么关系
* Cookie 和 sessionId 有关联性，前者是后者的承载。当然，前者也可以承载很多其他的东西





> Reference:
>
> * [JWT详细讲解(保姆级教程)-阿里云开发者社区](https://developer.aliyun.com/article/995894)