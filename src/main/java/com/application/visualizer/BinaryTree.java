package com.application.visualizer;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.UnorderedList;

import java.util.ArrayList;
import java.util.List;

@CssImport("./styles/binary-tree.css")
public class BinaryTree extends Div {
    private final ArrayList<ListItem> nodes = new ArrayList<>();
    private final ArrayList<UnorderedList> parents = new ArrayList<>();
    private final ArrayList<Number> numbers = new ArrayList<>();



    int nodeCounter;

    public BinaryTree(List<Integer> list) {
        this.addClassName("tree");

        //depth of the tree
        int depth = (int) (Math.log(list.size()) / Math.log(2));
        //number of nodes
        int counter = (int) Math.pow(2, depth + 1) - 1;


        for (Integer integer : list) {
            Number number = new Number(integer);
            ListItem item = new ListItem(number);
            item.getStyle().set("opacity", "0");
            nodes.add(item);
            numbers.add(number);
        }

        for (int i = list.size(); i < counter; i++) {
            Number number = new Number();
            ListItem item = new ListItem(number);
            item.getStyle().set("opacity", "0");
            nodes.add(item);

        }

        UnorderedList tree = new UnorderedList();
        tree.add(nodes.get(0));

        int leaf = 1;
        int parentCounter = (int) (Math.pow(2, depth) - 1);
        for (int i = 0; i < parentCounter; i++) {
            UnorderedList parent = new UnorderedList(nodes.get(leaf++), nodes.get(leaf++));
            nodes.get(i).add(parent);
            parents.add(parent);

            if(nodes.get(leaf-2).getStyle().get("opacity").equals("0")) parent.getStyle().set("opacity", "0");

        }

        Div container = new Div();
        container.addClassName("container");
        container.add(tree);
        this.add(container);

        nodeCounter = -1;
    }

    public List<ListItem> getNodes() {
        return nodes;
    }
    public Number getNumberAtIndex(int index) {
        return numbers.get(index);
    }

    public void deleteNode() {
        if (nodeCounter < 0) return;
        if(nodeCounter % 2 != 0) {
            parents.get(nodeCounter/2).getStyle().set("opacity", "0");
        }
        nodes.get(nodeCounter).getStyle().set("opacity", "0");
        nodeCounter--;
    }

    public void addNode() {
        if (nodeCounter == numbers.size() - 1) return;
        nodeCounter++;
        if(nodeCounter % 2 != 0) {
            parents.get(nodeCounter/2).getStyle().set("opacity", "1");
        }
        nodes.get(nodeCounter).getStyle().set("opacity", "1");
    }

}

