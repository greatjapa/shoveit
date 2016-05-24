# Shoveit 

A Yet Another Personal Debugger.

#### Examples

Print field types:

```Java
new Debugger().printTypes(Person.class);

// String name
// int age
// List friends
```

Print field types with custom format:

```Java
new Debugger().printTypes("{1} : {0}", Person.class);

// name : String 
// age : int 
// friends : List 
```

Print values:

```Java
Person person = new Person("Bane", 40);

new Debugger().printValues(person);

// name : Bane
// age : 40
// friends : null
```

Print values with custom format:

```Java
Person person = new Person("Bane", 40);

new Debugger().printValues("{0} is {1}", person);

// name is Bane
// age is 40
// friends is null
```

Print null values:

```Java
Person person = new Person("Bane", 40);

new Debugger().printNullValues(person);

// friends : null
```

Print serializable field values:

```Java
OldPerson person = new OldPerson("Vicent", "van", "Gogh"); //middleName is transient

new Debugger().printSerializableValues(person);

// name : Vicent
// lastName : Gogh
```

Print map:

```Java
new Debugger().printMap(map);

// map size = 4
// cat -> Meow
// ape -> Squeak
// dog -> Woof
// bat -> Squeak
```

Print map with custom format:

```Java
new Debugger().printMap("\"{0}\" : \"{1}\"", map);

// map size = 4
// "cat" : "Meow"
// "ape" : "Squeak"
// "dog" : "Woof"
// "bat" : "Squeak"
```

Print list:

```Java
new Debugger().printMap(list);

// list size = 4
// 0 -> cat
// 1 -> ape
// 2 -> dog
// 3 -> bat
```

Print list with custom format:

```Java
new Debugger().printMap("[{0}] = {1}", list);

// list size = 4
// [0] = cat
// [1] = ape
// [2] = dog
// [3] = bat
```

Print Strings and chaining:

```Java
new Debugger()
       .println("{")
       .println("\"type\": \"Person\"")
       .printValues("\"{0}\" : \"{1}\"", new Person("Bane", 40))
       .println("}");

// {
// "type": "Person"
// "name" : "Bane"
// "age" : "40"
// "friends" : "null"
// }
```

## License

 Shoveit is released under the [MIT license](LICENSE).
