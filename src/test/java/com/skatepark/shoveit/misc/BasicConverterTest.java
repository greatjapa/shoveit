package com.skatepark.shoveit.misc;

import org.junit.Assert;
import org.junit.Test;

public class BasicConverterTest {

    @Test
    public void testHex2Dec() {
        BasicConverter converter = new BasicConverter();
        for (int i = 0; i < 1000000; i++) {
            Assert.assertEquals(i, converter.hex2Dec(Integer.toHexString(i)));
        }
    }

    @Test
    public void testDec2Hex() {
        BasicConverter converter = new BasicConverter();
        for (int i = 0; i < 1000000; i++) {
            Assert.assertEquals(Integer.toHexString(i), converter.dec2Hex(i));
        }
    }
}
