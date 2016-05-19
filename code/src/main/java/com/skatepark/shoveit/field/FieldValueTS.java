package com.skatepark.shoveit.field;

import java.lang.reflect.Field;

public class FieldValueTS {

    private final String format;

    public FieldValueTS(String format) {
        if (format == null) {
            throw new IllegalArgumentException("format cannot be null.");
        }
        this.format = format;
    }

    public String get(Field field, Object object) {
        try {
            boolean accessible = field.isAccessible();
            field.setAccessible(true);
            Object result = field.get(object);
            field.setAccessible(accessible);

            String fieldName = field.getType().getSimpleName();

            return result == null ? "null" : String.format(format, fieldName, result.toString());
        } catch (IllegalAccessException e) {
            return null;
        }
    }
}
