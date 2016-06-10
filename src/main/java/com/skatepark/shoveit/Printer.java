package com.skatepark.shoveit;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collection;
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
public class Printer {

    public static final String LN = System.lineSeparator();

    public static final String SIDE_BY_SIDE_FORMAT = "{0} {1}";

    public static final String COLON_FORMAT = "{0} : {1}";

    public static final String EQUAL_FORMAT = "{0} = {1}";

    public static final String ARROW_FORMAT = "{0} -> {1}";

    public static final Predicate<Field> ALL = f -> true;

    public static final Predicate<Field> TRANSIENT = f -> Modifier.isTransient(f.getModifiers());

    public static final Predicate<Field> STATIC = f -> Modifier.isStatic(f.getModifiers());

    public static final Predicate<Field> PUBLIC = f -> Modifier.isPublic(f.getModifiers());

    public static final Predicate<Field> PROTECTED = f -> Modifier.isProtected(f.getModifiers());

    public static final Predicate<Field> FINAL = f -> Modifier.isFinal(f.getModifiers());

    public static final Predicate<Field> PRIVATE = f -> Modifier.isPrivate(f.getModifiers());

    public static final Predicate<Field> VOLATILE = f -> Modifier.isVolatile(f.getModifiers());

    public static final Predicate<Field> SYNCHRONIZED = f -> Modifier.isSynchronized(f.getModifiers());

    /**
     * Output used by print methods.
     */
    private final OutputStream out;

    /**
     * Constructs a debugger using {@link System#out} output.
     */
    public Printer() {
        this(System.out);
    }

    /**
     * Constructs a debugger using the given output.
     */
    public Printer(OutputStream out) {
        Objects.requireNonNull(out, "out must not be null");
        this.out = out;
    }

    /**
     * Print all fields and their types.
     *
     * @param clazz class inspected
     * @return Printer used for chaining.
     */
    public Printer printTypes(Class clazz) {
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
     * @return Printer used for chaining.
     */
    public Printer printTypes(Class clazz, String format) {
        return printTypes(clazz, format, LN);
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
     * @return Printer used for chaining.
     */
    public Printer printTypes(Class clazz, String format, String delimiter) {
        return printTypes(clazz, format, delimiter, ALL);
    }

    /**
     * Print the filtered list of fields and their types with the given format and delimiter.
     *
     * NOTE: All formats should be in {@link MessageFormat#format(String, Object...)} syntax.
     *
     * Ex: <code>"{0} {1}"</code>, <code>"{1} : {0}"</code>, <code>"{0} -> {1}"</code>, etc
     *
     * @param clazz     class inspected
     * @param format    custom format
     * @param delimiter delimiter
     * @param filter    used to filter fields
     * @return Printer used for chaining.
     */
    public Printer printTypes(Class clazz, String format, String delimiter, Predicate<Field> filter) {
        Objects.requireNonNull(clazz, "clazz must not be null");
        Objects.requireNonNull(format, "format must not be null");

        delimiter = Objects.toString(delimiter, LN);
        filter = Objects.nonNull(filter) ? filter : ALL;

        return print(Arrays.stream(clazz.getDeclaredFields())
                .filter(filter)
                .map(field -> new Object[]{field.getType().getSimpleName(), field.getName()})
                .map(args -> MessageFormat.format(format, args))
                .collect(Collectors.joining(delimiter))
        );
    }

    /**
     * Print all fields and their values.
     *
     * NOTE: All formats should be in {@link MessageFormat#format(String, Object...)} syntax.
     *
     * Ex: <code>"{0} {1}"</code>, <code>"{1} : {0}"</code>, <code>"{0} -> {1}"</code>, etc
     *
     * @param object object inspected
     * @return Printer used for chaining.
     */
    public <T> Printer printValues(T object) {
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
     * @return Printer used for chaining.
     */
    public <T> Printer printValues(T object, String format) {
        return printValues(object, format, LN);
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
     * @return Printer used for chaining.
     */
    public <T> Printer printValues(T object, String format, String delimiter) {
        return printValues(object, format, delimiter, ALL);
    }

    /**
     * Print the filtered list of fields and their values with the given format and delimiter.
     *
     * NOTE: All formats should be in {@link MessageFormat#format(String, Object...)} syntax.
     *
     * Ex: <code>"{0} {1}"</code>, <code>"{1} : {0}"</code>, <code>"{0} -> {1}"</code>, etc
     *
     * @param object    object inspected
     * @param format    custom format
     * @param delimiter delimiter
     * @param filter    used to filter fields
     * @return Printer used for chaining.
     */
    public Printer printValues(Object object, String format, String delimiter, Predicate<Field> filter) {
        Objects.requireNonNull(object, "object must not be null");
        Objects.requireNonNull(format, "format must not be null");

        delimiter = Objects.toString(delimiter, LN);
        filter = Objects.nonNull(filter) ? filter : field -> true;

        return print(Arrays.stream(object.getClass().getDeclaredFields())
                .filter(filter)
                .map(field -> new Object[]{field.getName(), getValue(field, object)})
                .map(args -> MessageFormat.format(format, args))
                .collect(Collectors.joining(delimiter)));
    }

    /**
     * Print all null field values.
     *
     * NOTE: All formats should be in {@link MessageFormat#format(String, Object...)} syntax.
     *
     * Ex: <code>"{0} {1}"</code>, <code>"{1} : {0}"</code>, <code>"{0} -> {1}"</code>, etc
     *
     * @param object object inspected
     * @return Printer used for chaining.
     */
    public <T> Printer printNullValues(T object) {
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
     * @return Printer used for chaining.
     */
    public <T> Printer printNullValues(T object, String format) {
        return printNullValues(object, format, LN);
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
     * @return Printer used for chaining.
     */
    public <T> Printer printNullValues(T object, String format, String delimiter) {
        return printValues(object, format, delimiter, field -> Objects.isNull(getValue(field, object)));
    }

    /**
     * Print map and their values.
     *
     * NOTE: All formats should be in {@link MessageFormat#format(String, Object...)} syntax.
     *
     * Ex: <code>"{0} {1}"</code>, <code>"{1} : {0}"</code>, <code>"{0} -> {1}"</code>, etc
     *
     * @param map map inspected
     * @return Printer used for chaining.
     */
    public <T, S> Printer printMap(Map<T, S> map) {
        return printMap(map, ARROW_FORMAT);
    }

    /**
     * Print map and their values with the given format.
     *
     * NOTE: All formats should be in {@link MessageFormat#format(String, Object...)} syntax.
     *
     * Ex: <code>"{0} {1}"</code>, <code>"{1} : {0}"</code>, <code>"{0} -> {1}"</code>, etc
     *
     * @param map    map inspected
     * @param format custom format
     * @return Printer used for chaining.
     */
    public <T, S> Printer printMap(Map<T, S> map, String format) {
        return printMap(map, format, LN);
    }

    /**
     * Print map and their values with the given format.
     *
     * NOTE: All formats should be in {@link MessageFormat#format(String, Object...)} syntax.
     *
     * Ex: <code>"{0} {1}"</code>, <code>"{1} : {0}"</code>, <code>"{0} -> {1}"</code>, etc
     *
     * @param map       map inspected
     * @param format    custom format
     * @param delimiter delimiter
     * @return Printer used for chaining.
     */
    public <T, S> Printer printMap(Map<T, S> map, String format, String delimiter) {
        Objects.requireNonNull(map, "map must not be null");
        Objects.requireNonNull(format, "format must not be null");

        delimiter = Objects.toString(delimiter, LN);

        return print(map.entrySet().stream()
                .map(entry -> new Object[]{entry.getKey(), entry.getValue()})
                .map(args -> MessageFormat.format(format, args))
                .collect(Collectors.joining(delimiter)));
    }

    /**
     * Print list and their values.
     *
     * NOTE: All formats should be in {@link MessageFormat#format(String, Object...)} syntax.
     *
     * Ex: <code>"{0} {1}"</code>, <code>"{1} : {0}"</code>, <code>"{0} -> {1}"</code>, etc
     *
     * @param list list inspected
     * @return Printer used for chaining.
     */
    public <T> Printer printList(List<T> list) {
        return printList(list, ARROW_FORMAT);
    }

    /**
     * Print list and their values with the given format.
     *
     * NOTE: All formats should be in {@link MessageFormat#format(String, Object...)} syntax.
     *
     * Ex: <code>"{0} {1}"</code>, <code>"{1} : {0}"</code>, <code>"{0} -> {1}"</code>, etc
     *
     * @param list   list inspected
     * @param format custom format
     * @return Printer used for chaining.
     */
    public <T> Printer printList(List<T> list, String format) {
        return printList(list, format, LN);
    }

    /**
     * Print list and their values with the given format and delimiter.
     *
     * NOTE: All formats should be in {@link MessageFormat#format(String, Object...)} syntax.
     *
     * Ex: <code>"{0} {1}"</code>, <code>"{1} : {0}"</code>, <code>"{0} -> {1}"</code>, etc
     *
     * @param list      list inspected
     * @param format    custom format
     * @param delimiter delimiter
     * @return Printer used for chaining.
     */
    public <T> Printer printList(List<T> list, String format, String delimiter) {
        Objects.requireNonNull(list, "list must not be null");
        Objects.requireNonNull(format, "format must not be null");

        delimiter = Objects.toString(delimiter, LN);

        return print(list.stream()
                .map(elem -> new Object[]{list.indexOf(elem), elem})
                .map(args -> MessageFormat.format(format, args))
                .collect(Collectors.joining(delimiter)));
    }

    /**
     * Print array and their values.
     *
     * NOTE: All formats should be in {@link MessageFormat#format(String, Object...)} syntax.
     *
     * Ex: <code>"{0} {1}"</code>, <code>"{1} : {0}"</code>, <code>"{0} -> {1}"</code>, etc
     *
     * @param array array inspected
     * @return Printer used for chaining.
     */
    public <T> Printer printArray(T[] array) {
        return printArray(array, ARROW_FORMAT);
    }

    /**
     * Print array and their values with the given format.
     *
     * NOTE: All formats should be in {@link MessageFormat#format(String, Object...)} syntax.
     *
     * Ex: <code>"{0} {1}"</code>, <code>"{1} : {0}"</code>, <code>"{0} -> {1}"</code>, etc
     *
     * @param array  array inspected
     * @param format custom format
     * @return Printer used for chaining.
     */
    public <T> Printer printArray(T[] array, String format) {
        return printArray(array, format, LN);
    }

    /**
     * Print array and their values with the given format.
     *
     * NOTE: All formats should be in {@link MessageFormat#format(String, Object...)} syntax.
     *
     * Ex: <code>"{0} {1}"</code>, <code>"{1} : {0}"</code>, <code>"{0} -> {1}"</code>, etc
     *
     * @param array     array inspected
     * @param format    custom format
     * @param delimiter delimiter
     * @return Printer used for chaining.
     */
    public <T> Printer printArray(T[] array, String format, String delimiter) {
        Objects.requireNonNull(array, "array must not be null");
        return printList(Arrays.asList(array), format, delimiter);
    }

    /**
     * Print set and their values.
     *
     * NOTE: All formats should be in {@link MessageFormat#format(String, Object...)} syntax.
     *
     * Ex: <code>"{0} {1}"</code>, <code>"{1} : {0}"</code>, <code>"{0} -> {1}"</code>, etc
     *
     * @param set set inspected
     * @return Printer used for chaining.
     */
    public <T> Printer printSet(Set<T> set) {
        return printSet(set, "{0}");
    }

    /**
     * Print set and their values with the given format.
     *
     * NOTE: All formats should be in {@link MessageFormat#format(String, Object...)} syntax.
     *
     * Ex: <code>"{0} {1}"</code>, <code>"{1} : {0}"</code>, <code>"{0} -> {1}"</code>, etc
     *
     * @param set    set inspected
     * @param format custom format
     * @return Printer used for chaining.
     */
    public <T> Printer printSet(Set<T> set, String format) {
        return printSet(set, format, LN);
    }

    /**
     * Print set and their values with the given format and delimiter.
     *
     * NOTE: All formats should be in {@link MessageFormat#format(String, Object...)} syntax.
     *
     * Ex: <code>"{0} {1}"</code>, <code>"{1} : {0}"</code>, <code>"{0} -> {1}"</code>, etc
     *
     * @param set       set inspected
     * @param format    custom format
     * @param delimiter delimiter
     * @return Printer used for chaining.
     */
    public <T> Printer printSet(Set<T> set, String format, String delimiter) {
        Objects.requireNonNull(set, "set must not be null");
        Objects.requireNonNull(format, "format must not be null");

        delimiter = Objects.toString(delimiter, LN);

        return print(set.stream()
                .map(elem -> MessageFormat.format(format, elem))
                .collect(Collectors.joining(delimiter)));
    }

    /**
     * Print array size with the given format.
     *
     * NOTE: All formats should be in {@link MessageFormat#format(String, Object...)} syntax.
     *
     * Ex: <code>"{0} {1}"</code>, <code>"{1} : {0}"</code>, <code>"{0} -> {1}"</code>, etc
     *
     * @param array array inspected
     * @return Printer used for chaining.
     */
    public <T> Printer printSize(T[] array) {
        return printSize(array, SIDE_BY_SIDE_FORMAT);
    }

    /**
     * Print array size with the given format.
     *
     * NOTE: All formats should be in {@link MessageFormat#format(String, Object...)} syntax.
     *
     * Ex: <code>"{0} {1}"</code>, <code>"{1} : {0}"</code>, <code>"{0} -> {1}"</code>, etc
     *
     * @param array  array inspected
     * @param format custom format
     * @return Printer used for chaining.
     */
    public <T> Printer printSize(T[] array, String format) {
        return printSize(Arrays.asList(array), format);
    }

    /**
     * Print collection size with the given format.
     *
     * NOTE: All formats should be in {@link MessageFormat#format(String, Object...)} syntax.
     *
     * Ex: <code>"{0} {1}"</code>, <code>"{1} : {0}"</code>, <code>"{0} -> {1}"</code>, etc
     *
     * @param collection collection inspected
     * @return Printer used for chaining.
     */
    public <T> Printer printSize(Collection<T> collection) {
        return printSize(collection, SIDE_BY_SIDE_FORMAT);
    }

    /**
     * Print collection size with the given format.
     *
     * NOTE: All formats should be in {@link MessageFormat#format(String, Object...)} syntax.
     *
     * Ex: <code>"{0} {1}"</code>, <code>"{1} : {0}"</code>, <code>"{0} -> {1}"</code>, etc
     *
     * @param collection collection inspected
     * @param format     custom format
     * @return Printer used for chaining.
     */
    public <T> Printer printSize(Collection<T> collection, String format) {
        Objects.requireNonNull(collection, "collection must not be null");
        Objects.requireNonNull(format, "format must not be null");

        return print(MessageFormat.format(format, "size", collection.size()));
    }

    /**
     * Print {@link String} value with {@link System#lineSeparator}.
     *
     * @param value value as {@link String}
     * @return Printer used for chaining.
     */
    public Printer println(String value) {
        return print(value).ln();
    }

    /**
     * Print line separator defined by {@link System#lineSeparator}.
     *
     * @return Printer used for chaining.
     */
    public Printer ln() {
        return print(LN);
    }

    /**
     * Print {@link String} value.
     *
     * @param value value as {@link String}
     * @return Printer used for chaining.
     */
    public Printer print(String value) {
        Objects.requireNonNull(value, "value must not be null");
        try {
            out.write(value.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * Sleep.
     *
     * @param miliseconds miliseconds.
     * @return Printer used for chaining.
     */
    public Printer sleep(long miliseconds) {
        try {
            Thread.sleep(miliseconds);
        } catch (InterruptedException e) {
            // do nothing
        }
        return this;
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
