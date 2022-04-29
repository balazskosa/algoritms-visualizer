package com.application.visualizer.algorithms;

import com.application.visualizer.Movement;
import com.application.visualizer.fixed.Change;
import com.vaadin.flow.internal.Pair;

import java.util.List;

public class QuickSort extends Sort {

    public QuickSort(List<Integer> list) {
        super(list);
    }

    @Override
    protected void sort() {
        quickSort(0, this.numbers.size() - 1);
    }

    public void quickSort(int begin, int end) {

        if (begin < end) {
            int partitionIndex = partition(begin, end);
            String rangeLeft = " [" + begin + "," + (partitionIndex - 1) + "]";
            String rangeRight = " [" + (partitionIndex + 1) + "," + end + "]";


            if (!(partitionIndex <= begin)) {
                //animation
                this.getMovements().add(new Movement("Begin quick sorting the left part" + rangeLeft));
                quickSort(begin, partitionIndex - 1);
            }

            if (!(partitionIndex >= end)) {
                //animation
                this.getMovements().add(new Movement("Begin quick sorting the right part" + rangeRight));
                quickSort(partitionIndex + 1, end);
            }

        } else if (begin == end) {
            this.movements.add(new Movement("This is already sorted",
                    Change.SORTED, new Pair<>(begin, null)));
        }
    }

    private int partition(int begin, int end) {
        String step;
        Movement movement;


        int pivot = this.numbers.get(end);
        //animation
        step = "Select the pivot, in this case, take the last element as the pivot";
        movement = new Movement(step, Change.SELECTED, new Pair<>(end, null));
        this.movements.add(movement);

        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            //animation
            step = "Checks the next number and swaps it before the pivot if its value is smaller";
            movement = new Movement(step, Change.SECOND_SELECTED, new Pair<>(j, null));

            this.movements.add(movement);


            if (this.numbers.get(j) <= pivot) {
                //animation
                step = "The current number is less than the pivot";
                movement = new Movement(step);
                this.movements.add(movement);
                i++;
                //animation
                step = "Swap the selected values.";
                movement = new Movement(step, Change.SWAP, new Pair<>(i, j));
                movement.add(Change.THIRD_SELECTED, new Pair<>(i, null));
                if (i != j) movement.add(Change.RESET, new Pair<>(j, null));
                this.movements.add(movement);

                int swapTemp = this.numbers.get(i);
                this.numbers.set(i, this.numbers.get(j));
                this.numbers.set(j, swapTemp);

            } else {
                step = "The current number is not less than or equal to the pivot";
                movement = new Movement(step, Change.RESET, new Pair<>(j, null));
                this.movements.add(movement);
            }

        }

        //animation
        step = "Move the pivot to its final location";
        movement = new Movement(step, Change.SWAP, new Pair<>(i + 1, end));
        movement.add(Change.SORTED, new Pair<>(i + 1, null));
        movement.add(Change.RESET, new Pair<>(end, null));
        for (int counter = i; counter >= begin; counter--) {
            movement.add(Change.RESET, new Pair<>(counter, null));
        }
        this.movements.add(movement);

        int swapTemp = this.numbers.get(i + 1);
        this.numbers.set(i + 1, this.numbers.get(end));
        this.numbers.set(end, swapTemp);

        //animation
        step = "By the end of the partitioning, all elements less then the pivot" +
                " are on the left of it and all elements greater then the pivot are on the right of it.";
        movement = new Movement(step);
        this.movements.add(movement);

        return i + 1;
    }

    @Override
    protected void initializingFirstMovements() {
        this.movements.add(new Movement("Starting quicksort"));
    }

}
