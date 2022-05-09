package com.application.visualizer.data.algorithms;

import java.util.ArrayList;

public class HeapSort extends Sort {
    public HeapSort(ArrayList<Integer> list) {
        super(list);
    }

    @Override
    protected void initializingFirstMovements() {

    }

    @Override
    public void sort() {
        int n = this.numbers.size();

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(n, i);

        // One by one extract an element from heap
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            int temp = this.numbers.get(0);
            this.numbers.set(0, this.numbers.get(i));
            this.numbers.set(i, temp);

            // call max heapify on the reduced heap
            heapify(i, 0);
        }
    }

    private void heapify(int n, int i) {
        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (l < n && this.numbers.get(l) > this.numbers.get(largest))
            largest = l;

        // If right child is larger than largest so far
        if (r < n && this.numbers.get(r) > this.numbers.get(largest))
            largest = r;

        // If largest is not root
        if (largest != i) {
            int swap = this.numbers.get(i);
            this.numbers.set(i, this.numbers.get(largest));
            this.numbers.set(largest, swap);


            // Recursively heapify the affected sub-tree
            heapify(n, largest);
        }
    }
}
