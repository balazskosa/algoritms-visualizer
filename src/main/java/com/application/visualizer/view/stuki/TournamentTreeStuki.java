package com.application.visualizer.view.stuki;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class TournamentTreeStuki extends VerticalLayout {


    public TournamentTreeStuki() {
        this.add(tournamentSort());
        this.add(fillTree());
        this.add(treeMax());
    }

    private Stuki fillTree() {
        Stuki stuki = new Stuki("FillTree(t)");
        stuki.getBody().add(stuki.condition("h(t) <= 0"));

        Div secondBranch = new Div();
        stuki.getBody().add(stuki.branches("SKIP", secondBranch));
        secondBranch.add(stuki.sequence("FillTree(t.left)"));
        secondBranch.add(stuki.sequence("FillTree(t.right)"));
        secondBranch.add(stuki.sequence("gy(t) := Max(gy(t.left); gy(t.right))"));

        return stuki;
    }

    private Stuki treeMax() {
        Stuki stuki = new Stuki("TreeMax(t)");
        stuki.getBody().add(stuki.condition("h(t) = 0"));

        Div secondBranch = new Div();
        stuki.getBody().add(stuki.branches("gy(t)" + " := -" + (char)0x221E, secondBranch));
        secondBranch.add(stuki.condition("gy(t) = gy(t.left)"));
        secondBranch.add(stuki.branches("TreeMax (t.left)", "TreeMax (t.right)"));
        secondBranch.add(stuki.sequence("gy(t) := Max(gy(t.left); gy(t.right))"));

        return stuki;
    }

    private Stuki tournamentSort() {
        Stuki stuki = new Stuki("Tournament Sort(t)");

        stuki.getBody().add(stuki.condition("h(t) < 0"));
        Div secondBranch = new Div();
        stuki.getBody().add(stuki.branches("SKIP", secondBranch));
        secondBranch.add(stuki.sequence("FillTree(t)"));
        secondBranch.add(stuki.sequence("write(gy(t))"));
        secondBranch.add(stuki.sequence("n := 2^h(t) - 1"));

        Div loop = stuki.loop("n >= 1");
        loop.add(stuki.sequence("TreeMax(t)"));
        loop.add(stuki.sequence("write(gy(t))"));
        loop.add(stuki.sequence("n := n - 1"));
        secondBranch.add(loop);

        return stuki;
    }
}
