package com.application.visualizer.view.visualizerelements;

import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.UnorderedList;

import java.util.ArrayList;


public class BinaryTree extends Tree {
    private final ArrayList<UnorderedList> parents = new ArrayList<>();
    int nodeCounter;

    public BinaryTree(int size) {

        //depth of the tree
        int depth = (int) (Math.log(size) / Math.log(2));
        //number of nodes
        int counter = (int) Math.pow(2, depth + 1) - 1;


        for (int i = 0; i < size; i++) {
            Number number = new Number();
            ListItem item = new ListItem(number);
            item.getStyle().set("opacity", "0");
            nodes.add(item);
            numbers.add(number);
        }

        for (int i = size; i < counter; i++) {
            Number number = new Number();
            ListItem item = new ListItem(number);
            item.getStyle().set("opacity", "0");
            nodes.add(item);

        }

        tree.add(nodes.get(0));
        int leaf = 1;

        int parentCounter = (int) (Math.pow(2, depth) - 1);
        for (int i = 0; i < parentCounter; i++) {
            UnorderedList parent = new UnorderedList(nodes.get(leaf++), nodes.get(leaf++));
            nodes.get(i).add(parent);
            parents.add(parent);

            if (nodes.get(leaf - 2).getStyle().get("opacity").equals("0")) parent.getStyle().set("opacity", "0");

        }
        nodeCounter = -1;
    }

    @Override
    public void deleteNode() {
        if (nodeCounter < 0) return;
        if (nodeCounter % 2 != 0) {
            parents.get(nodeCounter / 2).getStyle().set("opacity", "0");
        }
        nodes.get(nodeCounter).getStyle().set("opacity", "0");
        nodeCounter--;
    }

    @Override
    public void addNode() {
        if (nodeCounter == numbers.size() - 1) return;
        nodeCounter++;
        if (nodeCounter % 2 != 0) {
            parents.get(nodeCounter / 2).getStyle().set("opacity", "1");
        }
        nodes.get(nodeCounter).getStyle().set("opacity", "1");
    }

}

