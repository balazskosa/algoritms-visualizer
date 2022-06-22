package com.application.complexity.data;

import java.util.ArrayList;

public class MergesortComplexity extends SortComplexity {
    public MergesortComplexity(int size) {
        super(size, "Mergesort");
    }

    @Override
    protected void sort() {
        mergeSort(0, numbers.size() - 1);
    }


    private void merge(int l, int m, int r) {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        var left = new ArrayList<Integer>(n1);
        var right = new ArrayList<Integer>(n2);

        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i) left.add(i, numbers.get(l + i));
        for (int j = 0; j < n2; ++j) right.add(j, numbers.get(m + 1 + j));
        swapCounter += n1 + n2;

        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray array
        int k = l;

        while (less(i, n1) && less(j, n2)) {
            andOperator();

            if (!greater(left.get(i), right.get(j))) {
                set(k, left.get(i));
                i++;
            } else {
                set(k, right.get(j));
                j++;
            }
            k++;
        }


        /* Copy remaining elements of L[] if any */
        while (less(i, n1)) {
            set(k, left.get(i));
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (less(j, n2)) {
            set(k, right.get(j));
            j++;
            k++;
        }
    }

    // Main function that sorts arr[l..r] using
    // merge()
    private void mergeSort(int l, int r) {
        if (less(l, r)) {
            // Find the middle point
            int m = l + (r - l) / 2;

            // Sort first and second halves
            mergeSort(l, m);
            mergeSort(m + 1, r);

            // Merge the sorted halves
            merge(l, m, r);
        }
    }
}
