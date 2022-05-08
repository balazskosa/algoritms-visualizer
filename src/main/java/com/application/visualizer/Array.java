package com.application.visualizer;

import com.application.visualizer.data.Change;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.internal.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@CssImport("./styles/array.css")
public class Array extends Div {

    private List<Integer> list;

    public Array(List<Integer> list) {
        this.list = new ArrayList<>(list);
        this.addClassName("array");

        setItems(list);
    }


    private Number getNumberAtIndex(int index) {
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

    private final Consumer<Pair<Integer, Integer>> swapToSecond = (indexes) -> {
        int firstValue = getNumberAtIndex(indexes.getFirst()).getValue();
        Number secondNumber = getNumberAtIndex(indexes.getSecond());
        secondNumber.setValue(firstValue);
        secondNumber.animation();
    };
    private final Consumer<Pair<Integer, Integer>> reset = (index) ->
            getNumberAtIndex(index.getFirst()).resetStyle();

    private final Consumer<Pair<Integer, Integer>> selected = (index) ->
            getNumberAtIndex(index.getFirst()).selectedStyle();

    private final Consumer<Pair<Integer, Integer>> secondSelected = (index) ->
            getNumberAtIndex(index.getFirst()).secondSelectedStyle();

    private final Consumer<Pair<Integer, Integer>> thirdSelected = (index) ->
            getNumberAtIndex(index.getFirst()).thirdSelectedStyle();

    private final Consumer<Pair<Integer, Integer>> sorted = (index) ->
            getNumberAtIndex(index.getFirst()).sortedStyle();

    private final Consumer<Pair<Integer, Integer>> setValue = (index) ->
            getNumberAtIndex(index.getFirst()).setValue(index.getSecond());

    public List<Integer> getList() {
        return list;
    }

    public void setItems(List<Integer> list) {
        this.list = list;
        this.removeAll();
        list.forEach((value) -> {
            Number number = new Number(value);
            this.add(number);
            //number.animation();
        });

    }

    public void animation(Change change, Pair<Integer, Integer> indexes) {
        switch (change) {
            case RESET -> reset.accept(indexes);
            case THIRD_SELECTED -> thirdSelected.accept(indexes);
            case SECOND_SELECTED -> secondSelected.accept(indexes);
            case SELECTED -> selected.accept(indexes);
            case SWAP -> swap.accept(indexes);
            case SORTED -> sorted.accept(indexes);
            case SWAP_TO_SECOND -> swapToSecond.accept(indexes);
            case SET_VALUE -> setValue.accept(indexes);
            default -> throw new IllegalArgumentException("Unknown change value");
        }
    }

    public void setUnsortedArray() {
        setItems(list);
    }

    public void setSortedArray() {
        List<Integer> sortedList = list.stream().sorted().toList();
        setItems(sortedList);
        for (int i = 0; i < list.size(); i++) {
            getNumberAtIndex(i).sortedStyle();
        }
    }
}
