package com.skatepark.shoveit;

import com.skatepark.shoveit.pojo.Person;

import org.junit.Test;

public class InspectorTest {

    @Test
    public void test(){
        Inspector inspector = new Inspector();
        String result = inspector.fieldsAsString(Person.class);
        System.out.println(result);
    }

}
