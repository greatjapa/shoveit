package com.skatepark.shoveit;

import com.skatepark.shoveit.pojo.Person;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DebuggerTest {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    @Test
    public void testPrintList() {
        List<String> list = IntStream.range(0, 10)
                .mapToObj(i -> "value" + i)
                .collect(Collectors.toCollection(ArrayList::new));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Debugger(out).printList(list);
        String result = new String(out.toByteArray());

        StringBuilder expected = new StringBuilder();
        expected.append("list size = 10").append(LINE_SEPARATOR);
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
                .collect(Collectors.toCollection(ArrayList::new));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Debugger(out).printList("[{0}] = {1}", list);
        String result = new String(out.toByteArray());

        StringBuilder expected = new StringBuilder();
        expected.append("list size = 10").append(LINE_SEPARATOR);
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
    public void testPrintMap() {
        Map<Integer, String> map = IntStream.range(0, 10)
                .mapToObj(i -> i)
                .collect(Collectors.toMap(Integer::intValue, i -> "value" + i));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Debugger(out).printMap(map);
        String result = new String(out.toByteArray());

        StringBuilder expected = new StringBuilder();
        expected.append("map size = 10").append(LINE_SEPARATOR);
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
    public void testPrintMapWithCustomFormat() {
        Map<Integer, String> map = IntStream.range(0, 10)
                .mapToObj(i -> i)
                .collect(Collectors.toMap(Integer::intValue, i -> "value" + i));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Debugger(out).printMap("{0} has {1}", map);
        String result = new String(out.toByteArray());

        StringBuilder expected = new StringBuilder();
        expected.append("map size = 10").append(LINE_SEPARATOR);
        expected.append("0 has value0").append(LINE_SEPARATOR);
        expected.append("1 has value1").append(LINE_SEPARATOR);
        expected.append("2 has value2").append(LINE_SEPARATOR);
        expected.append("3 has value3").append(LINE_SEPARATOR);
        expected.append("4 has value4").append(LINE_SEPARATOR);
        expected.append("5 has value5").append(LINE_SEPARATOR);
        expected.append("6 has value6").append(LINE_SEPARATOR);
        expected.append("7 has value7").append(LINE_SEPARATOR);
        expected.append("8 has value8").append(LINE_SEPARATOR);
        expected.append("9 has value9").append(LINE_SEPARATOR);

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
        expected.append("List friends").append(LINE_SEPARATOR);

        Assert.assertEquals(expected.toString(), result);
    }

    @Test
    public void testPrintTypesWithCustomFormat() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Debugger(out).printTypes("{1} : {0}", Person.class);
        String result = new String(out.toByteArray());

        StringBuilder expected = new StringBuilder();
        expected.append("name : String").append(LINE_SEPARATOR);
        expected.append("lastName : String").append(LINE_SEPARATOR);
        expected.append("age : int").append(LINE_SEPARATOR);
        expected.append("friends : List").append(LINE_SEPARATOR);

        Assert.assertEquals(expected.toString(), result);
    }

    @Test
    public void testPrintValues() {
        Person person = new Person("Will", "Smith", 40);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Debugger(out).printValues(person);
        String result = new String(out.toByteArray());

        StringBuilder expected = new StringBuilder();
        expected.append("name = Will").append(LINE_SEPARATOR);
        expected.append("lastName = Smith").append(LINE_SEPARATOR);
        expected.append("age = 40").append(LINE_SEPARATOR);
        expected.append("friends = null").append(LINE_SEPARATOR);

        Assert.assertEquals(expected.toString(), result);
    }

    @Test
    public void testPrintValuesWithCustomFormat() {
        Person person = new Person("Will", "Smith", 40);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Debugger(out).printValues("{0} <- {1}", person);
        String result = new String(out.toByteArray());

        StringBuilder expected = new StringBuilder();
        expected.append("name <- Will").append(LINE_SEPARATOR);
        expected.append("lastName <- Smith").append(LINE_SEPARATOR);
        expected.append("age <- 40").append(LINE_SEPARATOR);
        expected.append("friends <- null").append(LINE_SEPARATOR);

        Assert.assertEquals(expected.toString(), result);
    }

    @Test
    public void testPrintNullValues() {
        Person person = new Person("Will", "Smith", 40);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Debugger(out).printNullValues(person);
        String result = new String(out.toByteArray());

        StringBuilder expected = new StringBuilder();
        expected.append("friends = null").append(LINE_SEPARATOR);

        Assert.assertEquals(expected.toString(), result);
    }

    @Test
    public void testPrintNullValuesWithCustomFormat() {
        Person person = new Person("Will", "Smith", 40);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Debugger(out).printNullValues("{0} is {1}", person);
        String result = new String(out.toByteArray());

        StringBuilder expected = new StringBuilder();
        expected.append("friends is null").append(LINE_SEPARATOR);

        Assert.assertEquals(expected.toString(), result);
    }

    @Test
    public void testPrintSerializableValues() {
        Person person = new Person("Will", "Smith", 40);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Debugger(out).printSerializableValues(person);
        String result = new String(out.toByteArray());

        StringBuilder expected = new StringBuilder();
        expected.append("name = Will").append(LINE_SEPARATOR);
        expected.append("age = 40").append(LINE_SEPARATOR);
        expected.append("friends = null").append(LINE_SEPARATOR);

        Assert.assertEquals(expected.toString(), result);
    }

    @Test
    public void testPrintSerializableValuesWithCustomFormat() {
        Person person = new Person("Will", "Smith", 40);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Debugger(out).printSerializableValues("{0} is {1}", person);
        String result = new String(out.toByteArray());

        StringBuilder expected = new StringBuilder();
        expected.append("name is Will").append(LINE_SEPARATOR);
        expected.append("age is 40").append(LINE_SEPARATOR);
        expected.append("friends is null").append(LINE_SEPARATOR);

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
