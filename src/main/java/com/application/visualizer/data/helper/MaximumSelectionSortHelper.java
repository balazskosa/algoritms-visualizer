package com.application.visualizer.data.helper;

import com.vaadin.flow.component.html.*;

public class MaximumSelectionSortHelper extends Div {

    public MaximumSelectionSortHelper() {
        sort();
        timeComplexity();
    }

    private void sort() {
        this.add(new H1("What is Selection Sort"));
        UnorderedList unorderedList = new UnorderedList();
        unorderedList.add(new ListItem("It is a simple sort algorithm that revolves around the comparison"));
        unorderedList.add(new ListItem("In each iteration, one element gets placed"));
        unorderedList.add(new ListItem("We choose the minimum element in the array and place is at the beginning of the array by swapping with the front element"));
        unorderedList.add(new ListItem("We can also do this by choosing maximum element and placing it at the rear end"));
        unorderedList.add(new ListItem("Selection sort basically selects an element in every iteration and place it at the appropriate position"));
        this.add(unorderedList);
    }

    private void timeComplexity() {
        this.add(new H1("Selection sort Time Complexity"));
        UnorderedList unorderedList = new UnorderedList();
        unorderedList.add(new ListItem("In the worst case, in every iteration, we have to traverse the entire array for finding min elements and this will continue for all n elements. Hence this will perform n^2 operations in total. "));
        unorderedList.add(new ListItem("In the best case that is sorted array, we can do some modification by using lag to check whether the lament is already sorted or not"));
        unorderedList.add(new ListItem("Best Time Complexity: O(n)"));
        unorderedList.add(new ListItem("Average Time Complexity: O(n^2)"));
        unorderedList.add(new ListItem("Worst Time Complexity: O(n^2)"));
        this.add(unorderedList);
    }
}
