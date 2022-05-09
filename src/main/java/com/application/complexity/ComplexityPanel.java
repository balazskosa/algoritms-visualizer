package com.application.complexity;

import com.application.complexity.data.BubbleSortComplexity;
import com.application.complexity.data.InsertionSortComplexity;
import com.application.complexity.data.MaximumSelectionSortComplexity;
import com.application.complexity.data.QuickSortComplexity;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;

@CssImport("./styles/complexity-panel.css")
public class ComplexityPanel extends Div {

    private final AlgorithmComplexityDiv maxSelectionSort;
    private final AlgorithmComplexityDiv insertionSort;
    private final AlgorithmComplexityDiv bubbleSort;
    private final AlgorithmComplexityDiv quicksort;
    public ComplexityPanel() {
        this.addClassName("complexity-panel");
        add(new H2("Complexity"));

        Div header = new Div();
        header.addClassName("complexity-panel-inline-block");
        H3 algorithm = new H3("Algorithm");
        algorithm.getStyle().set("width", "15vw");
        header.add(algorithm, new H3("Comparison"), new H3("Swap"));
        this.add(header);

        this.maxSelectionSort = new AlgorithmComplexityDiv("Maximum Selection Sort");
        this.insertionSort = new AlgorithmComplexityDiv("Insertion Sort");
        this.bubbleSort = new AlgorithmComplexityDiv("Bubble Sort");
        this.quicksort = new AlgorithmComplexityDiv("Quicksort");

        this.add(maxSelectionSort, insertionSort, bubbleSort, quicksort);
    }

    public void setAlgorithmsValues(int size) {
        maxSelectionSort.setValues(new MaximumSelectionSortComplexity(size));
        insertionSort.setValues(new InsertionSortComplexity(size));
        bubbleSort.setValues(new BubbleSortComplexity(size));
        quicksort.setValues(new QuickSortComplexity(size));
    }
}
