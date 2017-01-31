package com.skatepark.shoveit;

public class MergeSort {

    public static void sort(int[] values) {
        sort(values, 0, values.length - 1);
    }

    /**
     * @param start inclusive
     * @param end   inclusive.
     */
    private static void sort(int[] values, int start, int end) {
        if (end - start < 1) {
            return;
        }

        int middle = (end + start) / 2;
        sort(values, start, middle);
        sort(values, middle + 1, end);

        merge(values, start, end, middle);
    }

    private static void merge(int[] values, int start, int end, int middle) {
        int[] sorted = new int[end - start + 1];
        int sIndex = 0;

        int i = start, j = middle + 1;
        while (i <= middle && j <= end) {
            if (values[i] < values[j]) {
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
        for (int v : sorted) {
            values[i++] = v;
        }
    }
}
