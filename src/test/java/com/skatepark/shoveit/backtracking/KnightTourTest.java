package com.skatepark.shoveit.backtracking;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class KnightTourTest {

    @Test
    public void testSmallestBoard() {
        int rows = 5;
        int columns = 5;
        int[][] matrix = new KnightTour().calc(rows, columns, 0, 0);
        Assert.assertTrue(isValid(matrix));
    }

    @Test
    public void testBoard() {
        int rows = 8;
        int columns = 8;
        int[][] matrix = new KnightTour().calc(rows, columns, 0, 0);
        Assert.assertTrue(isValid(matrix));
    }

    @Test
    public void testRectangularBoard() {
        int rows = 4;
        int columns = 6;
        int[][] matrix = new KnightTour().calc(rows, columns, 0, 0);
        Assert.assertTrue(isValid(matrix));
    }

    private boolean isValid(int[][] matrix) {
        List<Integer> values = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                values.add(matrix[i][j]);
            }
        }
        Collections.sort(values);

        return IntStream.range(0, matrix.length * matrix[0].length)
                .allMatch(i -> i == values.get(i));
    }
}
