package com.skatepark.shoveit.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EightQueenPuzzle implements ICheesBoard {

    public int[][] calc(int size) {
        int matrix[][]=new int[size][size];
        feedMatrix(matrix, -1);

        int queen = 0;
        for (Cell cell : getAvailableCells(matrix)) {
            boolean result = move(cell, queen, matrix);

            if (result) {
                break;
            }
        }
        return matrix;
    }

    private boolean move(Cell cell, int queen, int matrix[][]) {
        matrix[cell.row][cell.column] = queen;
        if (queen == matrix.length - 1) {
            return true;
        }

        List<Cell> availableCells = getAvailableCells(matrix);
        for (Cell c : availableCells) {
            boolean result = move(c, queen + 1, matrix);

            if (result) {
                return true;
            }
        }
        matrix[cell.row][cell.column] = -1;
        return false;
    }

    private List<Cell> getAvailableCells(int matrix[][]) {
        int copy[][] = new int[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            copy[i] = Arrays.copyOf(matrix[i], matrix[i].length);
        }
        for (int i = 0; i < copy.length; i++) {
            for (int j = 0; j < copy[i].length; j++) {
                if (copy[i][j] >= 0) {
                    fillRange(i, j, copy);
                }
            }
        }
        List<Cell> moves = new ArrayList<>();
        for (int i = 0; i < copy.length; i++) {
            for (int j = 0; j < copy[i].length; j++) {
                if (copy[i][j] == -1) {
                    moves.add(new Cell(i, j));
                }
            }
        }
        return moves;
    }

    private void fillRange(int row, int column, int copy[][]) {
        for (int i = 0; i < copy.length; i++) {
            copy[row][i] = -2;
            copy[i][column] = -2;
        }
        for (int i = row, j = column; i < copy.length && j < copy.length; i++, j++) {
            if (copy[i][j] == -1) {
                copy[i][j] = -2;
            }
        }
        for (int i = row, j = column; i >= 0 && j >= 0; i--, j--) {
            if (copy[i][j] == -1) {
                copy[i][j] = -2;
            }
        }
        for (int i = row, j = column; i >= 0 && j < copy.length; i--, j++) {
            if (copy[i][j] == -1) {
                copy[i][j] = -2;
            }
        }
        for (int i = row, j = column; i < copy.length && j >= 0; i++, j--) {
            if (copy[i][j] == -1) {
                copy[i][j] = -2;
            }
        }
    }
}
