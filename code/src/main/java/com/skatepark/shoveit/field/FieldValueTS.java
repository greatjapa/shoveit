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

    public Object get(Field field, Object object) throws IllegalAccessException {
        field.setAccessible(true);
        return field.get(object);
    }
}
