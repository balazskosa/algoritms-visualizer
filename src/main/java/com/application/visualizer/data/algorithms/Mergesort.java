package com.application.visualizer.data.algorithms;

import com.application.visualizer.data.Change;
import com.application.visualizer.data.Movement;
import com.vaadin.flow.internal.Pair;

import java.util.ArrayList;
import java.util.List;

public class Mergesort extends Sort {
    public Mergesort(List<Integer> list) {
        super(list);
    }

    @Override
    public void sort() {
        mergeSort(0, numbers.size() - 1);
    }

    private void mergeSort(int l, int r) {
        if (l < r) {
            // Find the middle point
            int m = l + (r - l) / 2;

            step = "Split the array (as evenly as possible)";
            movement = new Movement(step, Change.SET_RIGHT_MARGIN, new Pair<>(m, null));
            movements.add(movement);
            // Sort first and second halves
            mergeSort(l, m);
            mergeSort(m + 1, r);

            step = "Merge the sorted halves and copy data to temporary arrays";
            movement = new Movement(step, Change.RESET_RIGHT_MARGIN, new Pair<>(m, null));
            // Merge the sorted halves
            merge(l, m, r);

        }
    }

    private void merge(int l, int m, int r) {
        // Find sizes of two subarrays to be merged

        int n1 = m - l + 1;
        int n2 = r - m;
        /* Create temp arrays */
        var left = new ArrayList<Integer>();
        var right = new ArrayList<Integer>();

        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; i++) {
            movement.add(Change.TMP_ARRAY_ADD, new Pair<>(numbers.get(l + i), null));
            movement.add(Change.SET_VALUE, new Pair<>(l + i, -1));
            movement.add(Change.SECOND_SELECTED, new Pair<>(l + i, -1));
            left.add(numbers.get(l + i));
        }
        movement.add(Change.TMP_ARRAY_SET_RIGHT_MARGIN, new Pair<>(n1 - 1, null));

        for (int j = 0; j < n2; j++) {
            movement.add(Change.TMP_ARRAY_ADD, new Pair<>(numbers.get(m + 1 + j), null));
            movement.add(Change.SET_VALUE, new Pair<>(m + 1 + j, -1));
            movement.add(Change.SECOND_SELECTED, new Pair<>(m + 1 + j, -1));
            right.add(numbers.get(m + 1 + j));
        }
        movements.add(movement);

        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2) {

            step = "Select the minimum of the two values";
            movement = new Movement(step, Change.TMP_ARRAY_SELECTED, new Pair<>(i, null));
            movement.add(Change.TMP_ARRAY_SELECTED, new Pair<>(n1 + j, null));
            movements.add(movement);


            if (left.get(i) <= right.get(j)) {
                step = "Add the selected value to the sorted array";
                movement = new Movement(step, Change.TMP_ARRAY_RESET, new Pair<>(i, null));
                movement.add(Change.TMP_ARRAY_SET_VALUE, new Pair<>(i, -1));
                movement.add(Change.SET_VALUE, new Pair<>(k, left.get(i)));
                movement.add(Change.SORTED, new Pair<>(k, left.get(i)));
                movements.add(movement);

                numbers.set(k, left.get(i));
                i++;
            } else {
                step = "Add the selected value to the sorted array";
                movement = new Movement(step, Change.TMP_ARRAY_RESET, new Pair<>(n1 + j, null));
                movement.add(Change.TMP_ARRAY_SET_VALUE, new Pair<>(n1 + j, -1));
                movement.add(Change.SET_VALUE, new Pair<>(k, right.get(j)));
                movement.add(Change.SORTED, new Pair<>(k, right.get(j)));
                movements.add(movement);

                numbers.set(k, right.get(j));
                j++;
            }

            k++;
        }

        /* Copy remaining elements of L[] if any */

        step = "When one list becomes empty, copy all values from the remaining array into the sorted array";
        movement = new Movement(step);
        movement.initializeChanges();

        if(i < n1) {
            for (int n = i; n < n1; n++) {
                movement.add(Change.TMP_ARRAY_SELECTED, new Pair<>(n, null));
            }
        } else {
            for (int n = j; n < n2; n++) {
                movement.add(Change.TMP_ARRAY_SELECTED, new Pair<>(n1 + n, null));
            }
        }
        movements.add(movement);

        step = "Copy remaining elements of subarray to list";
        movement = new Movement(step);
        movement.initializeChanges();
        while (i < n1) {
            movement.add(Change.SET_VALUE, new Pair<>(k, left.get(i)));
            movement.add(Change.SORTED, new Pair<>(k, left.get(i)));
            numbers.set(k, left.get(i));
            i++;
            k++;
        }


        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            movement.add(Change.SET_VALUE, new Pair<>(k, right.get(j)));
            movement.add(Change.SORTED, new Pair<>(k, right.get(j)));
            numbers.set(k, right.get(j));
            j++;
            k++;
        }
        movement.add(Change.TMP_ARRAY_CLEAR, new Pair<>(null, null));
        movements.add(movement);

//        step = "clear left and right subarray";
//        movement = new Movement(step, Change.TMP_ARRAY_CLEAR, new Pair<>(null, null));
//        movements.add(movement);
    }

    @Override
    protected void initializingFirstMovements() {
        movements.add(new Movement("Starting merge sort"));
    }
}
