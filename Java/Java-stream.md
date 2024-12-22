# Java 8 Stream



### What is a Stream? 

* **Definition**: A sequence of elements supporting sequential and parallel aggregate operations.
* **Stream pipeline**:
  * Source (an array, a collection, a generator function or an I/O channel)
  * Stream operations
    * intermediate operations
    * terminal operation
* **Stream operations features**: 
  * non-interfering
  * stateless/stateful
  * lazy (for intermediate operations) / eager (for terminal operations)
  * *short-circuiting* (for infinite source)
* **Non-reusability**: A stream should be operated on (invoking an intermediate or terminal stream operation) only once. A stream implementation may throw [`IllegalStateException`](https://docs.oracle.com/javase/8/docs/api/java/lang/IllegalStateException.html) if it detects that the stream is being reused.
* **Closeability**: Streams have a [`BaseStream.close()`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/BaseStream.html#close--) method and implement [`AutoCloseable`](https://docs.oracle.com/javase/8/docs/api/java/lang/AutoCloseable.html), but nearly all stream instances do not actually need to be closed after use. Generally, only streams whose source is an IO channel (such as those returned by [`Files.lines(Path, Charset)`](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Files.html#lines-java.nio.file.Path-java.nio.charset.Charset-)) will require closing. (If a stream does require closing, it can be declared as a resource in a `try`-with-resources statement.)
* **Parallelism**: Stream pipelines may execute either sequentially or in parallel.

> Ref: https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html



### Stream vs. Collection

> Collections and streams, while bearing some superficial similarities, have different goals. Collections are primarily concerned with the **efficient management of, and access to, their elements**. By contrast, streams do not provide a means to directly access or manipulate their elements, and are instead concerned with **declaratively describing their source and the computational operations** which will be performed in aggregate on that source.
>
> (*From https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html*)



> Ref: https://stackoverflow.com/questions/39432699/what-is-the-difference-between-streams-and-collections-in-java-8



### Reduction (terminal operations)

*Reduction operations* (also called a ***fold***) is equivalent to terminal operations. It takes a sequence of input elements and combines them into a single summary result by repeated application of a combining operation. There are 2 kinds of general reduction operations:

* ***Immutable*** reduction operation
* ***Mutable*** reduction operation



#### Immutable reduction: `Stream.reduce()`

Three forms:

```java
T reduce(T identity, BinaryOperator<T> accumulator);
```

```java
Optional<T> reduce(BinaryOperator<T> accumulator);
```

```java
<U> U reduce(U identity,
             BiFunction<U, ? super T, U> accumulator,
             BinaryOperator<U> combiner);
```



##### Example 1:

```java
// 1st form
List<Person> people = new ArrayList<>();
int totalAge = people.stream()
                    .map((Person p) -> p.getAge())
                    .reduce(0, (Integer a, Integer b) -> a + b);

```

##### Example 2:

```java
// 2nd form: no initial seed value and returns an Optional
List<Person> people = new ArrayList<>();
Optional<Integer> totalAgeOpt = people.stream()
                    .map((Person p) -> p.getAge())
                    .reduce((Integer a, Integer b) -> a + b);
```

##### Example 3:

```java
// 3rd form: 
// The three-argument form is a generalization of the two-argument form, 
// incorporating a mapping step into the accumulation step.
// (though the explicit map-reduce form is more readable and therefore should usually be preferred)
List<Person> people = new ArrayList<>();
Integer totalAge = people.stream()
                    .reduce(
                        0, 
                        (Integer partialSum, Person b) -> partialSum + b.getAge(), 
                        (Integer a, Integer b) -> a+b
                    );
```

> Here, the *identity* element is both an initial seed value for the reduction and a default result if there are no input elements. The *accumulator* function takes a partial result and the next element, and produces a new partial result. The *combiner* function combines two partial results to produce a new partial result. (The combiner is necessary in parallel reductions, where the input is partitioned, a partial accumulation computed for each partition, and then the partial results are combined to produce a final result.)





#### Mutable reduction: `Stream.collect()`

A *mutable reduction operation* accumulates input elements into a mutable result container, such as a `Collection` or `StringBuilder`, as it processes the elements in the stream.

```java
List<String> input = new ArrayList<>();
for (int i=0; i<5; i++) {
    input.add("hello");
    input.add("world");
    input.add("wenwen");
}

String concatResult = input.stream()	// or input.parallelStream()
    .collect(
        () -> new StringBuilder(),
        (StringBuilder sb, String s) -> {
            System.out.println("=== accumulator called ===");
            sb.append(s + ",");
        },
        ((StringBuilder sb1, StringBuilder sb2) -> {
            System.out.println("=== combiner called ===");
            sb1.append(sb2);
        })
    ).toString();
```



#### Downstream collector

```java
Map<Person.Sex, Double> averageAgeByGender = roster
    .stream()
    .collect(
        Collectors.groupingBy(
            Person::getGender,                      
            Collectors.averagingInt(Person::getAge))); // Downstream collector (Similar to `HAVING` keyword in SQL)
```



### Q&A

1. Stream vs. Collection (https://stackoverflow.com/questions/39432699/what-is-the-difference-between-streams-and-collections-in-java-8)
2. `reduce()` vs. `collect()` (https://stackoverflow.com/questions/22577197/java-8-streams-collect-vs-reduce)
3. Three-parameter-formed `reduce()`'s combiner (https://stackoverflow.com/questions/24308146/why-is-a-combiner-needed-for-reduce-method-that-converts-type-in-java-8)
4. `map` vs `flatMap`



### Ref

* Java doc - Stream class: https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html
* Java doc - Stream package summary: https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html#StreamSources

* Java tutorial: https://docs.oracle.com/javase/tutorial/collections/streams/reduction.html