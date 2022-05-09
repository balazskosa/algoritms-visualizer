package com.application.complexity;

import com.application.complexity.data.BubbleSortComplexity;
import com.application.complexity.data.InsertionSortComplexity;
import com.application.complexity.data.MaximumSelectionSortComplexity;
import com.application.complexity.data.QuickSortComplexity;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;

@CssImport("./styles/complexity-panel.css")
public class ComplexityPanel extends Div {
    private final int minValue = 10;
    private final int maxValue = 50000;
    private final AlgorithmComplexityDiv maxSelectionSort;
    private final AlgorithmComplexityDiv insertionSort;
    private final AlgorithmComplexityDiv bubbleSort;
    private final AlgorithmComplexityDiv quicksort;
    private final TextField sizeField;
    public ComplexityPanel() {
        this.addClassName("complexity-panel");

        Div header = new Div();
        header.add(new H2("Complexity"));
        this.add(header);

        Button calculate = new Button(new Icon(VaadinIcon.REFRESH));
        this.sizeField = new TextField();
        this.sizeField.setPlaceholder("Size ... [" + minValue +  ", " + maxValue +"]");
        this.add(sizeField, calculate);

        calculate.addClickListener((buttonClickEvent -> {
            int size;
            try {
                size = Integer.parseInt(this.sizeField.getValue());
                if(size < minValue || size > maxValue) throw new IllegalArgumentException();
            } catch (Exception e) {
                Notification.show("Wrong value");
                return;
            }
            setAlgorithmsValues(size);
            Notification.show("Calculate finished");
        }));

        Div secondHeader = new Div();
        secondHeader.addClassName("complexity-panel-inline-block");
        H3 algorithm = new H3("Algorithm");
        algorithm.getStyle().set("width", "15vw");
        H3 comparison = new H3("Comparison");
        comparison.getStyle().set("width", "8vw").set("text-align", "right");
        H3 swap = new H3("Swap");
        swap.getStyle().set("width", "8vw").set("text-align", "right");
        secondHeader.add(algorithm, comparison, swap);
        this.add(secondHeader);


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
