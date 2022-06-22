package com.application.complexity.data;

public class HeapsortComplexity extends SortComplexity {
    public HeapsortComplexity(int size) {
        super(size, "Heapsort");
    }

    @Override
    protected void sort() {
        int n = numbers.size();

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(n, i);

        // One by one extract an element from heap
        for (int i = n - 1; i >= 0; i--) {
            // Move current root to end
            swap(0, i);
            // call max heapify on the reduced heap
            heapify(i, 0);
        }
    }


    void heapify(int n, int i) {
        int largest = i;  // Initialize largest as root
        int l = 2 * i + 1;  // left = 2*i + 1
        int r = 2 * i + 2;  // right = 2*i + 2

        // If left child is larger than root
        if (less(l,n) && greater(numbers.get(l), numbers.get(largest))) {
            andOperator();
            largest = l;
        }


        // If right child is larger than largest so far
        if (less(r,n) && greater(numbers.get(r), numbers.get(largest))) {
            andOperator();
            largest = r;
        }


        // If largest is not root
        if (notEqual(largest, i)) {
            swap(i, largest);

            // Recursively heapify the affected sub-tree
            heapify(n, largest);
        }
    }

}
