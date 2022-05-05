package com.application.visualizer.ui;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;

@CssImport("./styles/display-current-step.css")
public class DisplayCurrentStep extends Div {

    Div currentStep = new Div();

    public DisplayCurrentStep() {
        this.addClassName("display-current-step");
        Div showStep = new Div();
        showStep.setText("Step:");
        showStep.getStyle().set("font-weight", "bold");
        showStep.getStyle().set("font-size", "1em");
        this.add(showStep);
        this.add(currentStep);

    }

    public void set(String currenStep) {
        this.currentStep.setText(currenStep);
    }
}
