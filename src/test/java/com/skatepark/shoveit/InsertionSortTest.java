package com.skatepark.shoveit;

import org.junit.Assert;
import org.junit.Test;

public class InsertionSortTest {

    @Test
    public void oneElement() {
        Integer[] values = {5};
        Integer[] expected = {5};
        new InsertionSort().sort(values);
        Assert.assertArrayEquals(expected, values);
    }

    @Test
    public void twoElements() {
        Integer[] values = {5, 4};
        Integer[] expected = {4, 5};
        new InsertionSort().sort(values);
        Assert.assertArrayEquals(expected, values);
    }

    @Test
    public void threeElements() {
        Integer[] values = {5, 4, 3};
        Integer[] expected = {3, 4, 5};
        new InsertionSort().sort(values);
        Assert.assertArrayEquals(expected, values);
    }

    @Test
    public void realCase() {
        Integer[] values = {4, 5, 7, 3, 1, 234, 67, 34, 12, 565, 8, 9, 0, 4, 2, 8, 9, 4, 4, 4, 4, 4, 6, 7, 400};
        Integer[] expected = {0, 1, 2, 3, 4, 4, 4, 4, 4, 4, 4, 5, 6, 7, 7, 8, 8, 9, 9, 12, 34, 67, 234, 400, 565};
        new InsertionSort().sort(values);
        Assert.assertArrayEquals(expected, values);
    }
}
