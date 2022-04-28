package com.application.visualizer;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.internal.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Movement {
    String currentStep;
    List<Change> changes;
    List<Pair<Integer, Integer>> index;

    public Movement(String currentStep) {
        this.currentStep = currentStep;
    }

    public Movement(String currentStep, Change change, Pair<Integer, Integer> indexes) {
        this.currentStep = currentStep;
        changes = new ArrayList<>();
        changes.add(change);
        index = new ArrayList<>();
        index.add(indexes);
    }

    public String getCurrentStep() {
        return currentStep;
    }


    public void add(Change change, Pair<Integer, Integer> indexes) {
        this.changes.add(change);
        this.index.add(indexes);
    }

    public List<Change> getChanges() {
        return changes;
    }

    public List<Pair<Integer, Integer>> getIndex() {
        return index;
    }
}

