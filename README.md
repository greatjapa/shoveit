# Shoveit 

[![Build Status](https://travis-ci.org/greatjapa/shoveit.svg?branch=master)](https://travis-ci.org/greatjapa/shoveit)
[![license](https://img.shields.io/github/license/mashape/apistatus.svg?maxAge=2592000)](https://github.com/greatjapa/shoveit/blob/master/LICENSE)

A Yet Another Personal Debugger.

#### Examples

Print field types:

```Java
new Printer().printTypes(Person.class);

// String name
// String lastName
// int age
// List friends
```

Print field types with custom format:

```Java
new Printer().printTypes(Person.class, "{1} : {0}");

// name : String 
// lastName : String
// age : int 
// friends : List 
```

Print values:

```Java
Person person = new Person("Will", "Smith", 40, null);

new Printer().printValues(person);

// name = Will
// lastName = Smith
// age = 40
// friends = null
```

Print values with custom format:

```Java
Person person = new Person("Will", "Smith", 40, null);

new Printer().printValues(person, "{0} <- {1}");

// name <- Will
// lastName <- Smith
// age <- 40
// friends <- null
```

Print null values:

```Java
Person person = new Person("Will", "Smith", 40, null);

 new Printer().printNullValues(person);
 
// friends = null
```

Print serializable field values:

```Java
Person person = new Person("Will", "Smith", 40, null);

new Printer().printValues(person, "{0} = {1}", Printer.LN, Printer.TRANSIENT.negate());

// name = Will
// age = 40
// friends = null
```

Print map:

```Java
new Printer().printMap(map);

// cat -> Meow
// ape -> Squeak
// dog -> Woof
// bat -> Squeak
```

Print map with custom format:

```Java
new Printer().printMap(map, "\"{0}\" : \"{1}\"");

// "cat" : "Meow"
// "ape" : "Squeak"
// "dog" : "Woof"
// "bat" : "Squeak"
```

Print list:

```Java
new Printer().printList(list);

// 0 -> cat
// 1 -> ape
// 2 -> dog
// 3 -> bat
```

Print list with custom format:

```Java
new Printer().printList(list, "[{0}] = {1}");

// [0] = cat
// [1] = ape
// [2] = dog
// [3] = bat
```

Print Strings and chaining:

```Java
Person person = new Person("Will", "Smith", 40);

new Printer()
       .println("{")
       .printValues(person, "\"{0}\" : \"{1}\"", ",")
       .println("}");

// {
// "name" : "Will",
// "lastName" : "Smith",
// "age" : "40",
// "friends" : "null"
// }
```

## License

 Shoveit is released under the [MIT license](LICENSE).
