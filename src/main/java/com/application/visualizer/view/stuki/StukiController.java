package com.application.visualizer.view.stuki;

import com.application.visualizer.AlgorithmSettingsPanel;
import com.application.visualizer.SettingsPanel;
import com.vaadin.flow.component.html.Div;

public class StukiController {

    private final SettingsPanel panel;
    private final Div view;

    public StukiController(AlgorithmSettingsPanel panel, Div view) {
        this.view = view;
        this.panel = panel;
        setStuki();

        panel.getGroup().addValueChangeListener((e) -> setStuki());
    }

    private void setStuki() {
        view.removeAll();
        switch (panel.getValue()) {
            case "Maximum Selection Sort"-> view.add(new MaximumSelectionSortStuki());
            case "Tournament Sort"-> view.add(new TournamentTreeStuki());
            case "Insertion Sort"->  view.add(new InsertionSortStuki());
            case "Bubble Sort"->  view.add(new BubbleSortStuki());
            case "Mergesort"->  view.add(new MergeSortStuki());
            case "Quicksort"->  view.add(new QuicksortStuki());
            case "Heapsort"->  view.add(new HeapsortStuki());
            default-> throw new IllegalArgumentException("Unknown algorithm: " + panel.getValue());
        }
    }
}
