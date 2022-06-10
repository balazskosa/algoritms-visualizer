package com.application.visualizer.data.algorithms;

import com.application.visualizer.data.Movement;
import com.application.visualizer.data.Change;
import com.vaadin.flow.internal.Pair;

import java.util.List;


public class MaximumSelectionSort extends Sort {
    public MaximumSelectionSort(List<Integer> list) {
        super(list);
    }

    @Override
    protected void sort() {
        String step;
        Movement movement;
        int j = numbers.size();

        while (j > 1) {
            step = "Starting pass";
            movement = new Movement(step);
            this.movements.add(movement);

            //--------------findMax
            int max = 0;

            //animation
            step = "Initialize max index";
            movement = new Movement(step, Change.SELECTED, new Pair<>(0, null));
            this.movements.add(movement);

            for (int i = 1; i < j; i++) {

                //animation
                step = "Compare to biggest seen so far";
                movement = new Movement(step, Change.SECOND_SELECTED, new Pair<>(i, null));
                if (max != i - 1) movement.add(Change.RESET, new Pair<>(i - 1, null));
                this.movements.add(movement);


                if (numbers.get(max) < numbers.get(i)) {

                    //animation
                    step = "Found something bigger, so switch value of max index";
                    movement = new Movement(step, Change.SELECTED, new Pair<>(i, null));
                    movement.add(Change.RESET, new Pair<>(max, null));
                    this.movements.add(movement);

                    max = i;
                }
            }


            //--------------end findMax

            //--------------swap

            //animation
            step = "Now swap the next biggest element into place.";
            movement = (max != j - 1) ? new Movement(step, Change.RESET, new Pair<>(j - 1, null)) : new Movement(step);
            this.movements.add(movement);

            if (max != j - 1) {
                int swap = this.numbers.get(max);
                this.numbers.set(max, this.numbers.get(j - 1));
                this.numbers.set(j - 1, swap);
            }

            //animation
            step = "Now swap the next biggest element into place.";
            movement = new Movement(step, Change.RESET, new Pair<>(max, null));
            movement.add(Change.SORTED, new Pair<>(j - 1, null));
            movement.add(Change.SWAP, new Pair<>(max, j - 1));
            this.movements.add(movement);

            //--------------end swap

            j = j - 1;
            step = "Done this pass";
            movement = new Movement(step);
            this.movements.add(movement);



        }

        step = "Last element is already sorted";
        movement = new Movement(step, Change.SORTED, new Pair<>(0, null));
        this.movements.add(movement);
    }

    @Override
    protected void initializingFirstMovements() {
        this.movements.add(new Movement("Starting maximum selection sort"));
        this.movements.add(new Movement("For each pass, we will move left to right" +
                " looking for the next largest value." +
                " Once that is found, it will be swapped into its final position"));
    }

}
