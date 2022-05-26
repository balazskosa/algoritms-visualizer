package com.application.visualizer.presentation;

import com.application.visualizer.data.Change;
import com.application.visualizer.data.Global;
import com.application.visualizer.data.Movement;
import com.application.visualizer.data.algorithms.*;
import com.application.visualizer.view.AlgorithmSettingsPanel;
import com.application.visualizer.view.ControlPanel;
import com.application.visualizer.view.CurrentStepPanel;
import com.application.visualizer.view.SizeSettingsPanel;
import com.application.visualizer.view.visualizerelements.Array;
import com.application.visualizer.view.visualizerelements.BinaryTree;
import com.application.visualizer.view.visualizerelements.TournamentTree;
import com.application.visualizer.view.visualizerelements.Tree;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.internal.Pair;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class VisualizerController {
    private int counter = 0;
    private List<Movement> movements;
    private final Array array;
    private final CurrentStepPanel currentStepPanel;
    private final ControlPanel controlPanel;
    private final AlgorithmSettingsPanel algorithmSettingsPanel;
    private final SizeSettingsPanel sizeSettingsPanel;

    private Tree tree;
    private final VerticalLayout view;
    private final Animation animation;

    public VisualizerController(Array array, CurrentStepPanel currentStepPanel,
                                ControlPanel controlPanel, AlgorithmSettingsPanel algorithmSettingsPanel,
                                SizeSettingsPanel sizeSettingsPanel, VerticalLayout view) {
        this.array = array;
        this.currentStepPanel = currentStepPanel;
        this.controlPanel = controlPanel;
        this.algorithmSettingsPanel = algorithmSettingsPanel;
        this.sizeSettingsPanel = sizeSettingsPanel;
        this.view = view;


        addAlgorithmChangeListener();
        addSizeChangeListener();
        addControlClickListener();
        setMovements();

        this.animation = new Animation(array);


    }

    private void addControlClickListener() {
        controlPanel.getNextButton().addClickListener((buttonClickEvent -> next()));
        controlPanel.getNextButton().addClickShortcut(Key.ARROW_RIGHT);

        controlPanel.getStartButton().addClickListener((buttonClickEvent -> start()));
        controlPanel.getStartButton().addClickShortcut(Key.ARROW_DOWN);

        controlPanel.getEndButton().addClickListener((buttonClickEvent -> end()));
        controlPanel.getEndButton().addClickShortcut(Key.ARROW_UP);

        controlPanel.getPreviousButton().addClickListener((buttonClickEvent -> previous()));
        controlPanel.getPreviousButton().addClickShortcut(Key.ARROW_LEFT);
    }

    public String getAlgorithm() {
        return this.algorithmSettingsPanel.getSelectedValue();
    }

    private void addAlgorithmChangeListener() {
        algorithmSettingsPanel.getGroup().addValueChangeListener((valueChangeEvent) -> setMovements());
    }

    private void addSizeChangeListener() {

        sizeSettingsPanel.getGroup().addValueChangeListener((valueChangeEvent) -> {
            switch (sizeSettingsPanel.getSelectedValue()) {
                case "Small" -> array.setItems(randomList(7));
                case "Medium" -> array.setItems(randomList(12));
                case "Large" -> array.setItems(randomList(16));
                case "Unique" -> {
                    Notification.show("Not implemented size");
                    array.setItems(randomList(12));
                    sizeSettingsPanel.getGroup().setValue(Global.size);
                }
                default -> throw new IllegalArgumentException("Not implemented size");
            }

            setMovements();
        });
    }

    private List<Integer> randomList(int n) {
        Set<Integer> set = new LinkedHashSet<>();
        Random rand = new Random();
        while (set.size() != n) {
            int randomValue = rand.nextInt(100);
            set.add(randomValue);
        }
        return set.stream().toList();
    }

    public void setMovements() {
        Sort sort;
        if (tree != null) view.remove(tree);
        tree = null;

        switch (algorithmSettingsPanel.getSelectedValue()) {

            case "Maximum Selection Sort" -> sort = new MaximumSelectionSort(array.getList());
            case "Insertion sort" -> sort = new InsertionSort(array.getList());
            case "Bubble sort" -> sort = new BubbleSort(array.getList());
            case "Quicksort" -> sort = new QuickSort(array.getList());
            case "Heapsort" -> {
                sort = new HeapSort(array.getList());
                tree = new BinaryTree(array.getList().size());
            }

            case "Tournament sort" -> {
                Notification.show("tournament");
                sort = new TournamentSort(array.getList());
                tree = new TournamentTree(array.getList().size());
            }
            case "Mergesort" -> {
                sort = new MaximumSelectionSort(array.getList());
                algorithmSettingsPanel.getGroup().setValue(Global.algorithm);
                Notification.show("Not implemented algorithm");
            }
            default -> throw new IllegalArgumentException("Wrong value");
        }

        if (tree != null) {
            view.add(tree);
            animation.setTree(tree);
        }

        movements = sort.getMovements();
        start();
    }

    public void next() {

        if (counter == this.movements.size()) return;
        Movement currentMovement = movements.get(counter);
        currentStepPanel.set(currentMovement.getCurrentStep());
        controlPanel.setCounterLabel((counter + 1) + " / " + movements.size());
        counter++;

        if (currentMovement.getChanges() == null) return;
        for (int i = 0; i < currentMovement.getChanges().size(); i++) {
            animation.animation(currentMovement.getChanges().get(i), currentMovement.getIndex().get(i));
        }

    }

    public void start() {
        counter = 0;
        next();
        array.setUnsortedArray();
    }

    public void end() {
        counter = this.movements.size() - 1;
        next();
        array.setSortedArray();
    }


    public void previous() {
        if (counter < 2) return;

        counter = counter - 2;
        Movement prevMovement = movements.get(counter);
        currentStepPanel.set(prevMovement.getCurrentStep());
        controlPanel.setCounterLabel((counter + 1) + " / " + movements.size());

        counter = counter + 1;

        prevMovement = movements.get(counter);
        if (prevMovement.getChanges() == null) return;

        for (int i = 0; i < prevMovement.getChanges().size(); i++) {
            currentAnimationBackward(prevMovement.getChanges().get(i), prevMovement.getIndex().get(i));
        }

    }


    private void currentAnimationBackward(Change change, Pair<Integer, Integer> indexes) {

        switch (change) {
            case SWAP -> animation.animation(Change.SWAP, new Pair<>(indexes.getSecond(), indexes.getFirst()));
            case RESET, SECOND_SELECTED, SELECTED, SORTED, THIRD_SELECTED -> {
                Change prevChange = findPreviousChange(indexes.getFirst());
                animation.animation(prevChange, indexes);
            }
            default -> throw new IllegalArgumentException("Unknown change value");
        }
    }

    private Change findPreviousChange(int index) {

        for (int i = this.counter - 1; i >= 0; i--) {
            Movement currentMovement = this.movements.get(i);
            if (currentMovement.getChanges() == null) continue;
            var find = findIndexWithChange(currentMovement, index);
            if (find.getFirst()) {
                return find.getSecond();
            }
        }
        return Change.RESET;
    }


    private Pair<Boolean, Change> findIndexWithChange(Movement currentMovement, int index) {
        for (int j = 0; j < currentMovement.getChanges().size(); j++) {

            boolean findIndex = currentMovement.getIndex().get(j).getFirst() == index;
            boolean findChangeStyle = currentMovement.getChanges().get(j) != Change.SWAP;

            if (findIndex && findChangeStyle) return new Pair<>(true, currentMovement.getChanges().get(j));
        }

        return new Pair<>(false, null);
    }
}
