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

public class PrinterTest {

    @Test
    public void testPrintList() {
        List<String> list = IntStream.range(0, 10)
                .mapToObj(i -> "value" + i)
                .collect(Collectors.toList());

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Printer(out)
                .printSize(list)
                .print(Printer.LN)
                .printList(list);
        String result = new String(out.toByteArray());

        StringBuilder expected = new StringBuilder();
        expected.append("size 10").append(Printer.LN);
        expected.append("0 -> value0").append(Printer.LN);
        expected.append("1 -> value1").append(Printer.LN);
        expected.append("2 -> value2").append(Printer.LN);
        expected.append("3 -> value3").append(Printer.LN);
        expected.append("4 -> value4").append(Printer.LN);
        expected.append("5 -> value5").append(Printer.LN);
        expected.append("6 -> value6").append(Printer.LN);
        expected.append("7 -> value7").append(Printer.LN);
        expected.append("8 -> value8").append(Printer.LN);
        expected.append("9 -> value9");

        Assert.assertEquals(expected.toString(), result);
    }

    @Test
    public void testPrintListWithCustomFormat() {
        List<String> list = IntStream.range(0, 10)
                .mapToObj(i -> "value" + i)
                .collect(Collectors.toList());

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Printer(out)
                .printSize(list, "{1}")
                .print(Printer.LN)
                .printList(list, "[{0}] = {1}");
        String result = new String(out.toByteArray());

        StringBuilder expected = new StringBuilder();
        expected.append("10").append(Printer.LN);
        expected.append("[0] = value0").append(Printer.LN);
        expected.append("[1] = value1").append(Printer.LN);
        expected.append("[2] = value2").append(Printer.LN);
        expected.append("[3] = value3").append(Printer.LN);
        expected.append("[4] = value4").append(Printer.LN);
        expected.append("[5] = value5").append(Printer.LN);
        expected.append("[6] = value6").append(Printer.LN);
        expected.append("[7] = value7").append(Printer.LN);
        expected.append("[8] = value8").append(Printer.LN);
        expected.append("[9] = value9");

        Assert.assertEquals(expected.toString(), result);
    }

    @Test
    public void testPrintListWithCustomFormatAndDelimiter() {
        List<String> list = IntStream.range(0, 10)
                .mapToObj(i -> "value" + i)
                .collect(Collectors.toList());

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Printer(out)
                .printSize(list, "{1},")
                .print(Printer.LN)
                .printList(list, "[{0}] = {1}", ",");
        String result = new String(out.toByteArray());

        StringBuilder expected = new StringBuilder();
        expected.append("10,").append(Printer.LN);
        expected.append("[0] = value0").append(",");
        expected.append("[1] = value1").append(",");
        expected.append("[2] = value2").append(",");
        expected.append("[3] = value3").append(",");
        expected.append("[4] = value4").append(",");
        expected.append("[5] = value5").append(",");
        expected.append("[6] = value6").append(",");
        expected.append("[7] = value7").append(",");
        expected.append("[8] = value8").append(",");
        expected.append("[9] = value9");

        Assert.assertEquals(expected.toString(), result);
    }

    @Test
    public void testPrintArray() {
        Object[] array = IntStream.range(0, 10)
                .mapToObj(i -> "value" + i)
                .toArray();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Printer(out)
                .printSize(array)
                .print(Printer.LN)
                .printArray(array);
        String result = new String(out.toByteArray());

        StringBuilder expected = new StringBuilder();
        expected.append("size 10").append(Printer.LN);
        expected.append("0 -> value0").append(Printer.LN);
        expected.append("1 -> value1").append(Printer.LN);
        expected.append("2 -> value2").append(Printer.LN);
        expected.append("3 -> value3").append(Printer.LN);
        expected.append("4 -> value4").append(Printer.LN);
        expected.append("5 -> value5").append(Printer.LN);
        expected.append("6 -> value6").append(Printer.LN);
        expected.append("7 -> value7").append(Printer.LN);
        expected.append("8 -> value8").append(Printer.LN);
        expected.append("9 -> value9");

        Assert.assertEquals(expected.toString(), result);
    }

    @Test
    public void testPrintArrayWithCustomFormat() {
        Object[] array = IntStream.range(0, 10)
                .mapToObj(i -> "value" + i)
                .toArray();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Printer(out)
                .printSize(array, "{1}")
                .print(Printer.LN)
                .printArray(array, "[{0}] = {1}");
        String result = new String(out.toByteArray());

        StringBuilder expected = new StringBuilder();
        expected.append("10").append(Printer.LN);
        expected.append("[0] = value0").append(Printer.LN);
        expected.append("[1] = value1").append(Printer.LN);
        expected.append("[2] = value2").append(Printer.LN);
        expected.append("[3] = value3").append(Printer.LN);
        expected.append("[4] = value4").append(Printer.LN);
        expected.append("[5] = value5").append(Printer.LN);
        expected.append("[6] = value6").append(Printer.LN);
        expected.append("[7] = value7").append(Printer.LN);
        expected.append("[8] = value8").append(Printer.LN);
        expected.append("[9] = value9");

        Assert.assertEquals(expected.toString(), result);
    }

    @Test
    public void testPrintArrayWithCustomFormatAndDelimiter() {
        Object[] array = IntStream.range(0, 10)
                .mapToObj(i -> "value" + i)
                .toArray();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Printer(out)
                .printSize(array, "{1},")
                .print(Printer.LN)
                .printArray(array, "[{0}] = {1}", ",");
        String result = new String(out.toByteArray());

        StringBuilder expected = new StringBuilder();
        expected.append("10,").append(Printer.LN);
        expected.append("[0] = value0").append(",");
        expected.append("[1] = value1").append(",");
        expected.append("[2] = value2").append(",");
        expected.append("[3] = value3").append(",");
        expected.append("[4] = value4").append(",");
        expected.append("[5] = value5").append(",");
        expected.append("[6] = value6").append(",");
        expected.append("[7] = value7").append(",");
        expected.append("[8] = value8").append(",");
        expected.append("[9] = value9");

        Assert.assertEquals(expected.toString(), result);
    }

    @Test
    public void testPrintMap() {
        Map<Integer, String> map = IntStream.range(0, 10)
                .mapToObj(i -> i)
                .collect(Collectors.toMap(Integer::intValue, i -> "value" + i));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Printer(out).printMap(map);
        String result = new String(out.toByteArray());

        StringBuilder expected = new StringBuilder();
        expected.append("0 -> value0").append(Printer.LN);
        expected.append("1 -> value1").append(Printer.LN);
        expected.append("2 -> value2").append(Printer.LN);
        expected.append("3 -> value3").append(Printer.LN);
        expected.append("4 -> value4").append(Printer.LN);
        expected.append("5 -> value5").append(Printer.LN);
        expected.append("6 -> value6").append(Printer.LN);
        expected.append("7 -> value7").append(Printer.LN);
        expected.append("8 -> value8").append(Printer.LN);
        expected.append("9 -> value9");

        Assert.assertEquals(expected.toString(), result);
    }

    @Test
    public void testPrintMapWithCustomFormat() {
        Map<Integer, String> map = IntStream.range(0, 10)
                .mapToObj(i -> i)
                .collect(Collectors.toMap(Integer::intValue, i -> "value" + i));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Printer(out).printMap(map, "{0} has {1}");
        String result = new String(out.toByteArray());

        StringBuilder expected = new StringBuilder();
        expected.append("0 has value0").append(Printer.LN);
        expected.append("1 has value1").append(Printer.LN);
        expected.append("2 has value2").append(Printer.LN);
        expected.append("3 has value3").append(Printer.LN);
        expected.append("4 has value4").append(Printer.LN);
        expected.append("5 has value5").append(Printer.LN);
        expected.append("6 has value6").append(Printer.LN);
        expected.append("7 has value7").append(Printer.LN);
        expected.append("8 has value8").append(Printer.LN);
        expected.append("9 has value9");

        Assert.assertEquals(expected.toString(), result);
    }

    @Test
    public void testPrintMapWithCustomFormatAndDelimiter() {
        Map<Integer, String> map = IntStream.range(0, 10)
                .mapToObj(i -> i)
                .collect(Collectors.toMap(Integer::intValue, i -> "value" + i));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Printer(out).printMap(map, "{0} has {1}", ",");
        String result = new String(out.toByteArray());

        StringBuilder expected = new StringBuilder();
        expected.append("0 has value0").append(",");
        expected.append("1 has value1").append(",");
        expected.append("2 has value2").append(",");
        expected.append("3 has value3").append(",");
        expected.append("4 has value4").append(",");
        expected.append("5 has value5").append(",");
        expected.append("6 has value6").append(",");
        expected.append("7 has value7").append(",");
        expected.append("8 has value8").append(",");
        expected.append("9 has value9");

        Assert.assertEquals(expected.toString(), result);
    }

    @Test
    public void testPrintSet() {
        Set<String> set = IntStream.range(0, 10)
                .mapToObj(i -> "value" + i)
                .collect(Collectors.toCollection(TreeSet::new));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Printer(out).printSet(set);
        String result = new String(out.toByteArray());

        StringBuilder expected = new StringBuilder();
        expected.append("value0").append(Printer.LN);
        expected.append("value1").append(Printer.LN);
        expected.append("value2").append(Printer.LN);
        expected.append("value3").append(Printer.LN);
        expected.append("value4").append(Printer.LN);
        expected.append("value5").append(Printer.LN);
        expected.append("value6").append(Printer.LN);
        expected.append("value7").append(Printer.LN);
        expected.append("value8").append(Printer.LN);
        expected.append("value9");

        Assert.assertEquals(expected.toString(), result);
    }

    @Test
    public void testPrintSetWithCustomFormat() {
        Set<String> set = IntStream.range(0, 10)
                .mapToObj(i -> "value" + i)
                .collect(Collectors.toCollection(TreeSet::new));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Printer(out).printSet(set, "~{0}");
        String result = new String(out.toByteArray());

        StringBuilder expected = new StringBuilder();
        expected.append("~value0").append(Printer.LN);
        expected.append("~value1").append(Printer.LN);
        expected.append("~value2").append(Printer.LN);
        expected.append("~value3").append(Printer.LN);
        expected.append("~value4").append(Printer.LN);
        expected.append("~value5").append(Printer.LN);
        expected.append("~value6").append(Printer.LN);
        expected.append("~value7").append(Printer.LN);
        expected.append("~value8").append(Printer.LN);
        expected.append("~value9");

        Assert.assertEquals(expected.toString(), result);
    }

    @Test
    public void testPrintSetWithCustomFormatAndDelimiter() {
        Set<String> set = IntStream.range(0, 10)
                .mapToObj(i -> "value" + i)
                .collect(Collectors.toCollection(TreeSet::new));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Printer(out).printSet(set, "~{0}", ",");
        String result = new String(out.toByteArray());

        StringBuilder expected = new StringBuilder();
        expected.append("~value0").append(",");
        expected.append("~value1").append(",");
        expected.append("~value2").append(",");
        expected.append("~value3").append(",");
        expected.append("~value4").append(",");
        expected.append("~value5").append(",");
        expected.append("~value6").append(",");
        expected.append("~value7").append(",");
        expected.append("~value8").append(",");
        expected.append("~value9");

        Assert.assertEquals(expected.toString(), result);
    }

    @Test
    public void testPrintTypes() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Printer(out).printTypes(Person.class);
        String result = new String(out.toByteArray());

        StringBuilder expected = new StringBuilder();
        expected.append("String name").append(Printer.LN);
        expected.append("String lastName").append(Printer.LN);
        expected.append("int age").append(Printer.LN);
        expected.append("List friends");

        Assert.assertEquals(expected.toString(), result);
    }

    @Test
    public void testPrintTypesWithCustomFormat() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Printer(out).printTypes(Person.class, "{1} : {0}");
        String result = new String(out.toByteArray());

        StringBuilder expected = new StringBuilder();
        expected.append("name : String").append(Printer.LN);
        expected.append("lastName : String").append(Printer.LN);
        expected.append("age : int").append(Printer.LN);
        expected.append("friends : List");

        Assert.assertEquals(expected.toString(), result);
    }

    @Test
    public void testPrintTypesWithCustomFormatAndDelimiter() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Printer(out).printTypes(Person.class, "{1} : {0}", ",");
        String result = new String(out.toByteArray());

        StringBuilder expected = new StringBuilder();
        expected.append("name : String").append(",");
        expected.append("lastName : String").append(",");
        expected.append("age : int").append(",");
        expected.append("friends : List");

        Assert.assertEquals(expected.toString(), result);
    }

    @Test
    public void testPrintValues() {
        Person person = new Person("Will", "Smith", 40, null);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Printer(out).printValues(person);
        String result = new String(out.toByteArray());

        StringBuilder expected = new StringBuilder();
        expected.append("name = Will").append(Printer.LN);
        expected.append("lastName = Smith").append(Printer.LN);
        expected.append("age = 40").append(Printer.LN);
        expected.append("friends = null");

        Assert.assertEquals(expected.toString(), result);
    }

    @Test
    public void testPrintValuesWithCustomFormat() {
        Person person = new Person("Will", "Smith", 40, new ArrayList<>());

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Printer(out).printValues(person, "{0} <- {1}");
        String result = new String(out.toByteArray());

        StringBuilder expected = new StringBuilder();
        expected.append("name <- Will").append(Printer.LN);
        expected.append("lastName <- Smith").append(Printer.LN);
        expected.append("age <- 40").append(Printer.LN);
        expected.append("friends <- []");

        Assert.assertEquals(expected.toString(), result);
    }

    @Test
    public void testPrintValuesWithCustomFormatAndDelimiter() {
        Person person = new Person("Will", "Smith", 40, new ArrayList<>());

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Printer(out).printValues(person, "{0} <- {1}", ",");
        String result = new String(out.toByteArray());

        StringBuilder expected = new StringBuilder();
        expected.append("name <- Will").append(",");
        expected.append("lastName <- Smith").append(",");
        expected.append("age <- 40").append(",");
        expected.append("friends <- []");

        Assert.assertEquals(expected.toString(), result);
    }

    @Test
    public void testPrintNullValues() {
        Person person = new Person("Will", "Smith", 40, null);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Printer(out).printNullValues(person);
        String result = new String(out.toByteArray());

        Assert.assertEquals("friends = null", result);
    }

    @Test
    public void testPrintNullValuesWithCustomFormat() {
        Person person = new Person("Will", "Smith", 40, null);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Printer(out).printNullValues(person, "{0} is {1}");
        String result = new String(out.toByteArray());

        Assert.assertEquals("friends is null", result);
    }

    @Test
    public void testPrintNullValuesWithCustomFormatAndDelimiter() {
        Person person = new Person("Will", "Smith", 40, null);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Printer(out).printNullValues(person, "{0} is {1}", ",");
        String result = new String(out.toByteArray());

        Assert.assertEquals("friends is null", result);
    }

    @Test
    public void testPrintSerializableValues() {
        Person person = new Person("Will", "Smith", 40, null);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Printer(out).printValues(person, "{0} = {1}", Printer.LN, Printer.TRANSIENT.negate());
        String result = new String(out.toByteArray());

        StringBuilder expected = new StringBuilder();
        expected.append("name = Will").append(Printer.LN);
        expected.append("age = 40").append(Printer.LN);
        expected.append("friends = null");

        Assert.assertEquals(expected.toString(), result);
    }

    @Test
    public void testPrintSerializableValuesWithCustomFormat() {
        Person person = new Person("Will", "Smith", 40, new ArrayList<>());

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Printer(out).printValues(person, "{0} is {1}", Printer.LN, Printer.TRANSIENT.negate());
        String result = new String(out.toByteArray());

        StringBuilder expected = new StringBuilder();
        expected.append("name is Will").append(Printer.LN);
        expected.append("age is 40").append(Printer.LN);
        expected.append("friends is []");

        Assert.assertEquals(expected.toString(), result);
    }

    @Test
    public void testPrintSerializableValuesWithCustomFormatAndDelimiter() {
        Person person = new Person("Will", "Smith", 40, new ArrayList<>());

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Printer(out).printValues(person, "{0} is {1}", ",", Printer.TRANSIENT.negate());
        String result = new String(out.toByteArray());

        StringBuilder expected = new StringBuilder();
        expected.append("name is Will").append(",");
        expected.append("age is 40").append(",");
        expected.append("friends is []");

        Assert.assertEquals(expected.toString(), result);
    }

    @Test
    public void testPrint() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        new Printer(out).println("Begin")
                .print("First line").ln()
                .print("Second line").ln()
                .println("End");

        String result = new String(out.toByteArray());

        StringBuilder expected = new StringBuilder();
        expected.append("Begin").append(Printer.LN);
        expected.append("First line").append(Printer.LN);
        expected.append("Second line").append(Printer.LN);
        expected.append("End").append(Printer.LN);

        Assert.assertEquals(expected.toString(), result);
    }
}
