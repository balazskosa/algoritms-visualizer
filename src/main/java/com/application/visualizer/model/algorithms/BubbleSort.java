package com.application.visualizer.model.algorithms;

import com.application.visualizer.model.Movement;
import com.application.visualizer.model.fixed.Change;
import com.vaadin.flow.internal.Pair;

import java.util.List;

public class BubbleSort extends Sort {

    public BubbleSort(List<Integer> list) {
        super(list);
    }

    @Override
    protected void sort() {

        String step;
        Movement movement;
        int n = this.numbers.size();
        for (int i = 0; i < n - 1; i++) {
            //animation
            step = "Starting pass " + i;
            movement = new Movement(step);
            this.movements.add(movement);

            //animation
            step = "For each element moving through the list";
            movement = new Movement(step);
            this.movements.add(movement);

            for (int j = 0; j < n - i -1; j++) {
                //animation
                step = "Compare elements";
                movement = new Movement(step, Change.SECOND_SELECTED, new Pair<>(j, null));
                movement.add(Change.SECOND_SELECTED, new Pair<>(j + 1, null));
                if(j != 0)  movement.add(Change.RESET, new Pair<>(j - 1, null));
                this.movements.add(movement);
                if(this.numbers.get(j) > this.numbers.get(j + 1)) {
                    //animation
                    step = "Swap";
                    movement = new Movement(step, Change.SWAP, new Pair<>(j, j + 1));
                    this.movements.add(movement);

                    int temp = this.numbers.get(j);
                    this.numbers.set(j, this.numbers.get(j + 1));
                    this.numbers.set(j + 1, temp);
                }
            }

            int lastNumberIndex = n - i - 1;
            //animation
            step = "Done this pass. The last element processed is now in its final position";
            movement = new Movement(step, Change.SORTED, new Pair<>(lastNumberIndex, null));
            if(lastNumberIndex != 0) movement.add(Change.RESET, new Pair<>(lastNumberIndex - 1, null));
            this.movements.add(movement);

        }

        step = "Last number is the smallest";
        movement = new Movement(step, Change.SORTED, new Pair<>(0, null));
        this.movements.add(movement);

    }

    @Override
    protected void initializingFirstMovements() {
        this.movements.add(new Movement("Starting Bubble Sort"));
        this.movements.add(new Movement("For each pass, we will move left to right swapping adjacent elements as needed." +
                " Each pass moves the next largest element into its final position (these will be shown in green)."));
    }


}
