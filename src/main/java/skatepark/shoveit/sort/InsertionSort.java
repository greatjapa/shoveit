package skatepark.shoveit.sort;

import java.util.Comparator;

public class InsertionSort implements ISort {

    @Override
    public <T> void sort(T[] list, Comparator<T> comparator) {
        for (int i = 0; i < list.length; i++) {
            int j = i;
            while (j > 0 && compare(list[j], list[j - 1], comparator) < 0) {
                T elem = list[j - 1];
                list[j - 1] = list[j];
                list[j] = elem;
                j--;
            }
        }
    }
}
