package skatepark.shoveit.backtracking;

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
    public void testSevenResults() {
        List<Integer> list = Arrays.asList(-4, 0, -2, 4, 2, 5);
        Set<Set<Integer>> result = new SubsetSum().calc(new HashSet<>(list));
        Assert.assertEquals(7, result.size());
    }

    @Test
    public void testMoreThanOneResult() {
        List<Integer> list = Arrays.asList(-6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6);
        Set<Set<Integer>> result = new SubsetSum().calc(new HashSet<>(list));
        Assert.assertEquals(471, result.size());

        list = Arrays.asList(-6, -5, -4, -3, -2, -1, 1, 2, 3, 4, 5, 6);
        result = new SubsetSum().calc(new HashSet<>(list));
        Assert.assertEquals(235, result.size());
    }
}
