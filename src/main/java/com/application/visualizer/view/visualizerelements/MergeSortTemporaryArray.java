package com.application.visualizer.view.visualizerelements;

public class MergeSortTemporaryArray extends Array {

    public MergeSortTemporaryArray() {
    }

    public void addNumber(int value) {
        Number number = new Number(value);
        getNumbers().add(number);
        this.add(number);
        //number.animation();
    }

    public void clear() {
        this.removeAll();
        this.getNumbers().clear();
    }


}
