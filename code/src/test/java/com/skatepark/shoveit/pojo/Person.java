package com.skatepark.shoveit.pojo;

import java.util.List;

public class Person {

    private String name;

    private transient String lastName;

    private int age;

    private List<Person> friends;

    public Person(String name, String lastName, int age, List<Person> friends) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.friends = friends;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Person> getFriends() {
        return friends;
    }
}
