package com.skatepark.shoveit.sort;

import java.util.Comparator;

public class BubbleSort implements ISort {

    @Override
    public <T> void sort(T[] list, Comparator<T> comparator) {
        boolean hasChanged;
        do {
            hasChanged = false;
            for (int i = 0; i < list.length - 1; i++) {
                if (compare(list[i], list[i + 1], comparator) > 0) {
                    T elem = list[i + 1];
                    list[i + 1] = list[i];
                    list[i] = elem;
                    hasChanged = true;
                }
            }
        } while (hasChanged);
    }
}
