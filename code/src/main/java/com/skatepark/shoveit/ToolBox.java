package com.skatepark.shoveit;

import com.skatepark.shoveit.field.FieldTS;
import com.skatepark.shoveit.field.FieldValueTS;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ToolBox {

    private static final String DEFAULT_FORMAT = "%s : %s";

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");


    public static String types(Class clazz) {
        return types(clazz, DEFAULT_FORMAT);
    }

    public static String types(Class clazz, String format) {
        return clazz == null ? "" : Arrays.stream(clazz.getDeclaredFields())
                .map(new FieldTS(format))
                .collect(Collectors.joining(LINE_SEPARATOR));
    }

    public static String values(Object object) {
        return values(object, DEFAULT_FORMAT);
    }

    public static String values(Object object, String format) {
        FieldValueTS fieldValueTs = new FieldValueTS(format);
        return object == null ? "" : Arrays.stream(object.getClass().getDeclaredFields())
                .map(field -> fieldValueTs.get(field, object))
                .collect(Collectors.joining(LINE_SEPARATOR));
    }
}
