package com.application.visualizer.view;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H4;

@CssImport("./styles/stuki.css")
public class Stuki extends Div {
    private final Div body = new Div();
    public Stuki(String name) {
        this.addClassName("stuki");

        Div header = new Div();
        header.add(new H4(name));
        header.addClassName("header");
        this.add(header);

        body.addClassName("body");
        this.add(body);

    }
    public Div sequence(String name) {

        Div sequence = new Div();
        sequence.addClassName("sequence");
        sequence.add(name);
        return sequence;
    }

    public Div condition(String name) {
        Div condition = new Div();
        condition.addClassName("condition");
        condition.add(name);
        return condition;
    }

    public Div loop(String name) {
        Div loop = new Div();
        loop.addClassName("loop");
        loop.add(name);
        return loop;
    }

    public Div branches(String firstCondition, String secondCondition) {
        Div branches = new Div();
        branches.addClassName("branches");
        Div firstBranch = sequence(firstCondition);
        Div secondBranch = sequence(secondCondition);

        branches.add(firstBranch, secondBranch);

        return branches;
    }

    public Div getBody() {
        return body;
    }

}