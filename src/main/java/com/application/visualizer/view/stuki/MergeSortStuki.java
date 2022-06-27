package com.application.visualizer.view.stuki;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class MergeSortStuki extends VerticalLayout {

    public MergeSortStuki() {
        this.add(mergeSort());
        this.add(merge());
    }

    private Stuki mergeSort() {
        Stuki stuki = new Stuki("Merge Sort(A, l, r)");
        stuki.getBody().add(stuki.condition("l < r"));
        Div firstBranch = new Div();
        stuki.getBody().add(stuki.branches(firstBranch, "SKIP"));
        firstBranch.add(stuki.sequence("m := l + (r - l) / 2"));
        firstBranch.add(stuki.sequence("Merge Sort(A, l, m)"));
        firstBranch.add(stuki.sequence("Merge Sort(A, m + 1, r)"));
        firstBranch.add(stuki.sequence("Merge(A, l, m, r)"));
        return stuki;
    }

    private Stuki merge() {
        Stuki stuki = new Stuki("Merge(A, l, m, r)");
        stuki.getBody().add(stuki.sequence("n1 := m - l + 1"));
        stuki.getBody().add(stuki.sequence("n2 := r - m"));
        stuki.getBody().add(stuki.sequence("L := A[l..m]"));
        stuki.getBody().add(stuki.sequence("R := A[m+1..r]"));
        stuki.getBody().add(stuki.sequence("i,j := 0, k := l"));

        Div loop = stuki.loop("i < n1 \tʌ j < n2");
        stuki.getBody().add(loop);

        Div firstBranch = new Div();
        firstBranch.add(stuki.sequence("A[k] := L[i]"));
        firstBranch.add(stuki.sequence("i++"));

        Div secondBranch = new Div();
        secondBranch.add(stuki.sequence("A[k] := R[j]"));
        secondBranch.add(stuki.sequence("j++"));

        loop.add(stuki.condition("L[i] <= R[j]"));
        loop.add(stuki.branches(firstBranch, secondBranch));
        loop.add(stuki.sequence("k := k + 1"));


        stuki.getBody().add(stuki.condition("i < n1"));
        stuki.getBody().add(stuki.branches("A[k..n1+n2] = L[i..n1]", "A[k..n1+n2] = R[j..n2]"));


        return stuki;
    }
}
