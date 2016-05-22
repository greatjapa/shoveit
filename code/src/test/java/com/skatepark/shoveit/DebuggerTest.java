package com.skatepark.shoveit;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class DebuggerTest {

    @Test
    public void foo() {
        List<String> list = Arrays.asList("Foo", "Boo");
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");

        new Debugger()
                .printTypes(Properties.class)
                .ln()
                .printValues(new Properties())
                .ln()
                .printList(list)
                .ln()
                .printMap(map)
                .ln()
                .print("end");
    }
}
