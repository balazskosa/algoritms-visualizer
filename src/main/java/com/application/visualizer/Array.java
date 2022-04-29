package com.application.visualizer;

import com.application.visualizer.algorithms.MaximumSelectionSort;
import com.application.visualizer.fixed.Change;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.internal.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@CssImport("./styles/array.css")
public class Array extends Div {

    private int counter = 0;
    private final List<Movement> movements;
    private final List<Integer> list;

    private final DisplayCurrentStep displayCurrentStep;

    public Array(List<Integer> list, DisplayCurrentStep displayCurrentStep) {
        this.list = new ArrayList<>(list);
        this.addClassName("array");

        for (Integer value : list) {
            this.add(new Number(value));
        }
        this.movements = new MaximumSelectionSort(list).getMovements();
        this.displayCurrentStep = displayCurrentStep;
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


    public String displayCounter() {
        return this.counter + " / " + this.movements.size();
    }

    public void play() {
        if (counter == this.movements.size()) return;

        Movement currentMovement = movements.get(counter);
        this.displayCurrentStep.set(currentMovement.getCurrentStep());
        //Notification.show(currentMovement.getCurrentStep(), 2000, Notification.Position.TOP_END);

        this.counter++;

        if (currentMovement.getChanges() == null) return;

        for (int i = 0; i < currentMovement.getChanges().size(); i++) {
            currentAnimation(currentMovement.getChanges().get(i), currentMovement.getIndex().get(i));
        }
    }

    private void currentAnimation(Change change, Pair<Integer, Integer> indexes) {
        switch (change) {
            case RESET -> reset.accept(indexes);
            case SECOND_SELECTED -> secondSelected.accept(indexes);
            case SELECTED -> selected.accept(indexes);
            case SWAP -> swap.accept(indexes);
            case SORTED -> sorted.accept(indexes);
            default -> throw new IllegalArgumentException("Unknown change value");
        }
    }

    public void setStart() {
        this.counter = 0;

        play();
        for (int i = 0; i < this.list.size(); i++) {
            Number currentNumber = this.getNumberAtIndex(i);
            currentNumber.setValue(this.list.get(i));
            currentNumber.resetStyle();
        }
    }

    public void setEnd() {
        this.counter = this.movements.size() - 1;

        play();

        List<Integer> sortedList = this.list.stream().sorted().toList();

        for (int i = 0; i < this.list.size(); i++) {
            Number currentNumber = this.getNumberAtIndex(i);
            currentNumber.setValue(sortedList.get(i));
            currentNumber.sortedStyle();
        }
    }

    public DisplayCurrentStep getDisplayCurrentStep() {
        return displayCurrentStep;
    }


//    public void playBackward() {
//
//        if (counter == 0) return;
//
//        this.counter--;
//
//        Movement currentMovement = movements.get(counter);
//        Notification.show(currentMovement.getCurrentStep(), 2000, Notification.Position.TOP_END);
//
//
//        if (currentMovement.getChanges() == null) return;
//
//
//        for (int i = 0; i < currentMovement.getChanges().size(); i++) {
//            currentAnimationBackward(currentMovement.getChanges().get(i), currentMovement.getIndex().get(i));
//        }
//    }

//    private void currentAnimationBackward(Change change, Pair<Integer, Integer> indexes) {
//
//        switch (change) {
//            case SWAP -> swap.accept(new Pair<>(indexes.getSecond(), indexes.getFirst()));
//            case RESET, SECOND_SELECTED, SELECTED, SORTED -> {
//                Change currentChange = findPreviousChange(indexes.getFirst());
//                currentAnimation(currentChange, indexes);
//            }
//            default -> throw new IllegalArgumentException("Unknown change value");
//        }
//    }

//    private Change findPreviousChange(int index) {
//
//        for (int i = this.counter; i >= 0; i--) {
//            Movement currentMovement = this.movements.get(i);
//            var find = findIndexWithChange(currentMovement, index);
//            if (find.getFirst()) return find.getSecond();
//        }
//        return Change.RESET;
//    }

//    private Pair<Boolean, Change> findIndexWithChange(Movement currentMovement, int index) {
//
//        for (int j = 0; j < currentMovement.getChanges().size(); j++) {
//
//            boolean findIndex = currentMovement.getIndex().get(j).getFirst() == index;
//            boolean findChangeStyle = currentMovement.getChanges().get(j) != Change.SWAP;
//
//            if (findIndex && findChangeStyle) return new Pair<>(true, currentMovement.getChanges().get(j));
//        }
//
//        return new Pair<>(false, null);
//    }
}
