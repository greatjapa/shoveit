package com.skatepark.shoveit;

import java.util.Comparator;

public class SelectionSort implements ISort {

    @Override
    public <T> void sort(T[] list, Comparator<T> comparator) {
        for (int i = 0; i < list.length - 1; i++) {
            T candidate = list[i];
            int cIndex = i;
            for (int j = i + 1; j < list.length; j++) {
                if (compare(candidate, list[j], comparator) > 0) {
                    candidate = list[j];
                    cIndex = j;
                }
            }
            list[cIndex] = list[i];
            list[i] = candidate;
        }
    }
}
