* `try-with-resource` 是 JDK 1.7 引入的语法
* 适用于继承了 `AutoCloseable`  的类，如 `InputStream`, `Jedis`
* 在 `try-with-resource` 块执行结束时，会自动调用继承了 `AutoCloseable`  的类的 `close()` 方法
* 对于开发者自定义的类，只需继承 `AutoCloseable` 并实现 `close()` 方法，即可使用 `try-with-resource` 提供的语法功能