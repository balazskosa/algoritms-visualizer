package com.application.visualizer.data.helper;

import com.vaadin.flow.component.html.*;

public class QuickSortHelper extends Div {

    public QuickSortHelper() {
        sort();
        timeComplexity();

    }

    private void sort() {
        this.add(new H1("What is Quick Sort"));
        this.add(new Span("Quick sort algorithm is one of the most widely used sorting algorithms. It follows a divide and conquer paradigm. We usually use Recursion in quicksort implementation. In each recursive call, a pivot is chosen, then the array is partitioned in such a way that all the elements less than pivot lie to the left and all the elements greater than pivot lie to the right."));
        this.add(new Span("After every call, the chosen pivot occupies its correct position in the array which it is supposed to as in a sorted array. So with each step, our problem gets reduced by 2 which leads to quick sorting. Pivot can be an element. Example: last element of the current array or the first element of current array or random pivot etc."));
    }

    private void timeComplexity() {
        this.add(new H1("Quick Sort Time Complexity"));
        UnorderedList unorderedList = new UnorderedList();
        unorderedList.add(new ListItem("Partition of elements take n time"));
        unorderedList.add(new ListItem("And in quicksort problem is divide by the factor 2"));
        unorderedList.add(new ListItem("Best Time Complexity : O(nlogn)"));
        unorderedList.add(new ListItem("Average Time Complexity : O(nlogn)"));
        unorderedList.add(new ListItem("Worst Time Complexity : O(n^2)"));
        unorderedList.add(new ListItem("Worst Case will happen when array is sorted"));
        this.add(unorderedList);
    }
}
