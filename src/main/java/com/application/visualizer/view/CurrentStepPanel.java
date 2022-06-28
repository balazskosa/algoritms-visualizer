package com.application.visualizer.view;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;

@CssImport("./styles/current-step-panel.css")
public class CurrentStepPanel extends Div {
    private final Div currentStep = new Div();
    public CurrentStepPanel() {
        this.addClassName("current-step-panel");
        Div showStep = new Div();
        showStep.setText("Step:");
        showStep.addClassName("step");
        this.add(showStep);
        this.add(currentStep);
    }
    public void set(String currenStep) {
        this.currentStep.setText(currenStep);
    }
}
