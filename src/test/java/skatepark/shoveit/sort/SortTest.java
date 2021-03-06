package skatepark.shoveit.sort;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class SortTest {

    private Integer[][] inputs = {
            {5},
            {5, 4},
            {5, 4, 3},
            {-4, -3, -2, -1, 0, 1, 2, 3, 4},
            {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12},
            {4, 5, 7, 3, 1, 234, 67, 34, 12, 565, 8, 9},
            {4, 5, 7, 3, 1, 234, 67, 34, 12, 565, 8, 9, 0, 4, 2, 8, 9, 4, 4, 4, 4, 4, 6, 7, 400}
    };

    private ISort[] algorithms = {new SelectionSort(), new MergeSort(), new BubbleSort(), new InsertionSort()};

    @Test
    public void testAllInputs() {
        for (ISort algorithm : algorithms) {
            for (Integer[] input : inputs) {

                Integer[] values = Arrays.copyOf(input, input.length);
                Integer[] expected = Arrays.copyOf(input, input.length);
                Arrays.sort(expected);

                algorithm.sort(values);

                Assert.assertArrayEquals(algorithm.getClass().getSimpleName(), expected, values);
            }
        }
    }
}
