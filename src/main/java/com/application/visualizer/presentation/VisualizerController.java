package com.application.visualizer.presentation;

import com.application.visualizer.data.Movement;
import com.application.visualizer.data.algorithms.*;
import com.application.visualizer.view.AlgorithmSettingsPanel;
import com.application.visualizer.view.ControlPanel;
import com.application.visualizer.view.CurrentStepPanel;
import com.application.visualizer.view.SizeSettingsPanel;
import com.application.visualizer.view.visualizerelements.*;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class VisualizerController {
    private int counter;
    public List<Movement> movements;
    private final Array array;
    private final CurrentStepPanel currentStepPanel;
    private final ControlPanel controlPanel;
    private final AlgorithmSettingsPanel algorithmSettingsPanel;
    private final SizeSettingsPanel sizeSettingsPanel;
    private Tree tree;
    private MergeSortTemporaryArray mergeSortTemporaryArray;
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
            int small = 8;
            int medium = 11;
            int large = 16;

            switch (sizeSettingsPanel.getSelectedValue()) {
                case "Small" -> array.setItems(randomList(small));
                case "Medium" -> array.setItems(randomList(medium));
                case "Large" -> array.setItems(randomList(large));
                case "Unique" -> {
                    Dialog dialog = new Dialog();
                    dialog.open();
                    
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
        counter = 0;

        if (tree != null) view.remove(tree);
        if (mergeSortTemporaryArray != null) view.remove(mergeSortTemporaryArray);
        tree = null;
        mergeSortTemporaryArray = null;

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
                sort = new TournamentSort(array.getList());
                tree = new TournamentTree(array.getList().size());
            }
            case "Mergesort" -> {
                sort = new MergeSort(array.getList());
                mergeSortTemporaryArray = new MergeSortTemporaryArray();

            }
            default -> throw new IllegalArgumentException("Wrong value");
        }
        movements = sort.getMovements();

        if (tree != null) {
            view.add(tree);
            animation.setTree(tree);
        }

        if (mergeSortTemporaryArray != null) {
            view.add(mergeSortTemporaryArray);
            animation.setMergeSortTemporaryArray(mergeSortTemporaryArray);
        }
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
            animation.addAnimation(currentMovement.getChanges().get(i), currentMovement.getIndex().get(i));
            animation.animation(currentMovement.getChanges().get(i), currentMovement.getIndex().get(i));
        }
    }

    public void start() {
        array.setUnsortedArray();
        if(counter == 0) {
            next();
            return;
        }

        counter = 0;

        if(tree != null) {
            view.remove(tree);
            if(tree instanceof TournamentTree)  {
                tree = new TournamentTree(array.getList().size());
            }
            if(tree instanceof BinaryTree) {
                tree = new BinaryTree(array.getList().size());
            }
            view.add(tree);
            animation.setTree(tree);
        }

        if(mergeSortTemporaryArray != null) {
            view.remove(mergeSortTemporaryArray);
            mergeSortTemporaryArray = new MergeSortTemporaryArray();
            view.add(mergeSortTemporaryArray);
            animation.setMergeSortTemporaryArray(mergeSortTemporaryArray);
        }

    }

    public void end() {
        animation.removeAnimationArray();
        counter = this.movements.size() - 1;

        for (int i = 0; i < counter; i++) {
            Movement movement = movements.get(i);
            if (movement.getChanges() == null) continue;
            for (int j = 0; j < movement.getChanges().size(); j++) {
                animation.animation(movement.getChanges().get(j), movement.getIndex().get(j));
            }
        }

        next();
    }


    public void previous() {
        if (counter < 2) return;

        counter = counter - 2;
        Movement prevMovement = movements.get(counter);
        currentStepPanel.set(prevMovement.getCurrentStep());
        controlPanel.setCounterLabel((counter + 1) + " / " + movements.size());

        if (movements.get(counter + 1).getChanges() == null) {
            counter = counter + 1;
            return;
        }

        array.setUnsortedArray();
        animation.removeAnimationArray();

        if(tree != null) {
            view.remove(tree);
            if(tree instanceof TournamentTree)  {
                tree = new TournamentTree(array.getList().size());
            }
            if(tree instanceof BinaryTree) {
                tree = new BinaryTree(array.getList().size());
            }

            view.add(tree);
            animation.setTree(tree);
            animation.removeAnimationTree();
        }

        if(mergeSortTemporaryArray != null) {
            view.remove(mergeSortTemporaryArray);
            mergeSortTemporaryArray = new MergeSortTemporaryArray();
            view.add(mergeSortTemporaryArray);
            animation.setMergeSortTemporaryArray(mergeSortTemporaryArray);
            animation.removeAnimationTempArray();
        }

        for (int i = 0; i <= counter; i++) {
            Movement movement = movements.get(i);
            if (movement.getChanges() == null) continue;
            for (int j = 0; j < movement.getChanges().size(); j++) {
                animation.animation(movement.getChanges().get(j), movement.getIndex().get(j));
            }

        }
        counter = counter + 1;
    }
}
