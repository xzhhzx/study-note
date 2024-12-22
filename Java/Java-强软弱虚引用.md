# Strong / Soft / Weak / Phantom reference

> Ref: [Strong, Weak, Soft, and Phantom References in Java | Baeldung](https://www.baeldung.com/java-reference-types)



### Strong reference

Q: When will strong-referenced objects be GC?

A: When reference is set to `null` (suppose connection with GC root is available), or else the object will never be GC, even OOM



### Phantom reference

The `get()` method of `PhantomReference` will always return `null`. 

Phantom references are great **if we need to implement some finalization logic**, and they’re considerably more reliable and flexible than the [*finalize*](https://www.baeldung.com/java-finalize) method. Phantom reference use case sample code:

```java
private static void clearReferences(ReferenceQueue queue) {
    while (true) {
        Reference reference = queue.poll();
        if (reference == null) {
            break; // no references to clear
        }
        cleanup(reference);
    }
}
```

