package com.skatepark.shoveit;

import java.util.Comparator;

public class InsertionSort implements ISort {

    @Override
    public <T> void sort(T[] list) {
        sort(list, null);
    }

    @Override
    public <T> void sort(T[] list, Comparator<T> comparator) {
        for (int i = 0; i < list.length; i++) {
            int j = i;
            while (j > 0 && lt(list[j], list[j - 1], comparator)) {
                T elem = list[j - 1];
                list[j - 1] = list[j];
                list[j] = elem;
                j--;
            }
        }
    }

    private <T> boolean lt(T elem1, T elem2, Comparator<T> comparator) {
        if (comparator != null) {
            return comparator.compare(elem1, elem2) < 0;
        }
        return ((Comparable) elem1).compareTo(elem2) < 0;
    }
}
