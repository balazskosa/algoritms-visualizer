package com.application.visualizer.algorithms;

import com.application.visualizer.Movement;

import java.util.ArrayList;
import java.util.List;

public abstract class Sort {
    protected final List<Integer> numbers;
    protected final List<Movement> movements = new ArrayList<>();

    public Sort(List<Integer> list) {
        this.numbers = new ArrayList<>(list);

        initializingFirstMovements();
        sort();
        this.movements.add(new Movement("Done sorting"));
    }
    protected abstract void initializingFirstMovements();
    protected abstract void sort();

    public List<Movement> getMovements() {
        return movements;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

}
