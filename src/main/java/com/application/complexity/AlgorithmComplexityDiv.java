package com.application.complexity;

import com.application.complexity.data.SortComplexity;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H4;

public class AlgorithmComplexityDiv extends Div {

    private final H4 comparisonCounter = new H4();
    private final H4 swapCounter = new H4();

    public AlgorithmComplexityDiv(String algorithm) {
        H4 algorithm1 = new H4();
        algorithm1.setText(algorithm);
        this.comparisonCounter.setText("unknown");
        this.comparisonCounter.getStyle().set("width", "8vw").set("text-align", "right");
        this.swapCounter.setText("unknown");
        this.swapCounter.getStyle().set("width", "8vw").set("text-align", "right");
        algorithm1.getStyle().set("width", "15vw");
        this.add(algorithm1, this.comparisonCounter, this.swapCounter);
    }

    protected void setValues(SortComplexity sortComplexity) {
        this.comparisonCounter.setText(String.valueOf(sortComplexity.getComparisonCounter()));
        this.swapCounter.setText(String.valueOf(sortComplexity.getSwapCounter()));
    }

}
