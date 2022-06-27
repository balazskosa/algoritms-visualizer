package com.application.visualizer.view.stuki;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class MaximumSelectionSortStuki extends VerticalLayout {


    public MaximumSelectionSortStuki() {
        this.add(maximumSelectionSort());
    }

    private Stuki maximumSelectionSort() {
        Stuki stuki = new Stuki("Maximum Selection Sort(A)");
        stuki.getBody().add(stuki.sequence("j:=n"));
        Div firstLoop = stuki.loop("j>=2");
        stuki.getBody().add(firstLoop);
        firstLoop.add(stuki.sequence("FindMax(A[1...j], ind, max)"));
        firstLoop.add(stuki.sequence("Switch(A[ind], A[j]"));
        firstLoop.add(stuki.sequence("j:=j-1"));
        return stuki;
    }
}

