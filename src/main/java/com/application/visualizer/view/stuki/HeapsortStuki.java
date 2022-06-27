package com.application.visualizer.view.stuki;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class HeapsortStuki extends VerticalLayout {

    public HeapsortStuki() {
        this.add(heapsort());
        this.add(buildHeap());
        this.add(makeHeap());
    }

    private Stuki heapsort() {
        Stuki stuki = new Stuki("Heapsort(A)");
        stuki.getBody().add(stuki.sequence("BuildHeap(A)"));
        stuki.getBody().add(stuki.sequence("i := Meret(A)"));

        Div loop = stuki.loop("i > 1");
        stuki.getBody().add(loop);

        loop.add(stuki.sequence("Switch(A[1], A[i])"));
        loop.add(stuki.sequence("A := A[1..n - 1]"));
        loop.add(stuki.sequence("MakeHeap(A, 1)"));

        return stuki;
    }

    private Stuki makeHeap() {
        Stuki stuki = new Stuki("MakeHeap(A, i)");
        stuki.getBody().add(stuki.sequence("n := Size(A)"));
        stuki.getBody().add(stuki.sequence("r := 2i + 1"));
        stuki.getBody().add(stuki.sequence("l := 2i"));

        stuki.getBody().add(stuki.condition("l <= n \tʌ A[l] > A[i]"));
        stuki.getBody().add(stuki.branches("max := l", "max := i"));

        stuki.getBody().add(stuki.condition("r <= n \tʌ A[r] > A[max]"));
        stuki.getBody().add(stuki.branches("max := r", "SKIP"));

        stuki.getBody().add(stuki.condition("max != i"));
        Div firstBranch = new Div();
        firstBranch.add(stuki.sequence("Switch(A[i];A[max])"));
        firstBranch.add(stuki.sequence("MakeHeap(A; max)"));
        stuki.getBody().add(stuki.branches(firstBranch, "SKIP"));

        return stuki;
    }

    private Stuki buildHeap() {
        Stuki stuki = new Stuki("BuildHeap(A)");
        stuki.getBody().add(stuki.condition("n := Size(A)"));
        stuki.getBody().add(stuki.condition("i := ⌊n/2⌋"));

        Div loop = stuki.loop("i > 1");
        stuki.getBody().add(loop);

        loop.add(stuki.sequence("MakeHeap(A; i)"));
        loop.add(stuki.sequence("i := i - 1"));

        return stuki;
    }
}
