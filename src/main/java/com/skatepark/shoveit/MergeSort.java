package com.skatepark.shoveit;

import java.util.Comparator;

public class MergeSort implements ISort {

    @Override
    public <T> void sort(T[] list) {
        sort(list, null);
    }

    @Override
    public <T> void sort(T[] list, Comparator<T> comparator) {
        sort(list, 0, list.length - 1, comparator);
    }

    /**
     * @param start inclusive
     * @param end   inclusive.
     */
    private <T> void sort(T[] values, int start, int end, Comparator<T> comparator) {
        if (end - start < 1) {
            return;
        }

        int middle = (end + start) / 2;
        sort(values, start, middle, comparator);
        sort(values, middle + 1, end, comparator);

        merge(values, start, end, middle, comparator);
    }

    private <T> void merge(T[] values, int start, int end, int middle, Comparator<T> comparator) {
        Object[] sorted = new Object[end - start + 1];
        int sIndex = 0;

        int i = start, j = middle + 1;
        while (i <= middle && j <= end) {
            if (compare(values[i], values[j], comparator) < 0) {
                sorted[sIndex++] = values[i++];
            } else {
                sorted[sIndex++] = values[j++];
            }
        }

        while (j <= end) {
            sorted[sIndex++] = values[j++];
        }
        while (i <= middle) {
            sorted[sIndex++] = values[i++];
        }

        i = start;
        for (Object v : sorted) {
            values[i++] = (T) v;
        }
    }

    private <T> int compare(T obj1, T obj2, Comparator<T> comparator) {
        if (comparator != null) {
            return comparator.compare(obj1, obj2);
        }
        return ((Comparable<T>) obj1).compareTo(obj2);
    }

}
