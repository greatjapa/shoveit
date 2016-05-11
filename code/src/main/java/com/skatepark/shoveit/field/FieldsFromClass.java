package com.skatepark.shoveit.field;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FieldsFromClass implements Function<Class<?>, String> {

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    @Override
    public String apply(Class<?> clazz) {
        return clazz == null ? "" : Arrays.stream(clazz.getDeclaredFields())
                .map(new FieldToString())
                .collect(Collectors.joining(LINE_SEPARATOR));
    }
}
