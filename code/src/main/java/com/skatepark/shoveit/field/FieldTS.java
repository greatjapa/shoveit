package com.skatepark.shoveit.field;

import java.lang.reflect.Field;

public class FieldTS {

    public static String type(String format, Field field) {
        String fieldName = field.getName();
        String type = field.getType().getSimpleName();
        return String.format(format, fieldName, type);
    }

    public static String value(String format, Field field, Object object) {
        try {
            boolean accessible = field.isAccessible();
            field.setAccessible(true);
            Object result = field.get(object);
            field.setAccessible(accessible);

            String fieldName = field.getName();

            result = result == null ? null : result.toString();

            return String.format(format, fieldName, result);
        } catch (IllegalAccessException e) {
            return null;
        }
    }
}
