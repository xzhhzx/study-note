# README
Sample code for demonstrating Spring Bean definition
(see each demo)



### Dependency Injection

1. **Field** injection is NOT recommended
2. (RECOMMENDATION) **Constructor** injection for mandatory dependencies; **setter** injection for optional dependencies
3. **Constructor** injection can introduce ***circular dependencies***, in this case **setter** injection can be used to avoid
4. In conclusion, **setter** injection may be the most recommended way for dependency injection