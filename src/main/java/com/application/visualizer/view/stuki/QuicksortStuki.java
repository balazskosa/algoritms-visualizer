package com.application.visualizer.view.stuki;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class QuicksortStuki extends VerticalLayout {

    public QuicksortStuki() {
        this.add(quicksort());
        this.add(partition());
    }

    private Stuki quicksort() {
        Stuki stuki = new Stuki("Quicksort(A, p, r)");
        stuki.getBody().add(stuki.condition("p < r"));
        Div firstBranch = new Div();
        stuki.getBody().add(stuki.branches(firstBranch, "SKIP"));
        firstBranch.add(stuki.sequence("q := Partition(A; p; r)"));
        firstBranch.add(stuki.sequence("Quicksort(A; p; q - 1)"));
        firstBranch.add(stuki.sequence("Quicksort(A; q + 1; r)"));
        return stuki;
    }

    private Stuki partition() {
        Stuki stuki = new Stuki("Partition(A, p, r)");
        stuki.getBody().add(stuki.sequence("i := p - 1"));
        stuki.getBody().add(stuki.sequence("j := p"));

        Div loop = stuki.loop("j <= 1");
        stuki.getBody().add(loop);

        Div firstBranch = new Div();
        firstBranch.add(stuki.sequence("i := i + 1"));
        firstBranch.add(stuki.sequence("Switch(A[i];A[j])"));

        loop.add(stuki.condition("A[j] <= A[r]"));
        loop.add(stuki.branches(firstBranch, "SKIP"));


        stuki.getBody().add(stuki.sequence("Switch( A[i + 1]; A[r])"));
        stuki.getBody().add(stuki.sequence("q := i + 1"));
        return stuki;
    }
}
