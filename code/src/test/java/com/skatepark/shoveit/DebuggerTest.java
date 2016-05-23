package com.skatepark.shoveit;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

public class DebuggerTest {

    @Test
    public void foo() {
        List<String> list = Arrays.asList("Foo", "Boo");
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");

        new Debugger()
                .println("Properties types")
                .printTypes(Properties.class)
                .ln()
                .println("Properties values")
                .printValues(new Properties())
                .ln()
                .println("Only properties null values")
                .printNullValues(new Properties())
                .ln()
                .println("print list")
                .printList(list)
                .ln()
                .println("print map")
                .printMap(map)
                .ln()
                .print("end");
    }
}
