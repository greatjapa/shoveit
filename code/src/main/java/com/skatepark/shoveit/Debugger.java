package com.skatepark.shoveit;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class Debugger {

    private static final String COLON_FORMAT = "%s : %s";

    private static final String EQUAL_FORMAT = "%s = %s";

    private static final String ARROW_FORMAT = "%s -> %s";

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private final OutputStream out;

    public Debugger() {
        this(System.out);
    }

    public Debugger(OutputStream out) {
        if (out == null) {
            throw new IllegalArgumentException("out cannot be null.");
        }
        this.out = out;
    }

    public Debugger printTypes(Class clazz) {
        return printTypes(COLON_FORMAT, clazz);
    }

    public Debugger printTypes(String format, Class clazz) {
        return printTypes(format, clazz, field -> true);
    }

    public Debugger printValues(Object object) {
        return printValues(EQUAL_FORMAT, object);
    }

    public Debugger printValues(String format, Object object) {
        return printValues(format, object, field -> true);
    }

    public Debugger printNullValues(Object object) {
        return printNullValues(EQUAL_FORMAT, object);
    }

    public Debugger printNullValues(String format, Object object) {
        return printValues(format, object, field -> getValue(field, object) == null);
    }

    public Debugger printMap(Map<?, ?> map) {
        return printMap(ARROW_FORMAT, map);
    }

    public Debugger printMap(String format, Map<?, ?> map) {
        if (format == null || map == null) {
            String msg = String.format("format: %s, map: %s", format, map);
            throw new IllegalArgumentException("Unexpected parameters: " + msg);
        }

        println(String.format(EQUAL_FORMAT, "size", map.size()));
        map.entrySet().stream()
                .map(entry -> new Object[]{entry.getKey(), entry.getValue()})
                .map(args -> String.format(format, args))
                .map(line -> line.concat(LINE_SEPARATOR))
                .forEach(this::print);
        return this;
    }

    public Debugger printList(List<?> list) {
        return printList(ARROW_FORMAT, list);
    }

    public Debugger printList(String format, List<?> list) {
        if (format == null || list == null) {
            String msg = String.format("format: %s, list: %s", format, list);
            throw new IllegalArgumentException("Unexpected parameters: " + msg);
        }

        println(String.format(EQUAL_FORMAT, "size", list.size()));
        list.stream()
                .map(elem -> new Object[]{list.indexOf(elem), elem})
                .map(args -> String.format(format, args))
                .map(line -> line.concat(LINE_SEPARATOR))
                .forEach(this::print);
        return this;
    }

    public Debugger println(String value) {
        return print(value).ln();
    }

    public Debugger ln() {
        return print(LINE_SEPARATOR);
    }

    public Debugger print(String value) {
        if (value == null) {
            throw new IllegalArgumentException("value cannot be null.");
        }
        try {
            out.write(value.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    private Debugger printTypes(String format, Class clazz, Predicate<Field> filter) {
        if (format == null || clazz == null) {
            String msg = String.format("format: %s, clazz: %s", format, clazz);
            throw new IllegalArgumentException("Unexpected parameters: " + msg);
        }

        Arrays.stream(clazz.getDeclaredFields())
                .filter(filter)
                .map(field -> new Object[]{field.getName(), field.getType().getSimpleName()})
                .map(args -> String.format(format, args))
                .map(line -> line.concat(LINE_SEPARATOR))
                .forEach(this::print);
        return this;
    }

    private Debugger printValues(String format, Object object, Predicate<Field> filter) {
        if (format == null || object == null) {
            String msg = String.format("format: %s, object: %s, filter: %s", format, object, filter);
            throw new IllegalArgumentException("Unexpected parameters: " + msg);
        }

        Arrays.stream(object.getClass().getDeclaredFields())
                .filter(filter)
                .map(field -> new Object[]{field.getName(), getValue(field, object)})
                .map(args -> String.format(format, args))
                .map(line -> line.concat(LINE_SEPARATOR))
                .forEach(this::print);
        return this;
    }

    private Object getValue(Field field, Object object) {
        try {
            boolean accessible = field.isAccessible();
            field.setAccessible(true);
            Object result = field.get(object);
            field.setAccessible(accessible);
            return result;
        } catch (IllegalAccessException e) {
            return null;
        }
    }
}
