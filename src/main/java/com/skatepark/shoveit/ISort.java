package com.skatepark.shoveit;

import java.util.Comparator;

public interface ISort {

    default <T> void sort(T[] list) {
        sort(list, null);
    }

    <T> void sort(T[] list, Comparator<T> comparator);

    default <T> int compare(T obj1, T obj2, Comparator<T> comparator) {
        if (comparator != null) {
            return comparator.compare(obj1, obj2);
        }
        return ((Comparable<T>) obj1).compareTo(obj2);
    }
}
