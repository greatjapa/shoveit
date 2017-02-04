package com.skatepark.shoveit.backtracking;

import java.util.ArrayList;
import java.util.List;

public class KnightTour {

    public int[][] calc(int rows, int columns, int row, int column) {
        if (row >= rows || column >= columns) {
            return null;
        }

        int matrix[][] = new int[rows][columns];
        feedMatrix(matrix, -1);

        int step = 0;
        moveTo(new Cell(row, column), matrix, step);
        return matrix;
    }

    private boolean moveTo(Cell cell, int[][] matrix, int step) {
        matrix[cell.row][cell.column] = step;
        if (isFull(matrix)) {
            return true;
        }

        List<Cell> moves = getAvailableMoves(cell, matrix);
        for (Cell candidate : moves) {
            boolean finished = moveTo(candidate, matrix, step + 1);

            if (finished) {
                return true;
            }
        }
        matrix[cell.row][cell.column] = -1;
        return false;
    }

    private boolean isFull(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == -1) {
                    return false;
                }
            }
        }
        return true;
    }


    private List<Cell> getAvailableMoves(Cell cell, int[][] matrix) {
        List<Cell> moves = new ArrayList<>();

        int row = cell.row - 1;
        int column = cell.column + 2;
        if (isValid(row, column, matrix)) {
            moves.add(new Cell(row, column));
        }

        row = cell.row - 2;
        column = cell.column + 1;
        if (isValid(row, column, matrix)) {
            moves.add(new Cell(row, column));
        }

        row = cell.row - 2;
        column = cell.column - 1;
        if (isValid(row, column, matrix)) {
            moves.add(new Cell(row, column));
        }

        row = cell.row - 1;
        column = cell.column - 2;
        if (isValid(row, column, matrix)) {
            moves.add(new Cell(row, column));
        }

        row = cell.row + 1;
        column = cell.column + 2;
        if (isValid(row, column, matrix)) {
            moves.add(new Cell(row, column));
        }

        row = cell.row + 2;
        column = cell.column + 1;
        if (isValid(row, column, matrix)) {
            moves.add(new Cell(row, column));
        }

        row = cell.row + 2;
        column = cell.column - 1;
        if (isValid(row, column, matrix)) {
            moves.add(new Cell(row, column));
        }

        row = cell.row + 1;
        column = cell.column - 2;
        if (isValid(row, column, matrix)) {
            moves.add(new Cell(row, column));
        }
        return moves;
    }

    private boolean isValid(int row, int column, int[][] matrix) {
        if (row < 0 || column < 0 || row >= matrix.length || column >= matrix[0].length) {
            return false;
        }
        return matrix[row][column] == -1;
    }

    private void feedMatrix(int[][] matrix, int value) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = value;
            }
        }
    }

    private class Cell {
        int row;
        int column;

        public Cell(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }
}
