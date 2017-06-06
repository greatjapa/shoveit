package skatepark.shoveit.backtracking;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class EightQueenPuzzleTest {

    @Test
    public void testNoValidResults() {
        int matrix[][] = new EightQueenPuzzle().calc(2);
        Assert.assertFalse(isValid(matrix));

        matrix = new EightQueenPuzzle().calc(3);
        Assert.assertFalse(isValid(matrix));
    }

    @Test
    public void testSmallestBoard() {
        int matrix[][] = new EightQueenPuzzle().calc(1);
        Assert.assertTrue(isValid(matrix));

        matrix = new EightQueenPuzzle().calc(4);
        Assert.assertTrue(isValid(matrix));
    }

    @Test
    public void testBoard() {
        int matrix[][] = new EightQueenPuzzle().calc(8);
        Assert.assertTrue(isValid(matrix));
    }

    private boolean isValid(int[][] matrix) {
        List<Integer> values = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] >= 0) {
                    values.add(matrix[i][j]);
                }
            }
        }
        Collections.sort(values);

        return IntStream.range(0, matrix.length)
                .allMatch(i -> i < values.size() && i == values.get(i));
    }
}
