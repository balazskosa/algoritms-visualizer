package com.application.visualizer.data.helper;

import com.vaadin.flow.component.html.*;

public class BubbleSortHelper extends Div {

    public BubbleSortHelper() {
        sort();
        timeComplexity();

    }
     private void sort() {
        this.add(new H1("What is Bubble Sort"));
        this.add(new Span("Bubble sort is one of the easiest and brute force sorting algorithm. It is used to sort elements in either ascending or descending order. Every element is compared with every other element in bubble sort."));
        this.add(new Span("It basically does swapping of elements if they are not in the right order depending on their value and the intended order. A nested loop will be used to implement this algorithm."));
    }

    private void timeComplexity() {
        this.add(new H1("Bubble Sort Time Complexity"));
        UnorderedList unorderedList = new UnorderedList();
        unorderedList.add(new ListItem("Each and every element is compared with the other elements for array which takes n time"));
        unorderedList.add(new ListItem("And the above steps continues for n iterations"));
        unorderedList.add(new ListItem("Best Time Complexity: O(n^2)"));
        unorderedList.add(new ListItem("Average Time Complexity: O(n^2)"));
        unorderedList.add(new ListItem("Worst Time Complexity: O(n^2)"));
        this.add(unorderedList);
    }
}
