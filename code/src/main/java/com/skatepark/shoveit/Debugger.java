package com.skatepark.shoveit;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

/**
 * This class provides useful print methods for most common objects like {@link List}, {@link Set},
 * {@link Map} and generic objects.
 *
 * @author Marcelo Oikawa
 */
public class Debugger {

    private static final String SIDE_BY_SIDE_FORMAT = "{0} {1}";

    private static final String COLON_FORMAT = "{0} : {1}";

    private static final String EQUAL_FORMAT = "{0} = {1}";

    private static final String ARROW_FORMAT = "{0} -> {1}";

    private static final String LINE_SEPARATOR = System.lineSeparator();

    /**
     * Output used by print methods.
     */
    private final OutputStream out;

    /**
     * Constructs a debugger using {@link System#out} output.
     */
    public Debugger() {
        this(System.out);
    }

    /**
     * Constructs a debugger using the given output.
     */
    public Debugger(OutputStream out) {
        if (out == null) {
            throw new IllegalArgumentException("out cannot be null.");
        }
        this.out = out;
    }

    /**
     * Print all fields and their types.
     *
     * @param clazz class inspected
     * @return Debugger used for chaining.
     */
    public Debugger printTypes(Class clazz) {
        return printTypes(SIDE_BY_SIDE_FORMAT, clazz);
    }

    /**
     * Print all fields and their types with the given format.
     *
     * NOTE: All formats should be in {@link MessageFormat#format(String, Object...)} syntax.
     *
     * Ex: <code>"{0} {1}"</code>, <code>"{1} : {0}"</code>, <code>"{0} -> {1}"</code>, etc
     *
     * @param format custom format
     * @param clazz  class inspected
     * @return Debugger used for chaining.
     */
    public Debugger printTypes(String format, Class clazz) {
        return printTypes(format, clazz, field -> true);
    }

    /**
     * Print all fields and their values.
     *
     * NOTE: All formats should be in {@link MessageFormat#format(String, Object...)} syntax.
     *
     * Ex: <code>"{0} {1}"</code>, <code>"{1} : {0}"</code>, <code>"{0} -> {1}"</code>, etc
     *
     * @param object object inspected
     * @return Debugger used for chaining.
     */
    public Debugger printValues(Object object) {
        return printValues(EQUAL_FORMAT, object);
    }

    /**
     * Print all fields and their values with the given format.
     *
     * NOTE: All formats should be in {@link MessageFormat#format(String, Object...)} syntax.
     *
     * Ex: <code>"{0} {1}"</code>, <code>"{1} : {0}"</code>, <code>"{0} -> {1}"</code>, etc
     *
     * @param format custom format
     * @param object object inspected
     * @return Debugger used for chaining.
     */
    public Debugger printValues(String format, Object object) {
        return printValues(format, object, field -> true);
    }

    /**
     * Print all null field values.
     *
     * NOTE: All formats should be in {@link MessageFormat#format(String, Object...)} syntax.
     *
     * Ex: <code>"{0} {1}"</code>, <code>"{1} : {0}"</code>, <code>"{0} -> {1}"</code>, etc
     *
     * @param object object inspected
     * @return Debugger used for chaining.
     */
    public Debugger printNullValues(Object object) {
        return printNullValues(EQUAL_FORMAT, object);
    }

    /**
     * Print all null field values with the given format.
     *
     * NOTE: All formats should be in {@link MessageFormat#format(String, Object...)} syntax.
     *
     * Ex: <code>"{0} {1}"</code>, <code>"{1} : {0}"</code>, <code>"{0} -> {1}"</code>, etc
     *
     * @param format custom format
     * @param object object inspected
     * @return Debugger used for chaining.
     */
    public Debugger printNullValues(String format, Object object) {
        return printValues(format, object, field -> getValue(field, object) == null);
    }

    /**
     * Print all serializable field values.
     *
     * NOTE: All formats should be in {@link MessageFormat#format(String, Object...)} syntax.
     *
     * Ex: <code>"{0} {1}"</code>, <code>"{1} : {0}"</code>, <code>"{0} -> {1}"</code>, etc
     *
     * @param object object inspected
     * @return Debugger used for chaining.
     */
    public Debugger printSerializableValues(Object object) {
        return printSerializableValues(EQUAL_FORMAT, object);
    }

    /**
     * Print all serializable field values with the given format.
     *
     * NOTE: All formats should be in {@link MessageFormat#format(String, Object...)} syntax.
     *
     * Ex: <code>"{0} {1}"</code>, <code>"{1} : {0}"</code>, <code>"{0} -> {1}"</code>, etc
     *
     * @param format custom format
     * @param object object inspected
     * @return Debugger used for chaining.
     */
    public Debugger printSerializableValues(String format, Object object) {
        return printValues(format, object, field -> !Modifier.isTransient(field.getModifiers()));
    }

    /**
     * Print map size and their values.
     *
     * NOTE: All formats should be in {@link MessageFormat#format(String, Object...)} syntax.
     *
     * Ex: <code>"{0} {1}"</code>, <code>"{1} : {0}"</code>, <code>"{0} -> {1}"</code>, etc
     *
     * @param map map inspected
     * @return Debugger used for chaining.
     */
    public Debugger printMap(Map<?, ?> map) {
        return printMap(ARROW_FORMAT, map);
    }

    /**
     * Print map size and their values with the given format.
     *
     * NOTE: All formats should be in {@link MessageFormat#format(String, Object...)} syntax.
     *
     * Ex: <code>"{0} {1}"</code>, <code>"{1} : {0}"</code>, <code>"{0} -> {1}"</code>, etc
     *
     * @param format custom format
     * @param map    map inspected
     * @return Debugger used for chaining.
     */
    public Debugger printMap(String format, Map<?, ?> map) {
        if (format == null || map == null) {
            String msg = String.format("format: %s, map: %s", format, map);
            throw new IllegalArgumentException("Unexpected parameters: " + msg);
        }

        println(MessageFormat.format(EQUAL_FORMAT, "map size", map.size()));
        map.entrySet().stream()
                .map(entry -> new Object[]{entry.getKey(), entry.getValue()})
                .map(args -> MessageFormat.format(format, args))
                .map(line -> line.concat(LINE_SEPARATOR))
                .forEach(this::print);
        return this;
    }

    /**
     * Print list size and their values.
     *
     * NOTE: All formats should be in {@link MessageFormat#format(String, Object...)} syntax.
     *
     * Ex: <code>"{0} {1}"</code>, <code>"{1} : {0}"</code>, <code>"{0} -> {1}"</code>, etc
     *
     * @param list list inspected
     * @return Debugger used for chaining.
     */
    public Debugger printList(List<?> list) {
        return printList(ARROW_FORMAT, list);
    }

    /**
     * Print list size and their values with the given format.
     *
     * NOTE: All formats should be in {@link MessageFormat#format(String, Object...)} syntax.
     *
     * Ex: <code>"{0} {1}"</code>, <code>"{1} : {0}"</code>, <code>"{0} -> {1}"</code>, etc
     *
     * @param format custom format
     * @param list   list inspected
     * @return Debugger used for chaining.
     */
    public Debugger printList(String format, List<?> list) {
        if (format == null || list == null) {
            String msg = String.format("format: %s, list: %s", format, list);
            throw new IllegalArgumentException("Unexpected parameters: " + msg);
        }

        println(MessageFormat.format(EQUAL_FORMAT, "list size", list.size()));
        list.stream()
                .map(elem -> new Object[]{list.indexOf(elem), elem})
                .map(args -> MessageFormat.format(format, args))
                .map(line -> line.concat(LINE_SEPARATOR))
                .forEach(this::print);
        return this;
    }

    /**
     * Print {@link String} value with {@link System#lineSeparator}.
     *
     * @param value value as {@link String}
     * @return Debugger used for chaining.
     */
    public Debugger println(String value) {
        return print(value).ln();
    }

    /**
     * Print line separator defined by {@link System#lineSeparator}.
     *
     * @return Debugger used for chaining.
     */
    public Debugger ln() {
        return print(LINE_SEPARATOR);
    }

    /**
     * Print {@link String} value.
     *
     * @param value value as {@link String}
     * @return Debugger used for chaining.
     */
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

    /**
     * Print the filtered list of fields and their types with the given format.
     *
     * NOTE: All formats should be in {@link MessageFormat#format(String, Object...)} syntax.
     *
     * Ex: <code>"{0} {1}"</code>, <code>"{1} : {0}"</code>, <code>"{0} -> {1}"</code>, etc
     *
     * @param format custom format
     * @param clazz  class inspected
     * @param filter used to filter fields
     * @return Debugger used for chaining.
     */
    private Debugger printTypes(String format, Class clazz, Predicate<Field> filter) {
        if (format == null || clazz == null) {
            String msg = String.format("format: %s, clazz: %s", format, clazz);
            throw new IllegalArgumentException("Unexpected parameters: " + msg);
        }

        Arrays.stream(clazz.getDeclaredFields())
                .filter(filter)
                .map(field -> new Object[]{field.getType().getSimpleName(), field.getName()})
                .map(args -> MessageFormat.format(format, args))
                .map(line -> line.concat(LINE_SEPARATOR))
                .forEach(this::print);
        return this;
    }

    /**
     * Print the filtered list of fields and their values with the given format.
     *
     * NOTE: All formats should be in {@link MessageFormat#format(String, Object...)} syntax.
     *
     * Ex: <code>"{0} {1}"</code>, <code>"{1} : {0}"</code>, <code>"{0} -> {1}"</code>, etc
     *
     * @param format custom format
     * @param object  object inspected
     * @param filter used to filter fields
     * @return Debugger used for chaining.
     */
    private Debugger printValues(String format, Object object, Predicate<Field> filter) {
        if (format == null || object == null) {
            String msg = String.format("format: %s, object: %s, filter: %s", format, object, filter);
            throw new IllegalArgumentException("Unexpected parameters: " + msg);
        }

        Arrays.stream(object.getClass().getDeclaredFields())
                .filter(filter)
                .map(field -> new Object[]{field.getName(), getValue(field, object)})
                .map(args -> MessageFormat.format(format, args))
                .map(line -> line.concat(LINE_SEPARATOR))
                .forEach(this::print);
        return this;
    }

    /**
     * Utility to get field value from object using reflexion.
     *
     * @param field field
     * @param object object
     * @return field value
     */
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
