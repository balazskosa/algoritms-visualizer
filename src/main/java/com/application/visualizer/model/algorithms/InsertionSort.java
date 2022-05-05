package com.application.visualizer.model.algorithms;

import com.application.visualizer.model.Movement;
import com.application.visualizer.model.fixed.Change;
import com.vaadin.flow.internal.Pair;

import java.util.List;

public class InsertionSort extends Sort {
    public InsertionSort(List<Integer> list) {
        super(list);
    }

    @Override
    public void sort() {
        String step;
        Movement movement;
        for (int i = 0; i < this.numbers.size(); i++) {

            int current = this.numbers.get(i);
            //animation
            step = "Processing record in position " + (i + 1);
            movement = new Movement(step, Change.SELECTED, new Pair<>(i, null));
            this.movements.add(movement);

            int j = i - 1;
            step = "Move the blue record to the left until it reaches the correct position";
            this.movements.add(new Movement(step));
            while (j >= 0 && this.numbers.get(j) > current) {

                this.numbers.set(j + 1, this.numbers.get(j));
                //animation
                step = "Swap";
                movement = new Movement(step, Change.SORTED, new Pair<>(j + 1, null));
                movement.add(Change.SELECTED, new Pair<>(j, null));
                movement.add(Change.SWAP_TO_SECOND, new Pair<>(j, j + 1));
                this.movements.add(movement);

                j--;
            }
            this.numbers.set(j + 1, current);

            //animation
            step = "Final swap";
            movement = new Movement(step, Change.SET_VALUE, new Pair<>(j + 1, current));
            movement.add(Change.SORTED, new Pair<>(j + 1, null));
            this.movements.add(movement);

            //animation
            step = "Done this pass";
            movement = new Movement(step);
            this.movements.add(movement);
        }
    }

    @Override
    protected void initializingFirstMovements() {
        this.movements.add(new Movement("Starting insertion sort"));
        this.movements.add(new Movement("Highlighted green records to the left are always sorted." +
                " We begin with the record in position 0 in the sorted portion," +
                " and we will be moving the record in position 1 (in blue) to the left until it is sorted.... and we'll repeat this"));

    }
}
