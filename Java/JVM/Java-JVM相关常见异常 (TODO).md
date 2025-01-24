### OOM

1. 堆内存溢出（Heap OOM）
2. MetaSpace OOM
3. Stack OOM

### 线程溢出

定义：创建了大量线程且没有回收（或没有及时回收）

-> 可以通过使用线程池避免，这也就是为什么阿里巴巴Java规范里规定了必须使用线程池，而不能手动 new Thread()