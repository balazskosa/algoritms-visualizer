package com.application.visualizer;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.internal.Pair;

import java.util.List;
import java.util.function.Consumer;


@CssImport("./styles/array.css")
public class Array extends Div {

    public Array(List<Integer> list) {
        this.addClassName("array");

        for (Integer value : list) {
            this.add(new Number(value));
        }
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

}
