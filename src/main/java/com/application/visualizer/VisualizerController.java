package com.application.visualizer;

import com.application.visualizer.data.Movement;
import com.application.visualizer.data.algorithms.MaximumSelectionSort;

import java.util.List;

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

        this.movements = new MaximumSelectionSort(array.getList()).getMovements();
        this.array = array;
        this.currentStepPanel = currentStepPanel;
        this.controlPanel = controlPanel;
        this.algorithmSettingsPanel = algorithmSettingsPanel;
        this.sizeSettingsPanel = sizeSettingsPanel;

        setControlPanelClickListener();
        play();

    }

    private void setControlPanelClickListener() {
        controlPanel.getNextButton().addClickListener((buttonClickEvent -> play()));
        controlPanel.getStartButton().addClickListener((buttonClickEvent -> start()));
        controlPanel.getEndButton().addClickListener((buttonClickEvent -> end()));
        controlPanel.getPreviousButton().setDisableOnClick(true);
    }


    public void play() {
        if (counter == this.movements.size()) return;
        Movement currentMovement = movements.get(counter);

        currentStepPanel.set(currentMovement.getCurrentStep());
        controlPanel.setCounterLabel((counter+1) + " / " + movements.size());
        counter++;

        if (currentMovement.getChanges() == null) return;
        for (int i = 0; i < currentMovement.getChanges().size(); i++) {
            this.array.animation(currentMovement.getChanges().get(i), currentMovement.getIndex().get(i));
        }
    }

    public void start() {
        counter = 0;
        play();
        array.setUnsortedArray();
    }

    public void end() {
        counter = this.movements.size() - 1;
        play();
        array.setSortedArray();

    }

    public void setMovements(List<Movement> movements) {
        this.movements = movements;
        start();
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
