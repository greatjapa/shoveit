package com.skatepark.shoveit.field;

import java.lang.reflect.Field;
import java.util.function.Function;

public class FieldTS implements Function<Field, String> {

    private final String format;

    public FieldTS(String format) {
        if (format == null) {
            throw new IllegalArgumentException("format cannot be null.");
        }
        this.format = format;
    }

    @Override
    public String apply(Field field) {
        String fieldName = field.getName();
        String type = field.getType().getSimpleName();
        return String.format(format, fieldName, type);
    }
}
