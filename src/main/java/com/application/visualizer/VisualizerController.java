package com.application.visualizer;

import com.application.visualizer.data.Change;
import com.application.visualizer.data.Global;
import com.application.visualizer.data.Movement;
import com.application.visualizer.data.algorithms.*;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.internal.Pair;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class VisualizerController {
    private int counter = 0;
    private List<Movement> movements;
    protected final Array array;
    private final CurrentStepPanel currentStepPanel;
    private final ControlPanel controlPanel;
    private final AlgorithmSettingsPanel algorithmSettingsPanel;
    private final SizeSettingsPanel sizeSettingsPanel;


    public VisualizerController(Array array, CurrentStepPanel currentStepPanel,
                                ControlPanel controlPanel, AlgorithmSettingsPanel algorithmSettingsPanel,
                                SizeSettingsPanel sizeSettingsPanel) {
        this.array = array;
        this.currentStepPanel = currentStepPanel;
        this.controlPanel = controlPanel;
        this.algorithmSettingsPanel = algorithmSettingsPanel;
        this.sizeSettingsPanel = sizeSettingsPanel;

        addAlgorithmChangeListener();
        addSizeChangeListener();
        addControlClickListener();
        setMovements();
    }

    private void addControlClickListener() {
        controlPanel.getNextButton().addClickListener((buttonClickEvent -> next()));
        controlPanel.getStartButton().addClickListener((buttonClickEvent -> start()));
        controlPanel.getEndButton().addClickListener((buttonClickEvent -> end()));
        controlPanel.getPreviousButton().addClickListener((buttonClickEvent -> previous()));
    }

    private void addAlgorithmChangeListener() {
        algorithmSettingsPanel.getGroup().addValueChangeListener((valueChangeEvent) -> setMovements());
    }

    private void addSizeChangeListener() {

        sizeSettingsPanel.getGroup().addValueChangeListener((valueChangeEvent) -> {
            switch (sizeSettingsPanel.getSelectedValue()) {
                case "Small" -> array.setItems(randomList(7));
                case "Medium" -> array.setItems(randomList(12));
                case "Large" -> array.setItems(randomList(15));
                //case "Unique" ->;
                default -> {
                    algorithmSettingsPanel.getGroup().setValue(Global.size);
                    Notification.show("Not implemented size");
                    throw new IllegalArgumentException("Not implemented size");
                }
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
        switch (algorithmSettingsPanel.getSelectedValue()) {
            case "Maximum Selection Sort" -> sort = new MaximumSelectionSort(array.getList());
            case "Insertion sort" -> sort = new InsertionSort(array.getList());
            case "Bubble sort" -> sort = new BubbleSort(array.getList());
            case "Quicksort" -> sort = new QuickSort(array.getList());
            //case "Tournament sort" ->;
            //case "Mergesort" -> ;
            //case "Heapsort" ->;
            default -> {
                algorithmSettingsPanel.getGroup().setValue(Global.algorithm);
                Notification.show("Not implemented algorithm");
                throw new IllegalArgumentException("Wrong value");
            }
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
            this.array.animation(currentMovement.getChanges().get(i), currentMovement.getIndex().get(i));
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
            case SWAP -> array.animation(Change.SWAP, new Pair<>(indexes.getSecond(), indexes.getFirst()));
            case RESET, SECOND_SELECTED, SELECTED, SORTED, THIRD_SELECTED -> {
                Change prevChange = findPreviousChange(indexes.getFirst());
                array.animation(prevChange, indexes);
            }
            default -> throw new IllegalArgumentException("Unknown change value");
        }
    }

    private Change findPreviousChange(int index) {

        for (int i = this.counter-1; i >= 0; i--) {
            Movement currentMovement = this.movements.get(i);
            if (currentMovement.getChanges() == null ) continue;
            var find = findIndexWithChange(currentMovement, index);
            if (find.getFirst()) {
                System.out.println("find");
                return find.getSecond();
            }
        }
        System.out.println("not find");
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
