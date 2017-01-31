package com.skatepark.shoveit;

import java.util.Comparator;

public interface ISort {

    <T> void sort(T[] list);

    <T> void sort(T[] list, Comparator<T> comparator);
}
