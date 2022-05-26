package com.application.visualizer.data.algorithms;

import com.application.visualizer.data.Change;
import com.application.visualizer.data.Movement;
import com.vaadin.flow.internal.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class TournamentSort extends Sort {
    public List<Integer> tournamentTree;
    private int n;
    private int counter;

    public TournamentSort(List<Integer> list) {
        super(list);
    }

    @Override
    protected void initializingFirstMovements() {
        step = "Starting Tournament sort";
        movements.add(new Movement(step));
    }

    private void initializingVariables() {
        n = numbers.size();
        counter = n - 1;
        tournamentTree = new ArrayList<>(2*n-1);
        IntStream.range(0, n - 1).map(i -> 0).forEach(tournamentTree::add);
        tournamentTree.addAll(numbers);
        numbers.clear();
    }

    @Override
    public void sort() {
        //
        initializingVariables();
        initialFilling();

        tournamentTreeFillOut();
        write();

        int r = n - 1;
        while (r >= 1) {
            tournamentTreeMax();
            write();
            r = r - 1;
        }

    }

    private void initialFilling() {
        for (int i = 0; i < n; i++) {
            step = "Initial filling";
            movement = new Movement(step, Change.SELECTED, new Pair<>(i, null));
            movement.add(Change.SELECTED_NODE, new Pair<>(n-1 + i, null));
            movements.add(movement);

            step = "Initial filling";
            movement = new Movement(step, Change.RESET, new Pair<>(i, null));
            movement.add(Change.SET_VALUE, new Pair<>(i, -1));
            movement.add(Change.SET_VALUE_NODE, new Pair<>(n-1 + i, tournamentTree.get(n-1+i)));
            movement.add(Change.RESET_NODE, new Pair<>(n-1 + i, null));
            movements.add(movement);
        }
    }

    private void tournamentTreeFillOut() {
        for (int j = n - 1; j >= 1; j--) {
            step = "Find Winner";
            movement = new Movement(step, Change.SELECTED_NODE, new Pair<>(2*j, null));
            if(j != n-1) {
                movement.add(Change.RESET_NODE, new Pair<>(j, null));
            }
            movement.add(Change.SELECTED_NODE, new Pair<>(2*j - 1, null));
            movements.add(movement);

            int max = Math.max(tournamentTree.get(2 * j - 1), tournamentTree.get(2 * j));
            tournamentTree.set(j - 1, max);

            step = "Winner";
            movement = new Movement(step, Change.RESET_NODE, new Pair<>(2*j -1, null));
            movement.add(Change.RESET_NODE, new Pair<>(2*j, null));
            movement.add(Change.SET_VALUE_NODE, new Pair<>(j -1 , max));
            movement.add(Change.SELECTED_NODE, new Pair<>(j-1, null));
            movements.add(movement);

        }

        step = "Tournament ended";
        movement = new Movement(step, Change.RESET_NODE, new Pair<>(0, null));
        movements.add(movement);
    }

    private void write() {
        step = "Write out the winner";
        movement = new Movement(step, Change.SET_VALUE, new Pair<>(counter, tournamentTree.get(0)));
        movement.add(Change.SORTED, new Pair<>(counter, null));
        counter--;
        movements.add(movement);

        numbers.add(0, tournamentTree.get(0));
    }

    private void tournamentTreeMax() {

        int j = 1;
        int prev = 0;
        //
        step = "Find the next maximum value";
        movement = new Movement(step, Change.SELECTED_NODE, new Pair<>(0, null));
        movements.add(movement);

        while (j <= n - 1) {
            if (tournamentTree.get(j - 1).intValue() == tournamentTree.get(2 * j - 1).intValue()) {
                j = 2 * j;
            } else {
                j = 2 * j + 1;
            }
            step = "Return to the winner";
            movement = new Movement(step, Change.SELECTED_NODE, new Pair<>(j - 1, null));
            movement.add(Change.RESET_NODE, new Pair<>(prev, null));
            movements.add(movement);
            prev = j - 1;
        }

        step = "Change the value to minus infinity";
        movement = new Movement(step, Change.RESET_NODE, new Pair<>(prev, null));
        movement.add(Change.SET_VALUE_NODE, new Pair<>(j-1, Integer.MIN_VALUE));
        movements.add(movement);

        tournamentTree.set(j - 1, Integer.MIN_VALUE);

        j = j / 2;

        step = "Replay to find the next winner";
        movements.add(new Movement(step));

        while (j >= 1) {
            step = "Find winner";
            movement = new Movement(step, Change.SELECTED_NODE, new Pair<>(2*j-1, null));
            movement.add(Change.SELECTED_NODE, new Pair<>(2*j, null));
            movements.add(movement);


            int max = Math.max(tournamentTree.get(2 * j - 1), tournamentTree.get(2 * j));
            tournamentTree.set(j - 1, max);
            step = "Next winner is...";
            movement = new Movement(step, Change.RESET_NODE, new Pair<>(2*j-1, null));
            movement.add(Change.RESET_NODE, new Pair<>(2*j, null));
            movement.add(Change.SELECTED_NODE, new Pair<>(j-1, null));
            movement.add(Change.SET_VALUE_NODE, new Pair<>(j-1, max));
            movements.add(movement);

            j = j / 2;
        }

        step = "Now, the next winner is staying on top of the tournament tree";
        movement = new Movement(step, Change.RESET_NODE, new Pair<>(0, null));
        movements.add(movement);

    }
}
