package com.application.visualizer.presentation;

import com.application.visualizer.data.Change;
import com.application.visualizer.view.visualizerelements.Array;
import com.application.visualizer.view.visualizerelements.MergeSortTemporaryArray;
import com.application.visualizer.view.visualizerelements.Number;
import com.application.visualizer.view.visualizerelements.Tree;
import com.vaadin.flow.internal.Pair;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Animation {
    Array array;
    Tree tree;
    MergeSortTemporaryArray mergeSortTemporaryArray;

//    public Animation(Array array, Tree tree) {
//        this.array = array;
//        this.tree = tree;
//    }

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

    private final BiConsumer<Pair<Integer, Integer>, List<Number>> setRightPadding = (index, list) ->
            list.get(index.getFirst()).setRightMargin();

    private final BiConsumer<Pair<Integer, Integer>, List<Number>> resetRightPadding = (index, list) ->
            list.get(index.getFirst()).resetRightMargin();
    private final Consumer<Pair<Integer, Integer>> deleteNode = (index) -> tree.deleteNode();

    private final Consumer<Pair<Integer, Integer>> addNumber = (index) -> mergeSortTemporaryArray.addNumber(index.getFirst());

    private final Consumer<Pair<Integer, Integer>> clear = (index) -> mergeSortTemporaryArray.clear();


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
            case SET_RIGHT_MARGIN -> setRightPadding.accept(indexes, array.getNumbers());
            case RESET_RIGHT_MARGIN -> resetRightPadding.accept(indexes, array.getNumbers());

            case TMP_ARRAY_RESET -> reset.accept(indexes, mergeSortTemporaryArray.getNumbers());
            case TMP_ARRAY_SELECTED -> selected.accept(indexes, mergeSortTemporaryArray.getNumbers());
            case TMP_ARRAY_SET_VALUE -> setValue.accept(indexes, mergeSortTemporaryArray.getNumbers());
            case TMP_ARRAY_SET_RIGHT_MARGIN -> setRightPadding.accept(indexes, mergeSortTemporaryArray.getNumbers());
            case TMP_ARRAY_ADD -> addNumber.accept(indexes);
            case TMP_ARRAY_CLEAR -> clear.accept(indexes);

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

    public void setTree(Tree tree) {
        this.tree = tree;
    }

    public void setMergeSortTemporaryArray(MergeSortTemporaryArray array) {
        this.mergeSortTemporaryArray = array;
    }
    public void addAnimation(Change change, Pair<Integer, Integer> indexes) {
        List<Change> swaps = List.of(Change.SWAP, Change.SWAP_TO_SECOND);
        if (swaps.contains(change)) {
            array.getNumbers().get(indexes.getFirst()).removeClassName("no-transition");
            array.getNumbers().get(indexes.getSecond()).removeClassName("no-transition");
        } else if (change.equals(Change.SWAP_NODES)) {
            tree.getNumbers().get(indexes.getFirst()).removeClassName("no-transition");
            tree.getNumbers().get(indexes.getSecond()).removeClassName("no-transition");
        }
    }

    public void removeAnimationArray() {
        this.array.getNumbers().forEach(number -> number.addClassName("no-transition"));
    }
    public void removeAnimationTree() {
        this.tree.getNumbers().forEach(number -> number.addClassName("no-transition"));
    }
    public void removeAnimationTempArray() {
        this.mergeSortTemporaryArray.getNumbers().forEach(number -> number.addClassName("no-transition"));
    }




}
