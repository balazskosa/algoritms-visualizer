package com.application.visualizer.data.helper;

import com.vaadin.flow.component.html.*;

public class InsertionShortHelper extends Div {

    public InsertionShortHelper() {
        sort();
        timeComplexity();

    }

    private void sort() {
        this.add(new H1("Insertion Sort "));
        this.add(new Span("Insertion sort is the simple sorting algorithm that virtually splits the given array into sorted and unsorted parts, then the values from the unsorted parts are picked and placed at the correct position in the sorted part."));
        this.add(new Span("I hope you remember the task assigned to David. Let us assume David used the insertion sort technique to complete his task. Then he first chooses a number 1 as a pivot or origin, then starts with the other sheet and places the sheet with the marks and compares it with the marks on selected sheet number 1. If the sheet has fewer marks, he inserts it over and repeats the same process until he gets the sorted array.\n" +
                "Now speaking technically, the insertion sort follows the following algorithm to sort an array of size in ascending order:"));
        this.add(new Span("1. Iterate from arr[1] to arr[n] over the array.\n" +
                "2. Compare the current element (key) to its predecessor.\n" +
                "3. If the key element is smaller than its predecessor, compare its elements before. Move the greater elements one position up to make space for the swapped element."));
    }

    private void timeComplexity() {
        this.add(new H1("Insertion Sort Time Complexity"));
        UnorderedList unorderedList = new UnorderedList();
        unorderedList.add(new ListItem("The worst-case (and average-case) complexity of the insertion sort algorithm is O(n²). Meaning that, in the worst case, the time taken to sort a list is proportional to the square of the number of elements in the list."));
        unorderedList.add(new ListItem("And in quicksort problem is divide by the factor 2"));
        unorderedList.add(new ListItem("The best-case time complexity of insertion sort algorithm is O(n) time complexity. Meaning that the time taken to sort a list is proportional to the number of elements in the list; this is the case when the list is already in the correct order. There’s only one iteration in this case since the inner loop operation is trivial when the list is already in order."));
        unorderedList.add(new ListItem("Insertion sort is frequently used to arrange small lists. On the other hand, Insertion sort isn’t the most efficient method for handling large lists with numerous elements. Notably, the insertion sort algorithm is preferred when working with a linked list. And although the algorithm can be applied to data structured in an array, other sorting algorithms such as quicksort."));

        this.add(unorderedList);
    }
}
