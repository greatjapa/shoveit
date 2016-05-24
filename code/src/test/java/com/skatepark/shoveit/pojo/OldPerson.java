package com.skatepark.shoveit.pojo;

public class OldPerson {

    private String name;

    @Deprecated
    private transient String middleName;

    private String lastName;

    public OldPerson(String name, String middleName, String lastName) {
        this.name = name;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }
}
