package com.application.visualizer.view.visualizerelements;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.UnorderedList;

import java.util.ArrayList;
import java.util.List;

@CssImport("./styles/tree.css")
public abstract class Tree extends Div {

    protected final ArrayList<ListItem> nodes = new ArrayList<>();
    protected final ArrayList<Number> numbers = new ArrayList<>();
    protected UnorderedList tree = new UnorderedList();
    public Tree() {
        this.addClassName("tree");
        Div container = new Div();
        container.addClassName("container");
        container.add(tree);
        this.add(container);
    }

    public void addNode() {}

    public void deleteNode() {}

    public Number getNumberAtIndex(int index) {
        return numbers.get(index);
    }
    public List<ListItem> getNodes() {
        return nodes;
    }
    public ArrayList<Number> getNumbers() {
        return numbers;
    }

}
