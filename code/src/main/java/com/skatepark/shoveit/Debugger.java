package com.skatepark.shoveit;

import com.skatepark.shoveit.field.FieldTS;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.stream.Collectors;

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
        return types(clazz, DEFAULT_FORMAT);
    }

    public Debugger types(Class clazz, String format) {
        if (clazz == null || format == null) {
            String msg = String.format("clazz: %s , format: %s", clazz, format);
            throw new IllegalArgumentException("Unexpected parameters: " + msg);
        }

        String result = Arrays.stream(clazz.getDeclaredFields())
                .map(field -> FieldTS.type(format, field))
                .collect(Collectors.joining(LINE_SEPARATOR, "", LINE_SEPARATOR));
        write(result);
        return this;
    }

    public Debugger values(Object object) {
        return values(object, DEFAULT_FORMAT);
    }

    public Debugger values(Object object, String format) {
        if (object == null || format == null) {
            String msg = String.format("object: %s , format: %s", object, format);
            throw new IllegalArgumentException("Unexpected parameters: " + msg);
        }
        String result = Arrays.stream(object.getClass().getDeclaredFields())
                .map(field -> FieldTS.value(format, field, object))
                .collect(Collectors.joining(LINE_SEPARATOR, "", LINE_SEPARATOR));
        write(result);
        return this;
    }

    public Debugger ln(){
        write(LINE_SEPARATOR);
        return this;
    }

    private void write(String value) {
        try {
            out.write(value.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
