package com.application.visualizer.data.algorithms;

import com.application.visualizer.data.Change;
import com.application.visualizer.data.Movement;
import com.vaadin.flow.internal.Pair;

import java.util.List;

public class HeapSort extends Sort {
    public HeapSort(List<Integer> list) {
        super(list);
    }


    @Override
    public void sort() {

        String step;
        Movement movement;


        int n = this.numbers.size();
        buildHeap();


        introductionBuildMaxheap();
        // Build max heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(n, i);

        // One by one extract an element from heap
        for (int i = n - 1; i > 0; i--) {

            step = "Now we have a max heap";
            movement = new Movement(step);
            movements.add(movement);
            // Move current root to end

            step = "Move current root to end";
            movement = new Movement(step, Change.SWAP_NODES, new Pair<>(0, i));
            movement.add(Change.SWAP, new Pair<>(0, i));
            movement.add(Change.SORTED, new Pair<>(i, null));
            movements.add(movement);

            int temp = this.numbers.get(0);
            this.numbers.set(0, this.numbers.get(i));
            this.numbers.set(i, temp);

            step = "Delete last node from the heap";
            movement = new Movement(step, Change.DELETE_NODE, new Pair<>(i, null));
            movements.add(movement);
            // call max heapify on the reduced heap
            if(i == 1) {
                step = "Only one element left in the heap and one element is always sorted";
                movement = new Movement(step, Change.SORTED, new Pair<>(0, null));
                movement.add(Change.DELETE_NODE, new Pair<>(0, null));
                movements.add(movement);
                return;
            }
            heapify(i, 0);
        }
    }

    private void heapify(int n, int i) {

        step = "Check this node as root and heapify the affected sub-tree";
        movement = new Movement(step, Change.SELECTED_NODE, new Pair<>(i, null));
        movements.add(movement);

        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (l < n && this.numbers.get(l) > this.numbers.get(largest)) {
            largest = l;
            step = "Left node is greater than root";
            movement = new Movement(step, Change.SELECTED_NODE, new Pair<>(l, null));
            movements.add(movement);
        }


        // If right child is larger than largest so far
        if (r < n && this.numbers.get(r) > this.numbers.get(largest)) {
            largest = r;
            step = "Right node is greater than root";
            movement = new Movement(step, Change.SELECTED_NODE, new Pair<>(r, null));
            movement.add(Change.RESET_NODE, new Pair<>(l, null));
            movements.add(movement);
        }


        // If largest is not root
        if (largest != i) {
            step = "One of the child node is greater than parent, so swap it";
            movement = new Movement(step, Change.SWAP_NODES, new Pair<>(i, largest));

            movement.add(Change.RESET_NODE, new Pair<>(largest, null));
            movement.add(Change.RESET_NODE, new Pair<>(i, null));
            movement.add(Change.SWAP, new Pair<>(i, largest));
            movements.add(movement);

            int swap = this.numbers.get(i);
            this.numbers.set(i, this.numbers.get(largest));
            this.numbers.set(largest, swap);


            // Recursively heapify the affected sub-tree
            if(2 * largest + 1 >= n) return;
            heapify(n, largest);
        } else {
            step = "Child nodes aren't greater than root";
            movement = new Movement(step, Change.RESET_NODE, new Pair<>(i, null));
            movements.add(movement);
        }
    }


    @Override
    protected void initializingFirstMovements() {
        this.movements.add(new Movement("Starting Heap Sort"));
    }


    private void buildHeap() {
        for (int i = 0; i < numbers.size(); i++) {
            step = "Build heap";
            movement = new Movement(step, Change.ADD_NODE, new Pair<>(i, numbers.get(i)));
            movement.add(Change.SELECTED, new Pair<>(i, null));
            movement.add(Change.SELECTED_NODE, new Pair<>(i, null));

            if (i != 0) {
                movement.add(Change.RESET, new Pair<>(i - 1, null));
                movement.add(Change.RESET_NODE, new Pair<>(i - 1, null));
            }
            movements.add(movement);
        }

        step = "Now that we have build a heap we need to transform it into a max heap";
        movement = new Movement(step, Change.RESET_NODE, new Pair<>(numbers.size() - 1, null));
        movement.add(Change.RESET, new Pair<>(numbers.size() - 1, null));

        movements.add(movement);


    }

    private void introductionBuildMaxheap() {
        step = "In a max heap parent node always greater than or equal to child nodes";
        movements.add(new Movement(step));
    }
}
