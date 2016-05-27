package com.skatepark.shoveit;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.function.Predicate;

public class _ {

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
}
