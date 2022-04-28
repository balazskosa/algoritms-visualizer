package com.application.visualizer;

import com.application.algorithms.MaximumSelectionSort;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.internal.Pair;

import java.util.List;
import java.util.function.Consumer;


@CssImport("./styles/array.css")
public class Array extends Div {

    private int counter = 0;
    private final List<Movement> movements;

    public Array(List<Integer> list) {
        this.addClassName("array");

        for (Integer value : list) {
            this.add(new Number(value));
        }
        this.movements = new MaximumSelectionSort(list).getMovements();
    }

    public Number getNumberAtIndex(int index) {
        return (Number) this.getComponentAt(index);
    }

    public Consumer<Pair<Integer, Integer>> swap = (indexes) -> {
        Number firstNumber = getNumberAtIndex(indexes.getFirst());
        Number secondNumber = getNumberAtIndex(indexes.getSecond());
        int tmp = firstNumber.getValue();
        firstNumber.setValue(secondNumber.getValue());
        secondNumber.setValue(tmp);

        firstNumber.animation();
        secondNumber.animation();
    };
    public Consumer<Pair<Integer, Integer>> reset = (index) ->
            getNumberAtIndex(index.getFirst()).resetStyle();

    public Consumer<Pair<Integer, Integer>> selected = (index) ->
            getNumberAtIndex(index.getFirst()).selectedStyle();

    public Consumer<Pair<Integer, Integer>> secondSelected = (index) ->
            getNumberAtIndex(index.getFirst()).secondSelectedStyle();

    public Consumer<Pair<Integer, Integer>> sorted = (index) ->
            getNumberAtIndex(index.getFirst()).sortedStyle();


    public String display() {
        return this.counter + " / " + (this.movements.size() - 1);
    }

    public void play() {
        if (counter == this.movements.size()) return;

        Movement currentMovement = movements.get(counter);
        Notification.show(currentMovement.currentStep, 2000, Notification.Position.TOP_END);


        this.counter++;

        if (currentMovement.getChanges() == null) return;

        for (int i = 0; i < currentMovement.changes.size(); i++) {
            currentAnimation(currentMovement.changes.get(i), currentMovement.getIndex().get(i));
        }
    }

    public void currentAnimation(Change change, Pair<Integer, Integer> indexes) {
        switch (change) {
            case RESET -> reset.accept(indexes);
            case SECOND_SELECTED -> secondSelected.accept(indexes);
            case SELECTED -> selected.accept(indexes);
            case SWAP -> swap.accept(indexes);
            case SORTED -> sorted.accept(indexes);
        }
    }
}
