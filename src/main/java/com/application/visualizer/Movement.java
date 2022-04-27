package com.application.visualizer;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.internal.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Movement {

    String currentStep;
    List<Consumer<Pair<Integer, Integer>>> changes;
    List<Pair<Integer, Integer>> index;

    public Movement(String currentStep,
                    List<Consumer<Pair<Integer, Integer>>> changes,
                    List<Pair<Integer, Integer>> index) {
        this.currentStep = currentStep;
        this.changes = changes;
        this.index = index;
    }

    public String getCurrentStep() {
        return currentStep;
    }

    public List<Consumer<Pair<Integer, Integer>>> getChanges() {
        return changes;
    }

    public void play(Div displayCurrentStep) {
        displayCurrentStep.setText(this.currentStep);
        if(changes == null) return;
        for (int i = 0; i < changes.size(); i++) {
            changes.get(i).accept(index.get(i));
        }
    }
}
