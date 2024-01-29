### Example 5: autowiring Beans of type Map/List/Set (advanced feature)
* When Spring initiates the ApplicationContext, it will auto-detect all the Beans that implemented this interface (the type of Map/List/Set collection item), and put them into the collection.
* Similar to the **strategy pattern**/**Service Locator pattern**
* Note: for Map, the key is the Bean name (or Bean id if name not provided)

Ref: https://blog.csdn.net/u010953880/article/details/114848189