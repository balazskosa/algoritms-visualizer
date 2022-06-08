package com.application.visualizer.view.visualizerelements;

import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.UnorderedList;

public class TournamentTree extends Tree {
    public TournamentTree(int size) {
        //number of nodes
        int counter = 2 * size - 1;

        for (int i = 0; i < counter; i++) {
            Number number = new Number();
            ListItem node = new ListItem(number);
            numbers.add(number);
            nodes.add(node);
        }

        tree.add(nodes.get(0));
        int leaf = 1;

        int parentCounter = size - 1;
        for (int i = 0; i < parentCounter; i++) {
            UnorderedList parent = new UnorderedList(nodes.get(leaf++), nodes.get(leaf++));
            nodes.get(i).add(parent);
        }
    }


}
