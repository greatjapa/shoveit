package com.skatepark.shoveit;

import com.skatepark.shoveit.pojo.Person;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DebuggerTest {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    @Test
    public void testPrintList() {
        List<String> list = IntStream.range(0, 10)
                .mapToObj(i -> "value" + i)
                .collect(Collectors.toList());

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Debugger(out).printList(list);
        String result = new String(out.toByteArray());

        StringBuilder expected = new StringBuilder();
        expected.append("#size = 10").append(LINE_SEPARATOR);
        expected.append("0 -> value0").append(LINE_SEPARATOR);
        expected.append("1 -> value1").append(LINE_SEPARATOR);
        expected.append("2 -> value2").append(LINE_SEPARATOR);
        expected.append("3 -> value3").append(LINE_SEPARATOR);
        expected.append("4 -> value4").append(LINE_SEPARATOR);
        expected.append("5 -> value5").append(LINE_SEPARATOR);
        expected.append("6 -> value6").append(LINE_SEPARATOR);
        expected.append("7 -> value7").append(LINE_SEPARATOR);
        expected.append("8 -> value8").append(LINE_SEPARATOR);
        expected.append("9 -> value9").append(LINE_SEPARATOR);

        Assert.assertEquals(expected.toString(), result);
    }

    @Test
    public void testPrintListWithCustomFormat() {
        List<String> list = IntStream.range(0, 10)
                .mapToObj(i -> "value" + i)
                .collect(Collectors.toList());

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Debugger(out).printList(list, "[{0}] = {1}");
        String result = new String(out.toByteArray());

        StringBuilder expected = new StringBuilder();
        expected.append("#size = 10").append(LINE_SEPARATOR);
        expected.append("[0] = value0").append(LINE_SEPARATOR);
        expected.append("[1] = value1").append(LINE_SEPARATOR);
        expected.append("[2] = value2").append(LINE_SEPARATOR);
        expected.append("[3] = value3").append(LINE_SEPARATOR);
        expected.append("[4] = value4").append(LINE_SEPARATOR);
        expected.append("[5] = value5").append(LINE_SEPARATOR);
        expected.append("[6] = value6").append(LINE_SEPARATOR);
        expected.append("[7] = value7").append(LINE_SEPARATOR);
        expected.append("[8] = value8").append(LINE_SEPARATOR);
        expected.append("[9] = value9").append(LINE_SEPARATOR);

        Assert.assertEquals(expected.toString(), result);
    }

    @Test
    public void testPrintArray() {
        Object[] array = IntStream.range(0, 10)
                .mapToObj(i -> "value" + i)
                .toArray();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Debugger(out).printArray(array);
        String result = new String(out.toByteArray());

        StringBuilder expected = new StringBuilder();
        expected.append("#size = 10").append(LINE_SEPARATOR);
        expected.append("0 -> value0").append(LINE_SEPARATOR);
        expected.append("1 -> value1").append(LINE_SEPARATOR);
        expected.append("2 -> value2").append(LINE_SEPARATOR);
        expected.append("3 -> value3").append(LINE_SEPARATOR);
        expected.append("4 -> value4").append(LINE_SEPARATOR);
        expected.append("5 -> value5").append(LINE_SEPARATOR);
        expected.append("6 -> value6").append(LINE_SEPARATOR);
        expected.append("7 -> value7").append(LINE_SEPARATOR);
        expected.append("8 -> value8").append(LINE_SEPARATOR);
        expected.append("9 -> value9");

        Assert.assertEquals(expected.toString(), result);
    }

    @Test
    public void testPrintArrayWithCustomFormat() {
        Object[] array = IntStream.range(0, 10)
                .mapToObj(i -> "value" + i)
                .toArray();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Debugger(out).printArray(array, "[{0}] = {1}");
        String result = new String(out.toByteArray());

        StringBuilder expected = new StringBuilder();
        expected.append("#size = 10").append(LINE_SEPARATOR);
        expected.append("[0] = value0").append(LINE_SEPARATOR);
        expected.append("[1] = value1").append(LINE_SEPARATOR);
        expected.append("[2] = value2").append(LINE_SEPARATOR);
        expected.append("[3] = value3").append(LINE_SEPARATOR);
        expected.append("[4] = value4").append(LINE_SEPARATOR);
        expected.append("[5] = value5").append(LINE_SEPARATOR);
        expected.append("[6] = value6").append(LINE_SEPARATOR);
        expected.append("[7] = value7").append(LINE_SEPARATOR);
        expected.append("[8] = value8").append(LINE_SEPARATOR);
        expected.append("[9] = value9");

        Assert.assertEquals(expected.toString(), result);
    }

    @Test
    public void testPrintMap() {
        Map<Integer, String> map = IntStream.range(0, 10)
                .mapToObj(i -> i)
                .collect(Collectors.toMap(Integer::intValue, i -> "value" + i));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Debugger(out).printMap(map);
        String result = new String(out.toByteArray());

        StringBuilder expected = new StringBuilder();
        expected.append("#size = 10").append(LINE_SEPARATOR);
        expected.append("0 -> value0").append(LINE_SEPARATOR);
        expected.append("1 -> value1").append(LINE_SEPARATOR);
        expected.append("2 -> value2").append(LINE_SEPARATOR);
        expected.append("3 -> value3").append(LINE_SEPARATOR);
        expected.append("4 -> value4").append(LINE_SEPARATOR);
        expected.append("5 -> value5").append(LINE_SEPARATOR);
        expected.append("6 -> value6").append(LINE_SEPARATOR);
        expected.append("7 -> value7").append(LINE_SEPARATOR);
        expected.append("8 -> value8").append(LINE_SEPARATOR);
        expected.append("9 -> value9");

        Assert.assertEquals(expected.toString(), result);
    }

    @Test
    public void testPrintMapWithCustomFormat() {
        Map<Integer, String> map = IntStream.range(0, 10)
                .mapToObj(i -> i)
                .collect(Collectors.toMap(Integer::intValue, i -> "value" + i));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Debugger(out).printMap(map, "{0} has {1}");
        String result = new String(out.toByteArray());

        StringBuilder expected = new StringBuilder();
        expected.append("#size = 10").append(LINE_SEPARATOR);
        expected.append("0 has value0").append(LINE_SEPARATOR);
        expected.append("1 has value1").append(LINE_SEPARATOR);
        expected.append("2 has value2").append(LINE_SEPARATOR);
        expected.append("3 has value3").append(LINE_SEPARATOR);
        expected.append("4 has value4").append(LINE_SEPARATOR);
        expected.append("5 has value5").append(LINE_SEPARATOR);
        expected.append("6 has value6").append(LINE_SEPARATOR);
        expected.append("7 has value7").append(LINE_SEPARATOR);
        expected.append("8 has value8").append(LINE_SEPARATOR);
        expected.append("9 has value9");

        Assert.assertEquals(expected.toString(), result);
    }

    @Test
    public void testPrintSet() {
        Set<String> set = IntStream.range(0, 10)
                .mapToObj(i -> "value" + i)
                .collect(Collectors.toCollection(TreeSet::new));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Debugger(out).printSet(set);
        String result = new String(out.toByteArray());

        StringBuilder expected = new StringBuilder();
        expected.append("#size = 10").append(LINE_SEPARATOR);
        expected.append("value0").append(LINE_SEPARATOR);
        expected.append("value1").append(LINE_SEPARATOR);
        expected.append("value2").append(LINE_SEPARATOR);
        expected.append("value3").append(LINE_SEPARATOR);
        expected.append("value4").append(LINE_SEPARATOR);
        expected.append("value5").append(LINE_SEPARATOR);
        expected.append("value6").append(LINE_SEPARATOR);
        expected.append("value7").append(LINE_SEPARATOR);
        expected.append("value8").append(LINE_SEPARATOR);
        expected.append("value9").append(LINE_SEPARATOR);

        Assert.assertEquals(expected.toString(), result);
    }

    @Test
    public void testPrintSetWithCustomFormat() {
        Set<String> set = IntStream.range(0, 10)
                .mapToObj(i -> "value" + i)
                .collect(Collectors.toCollection(TreeSet::new));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Debugger(out).printSet(set, "~{0}");
        String result = new String(out.toByteArray());

        StringBuilder expected = new StringBuilder();
        expected.append("#size = 10").append(LINE_SEPARATOR);
        expected.append("~value0").append(LINE_SEPARATOR);
        expected.append("~value1").append(LINE_SEPARATOR);
        expected.append("~value2").append(LINE_SEPARATOR);
        expected.append("~value3").append(LINE_SEPARATOR);
        expected.append("~value4").append(LINE_SEPARATOR);
        expected.append("~value5").append(LINE_SEPARATOR);
        expected.append("~value6").append(LINE_SEPARATOR);
        expected.append("~value7").append(LINE_SEPARATOR);
        expected.append("~value8").append(LINE_SEPARATOR);
        expected.append("~value9").append(LINE_SEPARATOR);

        Assert.assertEquals(expected.toString(), result);
    }

    @Test
    public void testPrintTypes() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Debugger(out).printTypes(Person.class);
        String result = new String(out.toByteArray());

        StringBuilder expected = new StringBuilder();
        expected.append("String name").append(LINE_SEPARATOR);
        expected.append("String lastName").append(LINE_SEPARATOR);
        expected.append("int age").append(LINE_SEPARATOR);
        expected.append("List friends");

        Assert.assertEquals(expected.toString(), result);
    }

    @Test
    public void testPrintTypesWithCustomFormat() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Debugger(out).printTypes(Person.class, "{1} : {0}");
        String result = new String(out.toByteArray());

        StringBuilder expected = new StringBuilder();
        expected.append("name : String").append(LINE_SEPARATOR);
        expected.append("lastName : String").append(LINE_SEPARATOR);
        expected.append("age : int").append(LINE_SEPARATOR);
        expected.append("friends : List");

        Assert.assertEquals(expected.toString(), result);
    }

    @Test
    public void testPrintValues() {
        Person person = new Person("Will", "Smith", 40, null);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Debugger(out).printValues(person);
        String result = new String(out.toByteArray());

        StringBuilder expected = new StringBuilder();
        expected.append("name = Will").append(LINE_SEPARATOR);
        expected.append("lastName = Smith").append(LINE_SEPARATOR);
        expected.append("age = 40").append(LINE_SEPARATOR);
        expected.append("friends = null");

        Assert.assertEquals(expected.toString(), result);
    }

    @Test
    public void testPrintValuesWithCustomFormat() {
        Person person = new Person("Will", "Smith", 40, new ArrayList<>());

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Debugger(out).printValues(person, "{0} <- {1}");
        String result = new String(out.toByteArray());

        StringBuilder expected = new StringBuilder();
        expected.append("name <- Will").append(LINE_SEPARATOR);
        expected.append("lastName <- Smith").append(LINE_SEPARATOR);
        expected.append("age <- 40").append(LINE_SEPARATOR);
        expected.append("friends <- []");

        Assert.assertEquals(expected.toString(), result);
    }

    @Test
    public void testPrintNullValues() {
        Person person = new Person("Will", "Smith", 40, null);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Debugger(out).printNullValues(person);
        String result = new String(out.toByteArray());

        Assert.assertEquals("friends = null", result);
    }

    @Test
    public void testPrintNullValuesWithCustomFormat() {
        Person person = new Person("Will", "Smith", 40, null);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Debugger(out).printNullValues(person, "{0} is {1}");
        String result = new String(out.toByteArray());

        Assert.assertEquals("friends is null", result);
    }

    @Test
    public void testPrintSerializableValues() {
        Person person = new Person("Will", "Smith", 40, null);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Debugger(out).printSerializableValues(person);
        String result = new String(out.toByteArray());

        StringBuilder expected = new StringBuilder();
        expected.append("name = Will").append(LINE_SEPARATOR);
        expected.append("age = 40").append(LINE_SEPARATOR);
        expected.append("friends = null");

        Assert.assertEquals(expected.toString(), result);
    }

    @Test
    public void testPrintSerializableValuesWithCustomFormat() {
        Person person = new Person("Will", "Smith", 40, new ArrayList<>());

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Debugger(out).printSerializableValues(person, "{0} is {1}");
        String result = new String(out.toByteArray());

        StringBuilder expected = new StringBuilder();
        expected.append("name is Will").append(LINE_SEPARATOR);
        expected.append("age is 40").append(LINE_SEPARATOR);
        expected.append("friends is []");

        Assert.assertEquals(expected.toString(), result);
    }

    @Test
    public void testPrint() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        new Debugger(out).println("Begin")
                .print("First line").ln()
                .print("Second line").ln()
                .println("End");

        String result = new String(out.toByteArray());

        StringBuilder expected = new StringBuilder();
        expected.append("Begin").append(LINE_SEPARATOR);
        expected.append("First line").append(LINE_SEPARATOR);
        expected.append("Second line").append(LINE_SEPARATOR);
        expected.append("End").append(LINE_SEPARATOR);

        Assert.assertEquals(expected.toString(), result);
    }
}
