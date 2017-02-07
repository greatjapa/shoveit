package com.skatepark.shoveit.backtracking;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SubsetSumTest {

    @Test
    public void testNoResult() {
        Set<Integer> values = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        Assert.assertNull(new SubsetSum().calc(values));
    }

    @Test
    public void testBase() {
        List<Integer> list = Arrays.asList(0);

        Set<Integer> set = new SubsetSum().calc(new HashSet<>(list));

        List<Integer> result = new ArrayList<>(set);

        Assert.assertArrayEquals(list.toArray(), result.toArray());
    }

    @Test
    public void test() {
        List<Integer> list = Arrays.asList(-21, 1, 2, 3, 4, 5, 6);

        Set<Integer> set = new SubsetSum().calc(new HashSet<>(list));

        List<Integer> result = new ArrayList<>(set);
        Collections.sort(result);

        Assert.assertArrayEquals(list.toArray(), result.toArray());
    }
}
