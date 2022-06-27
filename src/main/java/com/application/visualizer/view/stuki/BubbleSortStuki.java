package com.application.visualizer.view.stuki;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class BubbleSortStuki extends VerticalLayout {

    public BubbleSortStuki() {
            this.add(bubbleSort());
    }
    public Stuki bubbleSort() {
        Stuki stuki = new Stuki("Bubble Sort(A)");
        stuki.getBody().add(stuki.sequence("j := n"));
        Div firstLoop = stuki.loop("j >= 2");
        stuki.getBody().add(firstLoop);
        firstLoop.add(stuki.sequence("i := 1"));
        Div secondLoop = stuki.loop("j <= j-1");
        firstLoop.add(secondLoop);
        secondLoop.add(stuki.condition("A[i] <= A[i+1]"));
        secondLoop.add(stuki.branches( "SKIP", "SWITCH( A[i]; A[i+1])"));
        secondLoop.add(stuki.sequence("i := i+1"));
        firstLoop.add(stuki.sequence("j := j-1"));

        return stuki;
    }
}
