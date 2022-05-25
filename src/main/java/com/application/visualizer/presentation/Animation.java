package com.application.visualizer.presentation;

import com.application.visualizer.data.Change;
import com.application.visualizer.view.Array;
import com.application.visualizer.view.BinaryTree;
import com.application.visualizer.view.Number;
import com.vaadin.flow.internal.Pair;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Animation {


    Array array;
    BinaryTree tree;

    public Animation(Array array, BinaryTree tree) {
        this.array = array;
        this.tree = tree;
    }

    public Animation(Array array) {
        this.array = array;
    }

    private final BiConsumer<Pair<Integer, Integer>, List<Number>> swap = (indexes, list) -> {
        Number firstNumber = list.get(indexes.getFirst());
        Number secondNumber = list.get(indexes.getSecond());
        int tmp = firstNumber.getValue();
        firstNumber.setValue(secondNumber.getValue());
        secondNumber.setValue(tmp);

        firstNumber.animation();
        secondNumber.animation();
    };
    private final BiConsumer<Pair<Integer, Integer>, List<Number>> swapToSecond = (indexes, list) -> {
        int firstValue = list.get(indexes.getFirst()).getValue();
        Number secondNumber = list.get(indexes.getSecond());
        secondNumber.setValue(firstValue);
        secondNumber.animation();
    };
    private final BiConsumer<Pair<Integer, Integer>, List<Number>> reset = (index, list) ->
            list.get(index.getFirst()).resetStyle();

    private final BiConsumer<Pair<Integer, Integer>, List<Number>> selected = (index, list) ->
            list.get(index.getFirst()).selectedStyle();

    private final BiConsumer<Pair<Integer, Integer>, List<Number>> secondSelected = (index, list) ->
            list.get(index.getFirst()).secondSelectedStyle();

    private final BiConsumer<Pair<Integer, Integer>, List<Number>> thirdSelected = (index, list) ->
            list.get(index.getFirst()).thirdSelectedStyle();

    private final BiConsumer<Pair<Integer, Integer>, List<Number>> sorted = (index, list) ->
            list.get(index.getFirst()).sortedStyle();

    private final BiConsumer<Pair<Integer, Integer>, List<Number>> setValue = (index, list) ->
            list.get(index.getFirst()).setValue(index.getSecond());

    private final Consumer<Pair<Integer, Integer>> addNode = (index) -> {
        tree.addNode();
        tree.getNumberAtIndex(index.getFirst()).setValue(index.getSecond());
    };

    private final Consumer<Pair<Integer, Integer>> deleteNode = (index) -> tree.deleteNode();


    public void animation(Change change, Pair<Integer, Integer> indexes) {
        switch (change) {
            case RESET -> reset.accept(indexes, array.getNumbers());
            case THIRD_SELECTED -> thirdSelected.accept(indexes, array.getNumbers());
            case SECOND_SELECTED -> secondSelected.accept(indexes, array.getNumbers());
            case SELECTED -> selected.accept(indexes, array.getNumbers());
            case SWAP -> swap.accept(indexes, array.getNumbers());
            case SORTED -> sorted.accept(indexes, array.getNumbers());
            case SWAP_TO_SECOND -> swapToSecond.accept(indexes, array.getNumbers());
            case SET_VALUE -> setValue.accept(indexes, array.getNumbers());

            case RESET_NODE -> reset.accept(indexes, tree.getNumbers());
            case SWAP_NODES -> swap.accept(indexes, tree.getNumbers());
            case SELECTED_NODE -> selected.accept(indexes, tree.getNumbers());
            case SET_VALUE_NODE -> setValue.accept(indexes, tree.getNumbers());
            case ADD_NODE -> addNode.accept(indexes);
            case DELETE_NODE -> deleteNode.accept(indexes);
            default -> throw new IllegalArgumentException("Unknown change value");
        }
    }

    public void setArray(Array array) {
        this.array = array;
    }

    public void setTree(BinaryTree tree) {
        this.tree = tree;
    }

}
