package com.skatepark.shoveit;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
        Objects.requireNonNull(out, "out must not be null");
        this.out = out;
    }

    /**
     * Print all fields and their types.
     *
     * @param clazz class inspected
     * @return Debugger used for chaining.
     */
    public Debugger printTypes(Class clazz) {
        return printTypes(clazz, SIDE_BY_SIDE_FORMAT);
    }

    /**
     * Print all fields and their types with the given format.
     *
     * NOTE: All formats should be in {@link MessageFormat#format(String, Object...)} syntax.
     *
     * Ex: <code>"{0} {1}"</code>, <code>"{1} : {0}"</code>, <code>"{0} -> {1}"</code>, etc
     *
     * @param clazz  class inspected
     * @param format custom format
     * @return Debugger used for chaining.
     */
    public Debugger printTypes(Class clazz, String format) {
        return printTypes(clazz, format, LINE_SEPARATOR);
    }

    /**
     * Print all fields and their types with the given format and delimiter.
     *
     * NOTE: All formats should be in {@link MessageFormat#format(String, Object...)} syntax.
     *
     * Ex: <code>"{0} {1}"</code>, <code>"{1} : {0}"</code>, <code>"{0} -> {1}"</code>, etc
     *
     * @param clazz     class inspected
     * @param format    custom format
     * @param delimiter delimiter
     * @return Debugger used for chaining.
     */
    public Debugger printTypes(Class clazz, String format, String delimiter) {
        return printTypes(clazz, format, delimiter, field -> true);
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
    public <T> Debugger printValues(T object) {
        return printValues(object, EQUAL_FORMAT);
    }

    /**
     * Print all fields and their values with the given format.
     *
     * NOTE: All formats should be in {@link MessageFormat#format(String, Object...)} syntax.
     *
     * Ex: <code>"{0} {1}"</code>, <code>"{1} : {0}"</code>, <code>"{0} -> {1}"</code>, etc
     *
     * @param object object inspected
     * @param format custom format
     * @return Debugger used for chaining.
     */
    public <T> Debugger printValues(T object, String format) {
        return printValues(object, format, LINE_SEPARATOR);
    }

    /**
     * Print all fields and their values with the given format and delimiter.
     *
     * NOTE: All formats should be in {@link MessageFormat#format(String, Object...)} syntax.
     *
     * Ex: <code>"{0} {1}"</code>, <code>"{1} : {0}"</code>, <code>"{0} -> {1}"</code>, etc
     *
     * @param object    object inspected
     * @param format    custom format
     * @param delimiter delimiter
     * @return Debugger used for chaining.
     */
    public <T> Debugger printValues(T object, String format, String delimiter) {
        return printValues(object, format, delimiter, field -> true);
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
    public <T> Debugger printNullValues(T object) {
        return printNullValues(object, EQUAL_FORMAT);
    }

    /**
     * Print all null field values with the given format.
     *
     * NOTE: All formats should be in {@link MessageFormat#format(String, Object...)} syntax.
     *
     * Ex: <code>"{0} {1}"</code>, <code>"{1} : {0}"</code>, <code>"{0} -> {1}"</code>, etc
     *
     * @param object object inspected
     * @param format custom format
     * @return Debugger used for chaining.
     */
    public <T> Debugger printNullValues(T object, String format) {
        return printNullValues(object, format, LINE_SEPARATOR);
    }

    /**
     * Print all null field values with the given format and delimiter.
     *
     * NOTE: All formats should be in {@link MessageFormat#format(String, Object...)} syntax.
     *
     * Ex: <code>"{0} {1}"</code>, <code>"{1} : {0}"</code>, <code>"{0} -> {1}"</code>, etc
     *
     * @param object    object inspected
     * @param format    custom format
     * @param delimiter delimiter
     * @return Debugger used for chaining.
     */
    public <T> Debugger printNullValues(T object, String format, String delimiter) {
        return printValues(object, format, delimiter, field -> getValue(field, object) == null);
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
    public <T> Debugger printSerializableValues(T object) {
        return printSerializableValues(object, EQUAL_FORMAT);
    }

    /**
     * Print all serializable field values with the given format.
     *
     * NOTE: All formats should be in {@link MessageFormat#format(String, Object...)} syntax.
     *
     * Ex: <code>"{0} {1}"</code>, <code>"{1} : {0}"</code>, <code>"{0} -> {1}"</code>, etc
     *
     * @param object object inspected
     * @param format custom format
     * @return Debugger used for chaining.
     */
    public <T> Debugger printSerializableValues(T object, String format) {
        return printSerializableValues(object, format, LINE_SEPARATOR);
    }

    /**
     * Print all serializable field values with the given format and delimiter.
     *
     * NOTE: All formats should be in {@link MessageFormat#format(String, Object...)} syntax.
     *
     * Ex: <code>"{0} {1}"</code>, <code>"{1} : {0}"</code>, <code>"{0} -> {1}"</code>, etc
     *
     * @param object    object inspected
     * @param format    custom format
     * @param delimiter delimiter
     * @return Debugger used for chaining.
     */
    public <T> Debugger printSerializableValues(T object, String format, String delimiter) {
        return printValues(object, format, delimiter, field -> !Modifier.isTransient(field.getModifiers()));
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
    public <T, S> Debugger printMap(Map<T, S> map) {
        return printMap(map, ARROW_FORMAT);
    }

    /**
     * Print map size and their values with the given format.
     *
     * NOTE: All formats should be in {@link MessageFormat#format(String, Object...)} syntax.
     *
     * Ex: <code>"{0} {1}"</code>, <code>"{1} : {0}"</code>, <code>"{0} -> {1}"</code>, etc
     *
     * @param map    map inspected
     * @param format custom format
     * @return Debugger used for chaining.
     */
    public <T, S> Debugger printMap(Map<T, S> map, String format) {
        return printMap(map, format, LINE_SEPARATOR);
    }

    /**
     * Print map size and their values with the given format.
     *
     * NOTE: All formats should be in {@link MessageFormat#format(String, Object...)} syntax.
     *
     * Ex: <code>"{0} {1}"</code>, <code>"{1} : {0}"</code>, <code>"{0} -> {1}"</code>, etc
     *
     * @param map       map inspected
     * @param format    custom format
     * @param delimiter delimiter
     * @return Debugger used for chaining.
     */
    public <T, S> Debugger printMap(Map<T, S> map, String format, String delimiter) {
        Objects.requireNonNull(map, "map must not be null");
        Objects.requireNonNull(format, "format must not be null");

        delimiter = Objects.toString(delimiter, LINE_SEPARATOR);

        return print(map.entrySet().stream()
                .map(entry -> new Object[]{entry.getKey(), entry.getValue()})
                .map(args -> MessageFormat.format(format, args))
                .collect(Collectors.joining(delimiter)));
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
    public <T> Debugger printList(List<T> list) {
        return printList(list, ARROW_FORMAT);
    }

    /**
     * Print list size and their values with the given format.
     *
     * NOTE: All formats should be in {@link MessageFormat#format(String, Object...)} syntax.
     *
     * Ex: <code>"{0} {1}"</code>, <code>"{1} : {0}"</code>, <code>"{0} -> {1}"</code>, etc
     *
     * @param list   list inspected
     * @param format custom format
     * @return Debugger used for chaining.
     */
    public <T> Debugger printList(List<T> list, String format) {
        return printList(list, format, LINE_SEPARATOR);
    }

    /**
     * Print list size and their values with the given format and delimiter.
     *
     * NOTE: All formats should be in {@link MessageFormat#format(String, Object...)} syntax.
     *
     * Ex: <code>"{0} {1}"</code>, <code>"{1} : {0}"</code>, <code>"{0} -> {1}"</code>, etc
     *
     * @param list      list inspected
     * @param format    custom format
     * @param delimiter delimiter
     * @return Debugger used for chaining.
     */
    public <T> Debugger printList(List<T> list, String format, String delimiter) {
        Objects.requireNonNull(list, "list must not be null");
        Objects.requireNonNull(format, "format must not be null");

        delimiter = Objects.toString(delimiter, LINE_SEPARATOR);

        return print(list.stream()
                .map(elem -> new Object[]{list.indexOf(elem), elem})
                .map(args -> MessageFormat.format(format, args))
                .collect(Collectors.joining(delimiter)));
    }

    /**
     * Print array size and their values.
     *
     * NOTE: All formats should be in {@link MessageFormat#format(String, Object...)} syntax.
     *
     * Ex: <code>"{0} {1}"</code>, <code>"{1} : {0}"</code>, <code>"{0} -> {1}"</code>, etc
     *
     * @param array array inspected
     * @return Debugger used for chaining.
     */
    public <T> Debugger printArray(T[] array) {
        return printArray(array, ARROW_FORMAT);
    }

    /**
     * Print array size and their values with the given format.
     *
     * NOTE: All formats should be in {@link MessageFormat#format(String, Object...)} syntax.
     *
     * Ex: <code>"{0} {1}"</code>, <code>"{1} : {0}"</code>, <code>"{0} -> {1}"</code>, etc
     *
     * @param array  array inspected
     * @param format custom format
     * @return Debugger used for chaining.
     */
    public <T> Debugger printArray(T[] array, String format) {
        return printArray(array, format, LINE_SEPARATOR);
    }

    /**
     * Print array size and their values with the given format.
     *
     * NOTE: All formats should be in {@link MessageFormat#format(String, Object...)} syntax.
     *
     * Ex: <code>"{0} {1}"</code>, <code>"{1} : {0}"</code>, <code>"{0} -> {1}"</code>, etc
     *
     * @param array     array inspected
     * @param format    custom format
     * @param delimiter delimiter
     * @return Debugger used for chaining.
     */
    public <T> Debugger printArray(T[] array, String format, String delimiter) {
        Objects.requireNonNull(array, "array must not be null");
        return printList(Arrays.asList(array), format, delimiter);
    }

    /**
     * Print set size and their values.
     *
     * NOTE: All formats should be in {@link MessageFormat#format(String, Object...)} syntax.
     *
     * Ex: <code>"{0} {1}"</code>, <code>"{1} : {0}"</code>, <code>"{0} -> {1}"</code>, etc
     *
     * @param set set inspected
     * @return Debugger used for chaining.
     */
    public <T> Debugger printSet(Set<T> set) {
        return printSet(set, "{0}");
    }

    /**
     * Print set size and their values with the given format.
     *
     * NOTE: All formats should be in {@link MessageFormat#format(String, Object...)} syntax.
     *
     * Ex: <code>"{0} {1}"</code>, <code>"{1} : {0}"</code>, <code>"{0} -> {1}"</code>, etc
     *
     * @param set    set inspected
     * @param format custom format
     * @return Debugger used for chaining.
     */
    public <T> Debugger printSet(Set<T> set, String format) {
       return printSet(set, format, LINE_SEPARATOR);
    }

    /**
     * Print set size and their values with the given format and delimiter.
     *
     * NOTE: All formats should be in {@link MessageFormat#format(String, Object...)} syntax.
     *
     * Ex: <code>"{0} {1}"</code>, <code>"{1} : {0}"</code>, <code>"{0} -> {1}"</code>, etc
     *
     * @param set       set inspected
     * @param format    custom format
     * @param delimiter delimiter
     * @return Debugger used for chaining.
     */
    public <T> Debugger printSet(Set<T> set, String format, String delimiter) {
        Objects.requireNonNull(set, "set must not be null");
        Objects.requireNonNull(format, "format must not be null");

        delimiter = Objects.toString(delimiter, LINE_SEPARATOR);

        return print(set.stream()
                .map(elem -> MessageFormat.format(format, elem))
                .collect(Collectors.joining(delimiter)));
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
        Objects.requireNonNull(value, "value must not be null");
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
     * @param clazz     class inspected
     * @param format    custom format
     * @param delimiter delimiter
     * @param filter    used to filter fields
     * @return Debugger used for chaining.
     */
    private Debugger printTypes(Class clazz, String format, String delimiter, Predicate<Field> filter) {
        Objects.requireNonNull(clazz, "clazz must not be null");
        Objects.requireNonNull(format, "format must not be null");

        delimiter = Objects.toString(delimiter, LINE_SEPARATOR);

        return print(Arrays.stream(clazz.getDeclaredFields())
                .filter(filter)
                .map(field -> new Object[]{field.getType().getSimpleName(), field.getName()})
                .map(args -> MessageFormat.format(format, args))
                .collect(Collectors.joining(delimiter))
        );
    }

    /**
     * Print the filtered list of fields and their values with the given format.
     *
     * NOTE: All formats should be in {@link MessageFormat#format(String, Object...)} syntax.
     *
     * Ex: <code>"{0} {1}"</code>, <code>"{1} : {0}"</code>, <code>"{0} -> {1}"</code>, etc
     *
     * @param object    object inspected
     * @param format    custom format
     * @param delimiter delimiter
     * @param filter    used to filter fields
     * @return Debugger used for chaining.
     */
    private Debugger printValues(Object object, String format, String delimiter, Predicate<Field> filter) {
        Objects.requireNonNull(object, "object must not be null");
        Objects.requireNonNull(format, "format must not be null");

        delimiter = Objects.toString(delimiter, LINE_SEPARATOR);

        return print(Arrays.stream(object.getClass().getDeclaredFields())
                .filter(filter)
                .map(field -> new Object[]{field.getName(), getValue(field, object)})
                .map(args -> MessageFormat.format(format, args))
                .collect(Collectors.joining(delimiter)));
    }

    /**
     * Utility to get field value from object using reflexion.
     *
     * @param field  field
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
