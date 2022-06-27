package com.application.visualizer.view.stuki;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class InsertionSortStuki extends VerticalLayout {

    public InsertionSortStuki() {
        this.add(insertionSort());
    }

    private Stuki insertionSort() {
        Stuki stuki = new Stuki("Insertion Sort(A)");
        stuki.getBody().add(stuki.sequence("j := 1"));
        Div firstLoop = stuki.loop("j <= n-1");
        stuki.getBody().add(firstLoop);
        firstLoop.add(stuki.sequence("i := j"));
        firstLoop.add(stuki.sequence("w := A[i + 1]"));
        Div secondLoop = stuki.loop("i >=1 \tʌ A[i] > w");
        secondLoop.add(stuki.sequence("A[i + 1] := A[i]"));
        secondLoop.add(stuki.sequence("i := i-1"));
        firstLoop.add(secondLoop);
        firstLoop.add(stuki.sequence("A[i + 1] := w"));
        firstLoop.add(stuki.sequence("j := j + 1"));
        return stuki;
    }
}
