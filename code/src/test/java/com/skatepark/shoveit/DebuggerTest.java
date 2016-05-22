package com.skatepark.shoveit;

import org.junit.Test;

import java.util.Properties;

public class DebuggerTest {

    @Test
    public void foo() {
        new Debugger()
                .types(Properties.class)
                .ln()
                .valuesNull(new Properties())
                .ln()
                .write("end");
    }
}
