# Shoveit 

[![Build Status](https://travis-ci.org/greatjapa/shoveit.svg?branch=master)](https://travis-ci.org/greatjapa/shoveit)
[![license](https://img.shields.io/github/license/mashape/apistatus.svg?maxAge=2592000)](https://github.com/greatjapa/shoveit/blob/master/LICENSE)

A Yet Another Personal Debugger.

#### Examples

Print field types:

```Java
new Debugger().printTypes(Person.class);

// String name
// String lastName
// int age
// List friends
```

Print field types with custom format:

```Java
new Debugger().printTypes("{1} : {0}", Person.class);

// name : String 
// lastName : String
// age : int 
// friends : List 
```

Print values:

```Java
Person person = new Person("Will", "Smith", 40);

new Debugger().printValues(person);

// name : Will
// lastName : Smith
// age : 40
// friends : null
```

Print values with custom format:

```Java
Person person = new Person("Will", "Smith", 40);

new Debugger().printValues("{0} is {1}", person);

// name is Will
// lastName is Smith
// age is 40
// friends is null
```

Print null values:

```Java
Person person = new Person("Will", "Smith", 40);

new Debugger().printNullValues(person);

// friends : null
```

Print serializable field values:

```Java
Person person = new Person("Will", "Smith", 40); //lastName is transient

new Debugger().printSerializableValues(person);

// name : Will
// age : 40
// friends : null
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
       .println("-- Person values --")
       .printValues("\"{0}\" : \"{1}\"", new Person("Will", "Smith", 40))
       .println("------ end --------");

// -- Person values --
// "name" : "Will"
// "lastName" : "Smith"
// "age" : "40"
// "friends" : "null"
// ------ end --------
```

## License

 Shoveit is released under the [MIT license](LICENSE).
