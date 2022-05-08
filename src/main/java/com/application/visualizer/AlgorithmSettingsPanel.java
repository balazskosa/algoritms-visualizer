package com.application.visualizer;

import com.application.visualizer.data.Global;

public class AlgorithmSettingsPanel extends SettingsPanel {
    public AlgorithmSettingsPanel() {
        super("Algorithm", Global.ALGORITHMS, Global.algorithm);
    }


//    private void setChange() {
//        this.getGroup().addValueChangeListener((valueChangeEvent -> {
//            Sort sort;
//            switch(this.getSelectedValue()) {
//                case "Maximum Selection Sort" -> sort = new MaximumSelectionSort(visualizerController.array.getList());
//                case "Insertion sort" -> sort = new InsertionSort(visualizerController.array.getList());
//                case "Bubble sort" -> sort = new BubbleSort(visualizerController.array.getList());
//                case "Quicksort" -> sort = new QuickSort(visualizerController.array.getList());
//                //case "Tournament sort" ->;
//                //case "Mergesort" -> ;
//                //case "Heapsort" ->;
//                default -> {
//                    this.getGroup().setValue(Global.algorithm);
//                    Notification.show("Not implemented algorithms");
//                    throw new IllegalArgumentException("Wrong value");
//                }
//            }
//
//            visualizerController.setMovements(sort.getMovements());
//        })) ;
//    }
}
