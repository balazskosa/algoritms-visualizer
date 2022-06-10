package com.application.visualizer.presentation;

import com.application.visualizer.data.Global;
import com.application.visualizer.data.Movement;
import com.application.visualizer.data.algorithms.Sort;
import com.application.visualizer.view.ControlPanel;
import com.application.visualizer.view.CurrentStepPanel;
import com.application.visualizer.view.visualizerelements.*;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.List;

public class VisualizerController {
    private int counter;
    public List<Movement> movements;
    private final Array array;
    private final CurrentStepPanel currentStepPanel;
    private final ControlPanel controlPanel;
    private Tree tree;
    private MergeSortTemporaryArray mergeSortTemporaryArray;
    private final VerticalLayout view;
    private final Animation animation;

    private Sort sort;

    public VisualizerController(Array array,
                                CurrentStepPanel currentStepPanel,
                                ControlPanel controlPanel,
                                VerticalLayout view) {
        this.array = array;
        this.currentStepPanel = currentStepPanel;
        this.controlPanel = controlPanel;
        this.view = view;
        this.animation = new Animation(array);


        try {
            this.sort = (Sort) Class.forName("com.application.visualizer.data.algorithms." + Global.algorithm.replaceAll("\\s+", ""))
                    .getConstructor(List.class).newInstance(array.getList());
        } catch (Exception e) {
            Notification.show("Something wrong with the sorting algorithm's initializing");
            return;
        }

        addControlClickListener();
        setMovements();


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

    public void setMovements() {
        counter = 0;
        movements = sort.getMovements();
        setTempArray();
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
        if (counter == 0) {
            next();
            return;
        }

        counter = 0;
        setTempArray();
        next();

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

        if (tree != null) {
            view.remove(tree);
            if (tree instanceof TournamentTree) {
                tree = new TournamentTree(array.getList().size());
            }
            if (tree instanceof BinaryTree) {
                tree = new BinaryTree(array.getList().size());
            }

            view.add(tree);
            animation.setTree(tree);
            animation.removeAnimationTree();
        }

        if (mergeSortTemporaryArray != null) {
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

    public void setTree(Tree tree) {
        this.tree = tree;
    }

    public void setMergeSortTemporaryArray(MergeSortTemporaryArray mergeSortTemporaryArray) {
        this.mergeSortTemporaryArray = mergeSortTemporaryArray;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public Array getArray() {
        return array;
    }

    public Sort getSort() {
        return sort;
    }
    private void setTempArray() {
        if(view.getComponentCount() == 2) {
            view.remove(view.getComponentAt(1));

            if(tree != null) {
                tree = null;
            } else if(mergeSortTemporaryArray != null) mergeSortTemporaryArray = null;
        }


        switch (sort.getClass().getSimpleName()) {
            case "Mergesort" -> {
                mergeSortTemporaryArray = new MergeSortTemporaryArray();
                view.add(mergeSortTemporaryArray);
                animation.setMergeSortTemporaryArray(mergeSortTemporaryArray);
            }
            case "Heapsort" -> {
                tree = new BinaryTree(array.getList().size());
                view.add(tree);
                animation.setTree(tree);
            }
            case "TournamentSort" -> {
                tree = new TournamentTree(array.getList().size());
                view.add(tree);
                animation.setTree(tree);
            }
        }
    }

}
