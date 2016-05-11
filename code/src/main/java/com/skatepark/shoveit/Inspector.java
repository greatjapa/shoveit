package com.skatepark.shoveit;

import java.io.File;
import java.lang.reflect.Field;
import java.nio.file.Files;

public class Inspector {

    private static final String FIELD_FORMAT = "%s : %s%n";

    public String fieldsAsString(Class<?> clazz) {
        StringBuilder result = new StringBuilder();
        for (Field field: clazz.getDeclaredFields()){
            String fieldName = field.getName();
            String type = field.getType().getSimpleName();
            result.append(String.format(FIELD_FORMAT, fieldName, type));
        }
        return result.toString();
    }

    public int countFields(Class<?> clazz){
       return clazz == null ? 0: clazz.getDeclaredFields().length;
    }

}
