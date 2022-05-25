package com.application.visualizer.view;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;

import java.util.ArrayList;
import java.util.List;

@CssImport("./styles/array.css")
public class Array extends Div {

    private List<Integer> list;

    private final List<Number> numbers = new ArrayList<>();

    public Array(List<Integer> list) {
        this.addClassName("array");
        setItems(list);

    }
    public List<Integer> getList() {
        return list;
    }

    public void setItems(List<Integer> list) {
        this.list = new ArrayList<>(list);
        this.removeAll();
        this.numbers.clear();
        list.forEach((value) -> {
            Number number = new Number(value);
            numbers.add(number);
            this.add(number);
            //number.animation();
        });
    }


    public void setUnsortedArray() {
        setItems(list);
    }

    public void setSortedArray() {
        List<Integer> sortedList = list.stream().sorted().toList();
        for (int i = 0; i < list.size(); i++) {
            numbers.get(i).setValue(sortedList.get(i));
            numbers.get(i).sortedStyle();
        }
    }

    public List<Number> getNumbers() {
        return numbers;
    }
}
