package com.skatepark.shoveit;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.function.Predicate;

public class Debugger {

    private static final String DEFAULT_FORMAT = "%s : %s";

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

    public Debugger types(Class clazz) {
        return types(DEFAULT_FORMAT, clazz);
    }

    public Debugger types(String format, Class clazz) {
        return types(format, clazz, field -> true);
    }

    public Debugger values(Object object) {
        return values(DEFAULT_FORMAT, object);
    }

    public Debugger valuesNull(Object object) {
        return valuesNull(DEFAULT_FORMAT, object);
    }

    public Debugger values(String format, Object object) {
        return values(format, object, field -> true);
    }

    public Debugger valuesNull(String format, Object object) {
        return values(format, object, field -> getFieldValue(field, object) == null);
    }

    public Debugger ln() {
        return write(LINE_SEPARATOR);
    }

    public Debugger write(String value) {
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

    private Debugger types(String format, Class clazz, Predicate<Field> filter) {
        if (format == null || clazz == null) {
            String msg = String.format("format: %s, clazz: %s", format, clazz);
            throw new IllegalArgumentException("Unexpected parameters: " + msg);
        }

        Arrays.stream(clazz.getDeclaredFields())
                .filter(filter)
                .map(field -> String.format(format, field.getName(), field.getType().getSimpleName()))
                .map(line -> line.concat(LINE_SEPARATOR))
                .forEach(this::write);
        return this;
    }

    private Debugger values(String format, Object object, Predicate<Field> filter) {
        if (format == null || object == null) {
            String msg = String.format("format: %s, object: %s, filter: %s", format, object, filter);
            throw new IllegalArgumentException("Unexpected parameters: " + msg);
        }

        Arrays.stream(object.getClass().getDeclaredFields())
                .filter(filter)
                .map(field -> String.format(format, field.getName(), getFieldValue(field, object)))
                .map(line -> line.concat(LINE_SEPARATOR))
                .forEach(this::write);
        return this;
    }

    private Object getFieldValue(Field field, Object object) {
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
