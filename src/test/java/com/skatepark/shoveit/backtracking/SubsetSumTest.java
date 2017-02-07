package com.skatepark.shoveit.backtracking;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SubsetSumTest {

    @Test
    public void testNoResult() {
        Set<Integer> values = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        Assert.assertTrue(new SubsetSum().calc(values).isEmpty());
    }

    @Test
    public void testBase() {
        List<Integer> list = Arrays.asList(0);

        Set<Set<Integer>> result = new SubsetSum().calc(new HashSet<>(list));
        Assert.assertEquals(1, result.size());

        Set<Integer> set = result.iterator().next();

        Assert.assertEquals(0, (int) set.iterator().next());
    }

    @Test
    public void testMoreThanOneResult() {
        List<Integer> list = Arrays.asList(-21, 1, 3, 6, -4, 0, -2, 4, 2, 5);
        Set<Set<Integer>> result = new SubsetSum().calc(new HashSet<>(list));
        Assert.assertEquals(17, result.size());

        list = Arrays.asList(-21, 1, 3, 6, -4, -2, 4, 2, 5);
        result = new SubsetSum().calc(new HashSet<>(list));
        Assert.assertEquals(8, result.size());
    }
}
