package com.skatepark.shoveit;

import org.junit.Assert;
import org.junit.Test;

public class MergeSortTest {

    @Test
    public void oneElement() {
        int[] values = {5};
        int[] expected = {5};
        MergeSort.sort(values);
        Assert.assertArrayEquals(expected, values);
    }

    @Test
    public void twoElements() {
        int[] values = {5, 4};
        int[] expected = {4, 5};
        MergeSort.sort(values);
        Assert.assertArrayEquals(expected, values);
    }

    @Test
    public void threeElements() {
        int[] values = {5, 4, 3};
        int[] expected = {3, 4, 5};
        MergeSort.sort(values);
        Assert.assertArrayEquals(expected, values);
    }

    @Test
    public void realCase() {
        int[] values = {4, 5, 7, 3, 1, 234, 67, 34, 12, 565, 8, 9, 0, 4, 2, 8, 9, 4, 4, 4, 4, 4, 6, 7, 400};
        int[] expected = {0, 1, 2, 3, 4, 4, 4, 4, 4, 4, 4, 5, 6, 7, 7, 8, 8, 9, 9, 12, 34, 67, 234, 400, 565};
        MergeSort.sort(values);
        Assert.assertArrayEquals(expected, values);
    }
}
