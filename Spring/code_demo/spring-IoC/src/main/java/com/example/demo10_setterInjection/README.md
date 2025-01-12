### Setter injection
1. **Constructor injection** (Usually for injecting mandatory fields. @Autowired can be omitted)
2. **Setter injection** (Usually for injecting optional fields. @Autowired can NOT be omitted, if you want this optional field to be injected!)
3. When at some time you don't want to inject one of the optional fields, just simply remove `@Autowired`!