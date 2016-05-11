package com.skatepark.shoveit.field;

import java.lang.reflect.Field;
import java.util.function.Function;

public class FieldToString implements Function<Field, String> {

    private static final String FIELD_FORMAT = "%s : %s";

    @Override
    public String apply(Field field) {
        String fieldName = field.getName();
        String type = field.getType().getSimpleName();
        return String.format(FIELD_FORMAT, fieldName, type);
    }
}
