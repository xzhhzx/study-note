# Bean 的属性拷贝

在后端编程中，经常需要把类A对象的属性拷贝给类B对象（如 VO 类对象把属性传递给 DTO类）。以下是5种方法总结：

|          | getter/setter                            | Apache BeanUtils | Apache PropertyUtils | Spring BeanUtils | Spring CGLIB Copier | MapStruct                          |
| -------- | ---------------------------------------- | ---------------- | -------------------- | ---------------- | ------------------- | ---------------------------------- |
| 深浅拷贝 | custom                                   | shallow          | shallow              | shallow          | shallow             | deep                               |
| 类型安全 | 编译时报错                               | 运行时报错       | 运行时报错           | 运行时报错       | 运行时报错          | 编译时报错                         |
| 性能     | 很高                                     | 很低（使用反射） | 低                   | 中               | 高                  | 很高                               |
| 优缺点   | 简单可靠性能高，不引入额外依赖；编写繁琐 | （不要使用）     |                      |                  | 性能高              | 性能高，编写简单；需要引入额外依赖 |
| 推荐程度 | :star::star::star:                       | :x:              | :star:               | :star:           | :star::star:        | :star::star::star:                 |

* getter/setter 最直接简单，可以使用
* Apache BeanUtils 性能很差，阿里巴巴Java规范中也严禁使用
* 在中间4种方法，建议选择 Spring CGLIB Copier
* MapStruct 非常推荐，它的原理类似 Lombok，在编译时生成拷贝属性的代码（本质上就是 getter/setter）。而且相对于另外4种还可以支持深拷贝



**总结**：对于大量属性的拷贝场景，选择MapStruct；对于少量属性的拷贝场景，选择 getter/setter
