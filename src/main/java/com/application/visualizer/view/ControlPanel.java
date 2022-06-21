package com.application.visualizer.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;

@CssImport("./styles/control-panel.css")
public class ControlPanel extends Div {

    private final Label counterLabel;
    private final Button startButton;
    private final Button endButton;
    private final Button nextButton;
    private final Button previousButton;

    public ControlPanel() {
        this.addClassName("control-panel");
        this.counterLabel = new Label();

        startButton = new Button(new Icon(VaadinIcon.ANGLE_DOUBLE_LEFT));
        endButton = new Button(new Icon(VaadinIcon.ANGLE_DOUBLE_RIGHT));
        nextButton = new Button(new Icon(VaadinIcon.ANGLE_RIGHT));
        previousButton = new Button(new Icon(VaadinIcon.ANGLE_LEFT));

        this.add(this.startButton, this.previousButton, this.counterLabel, this.nextButton, this.endButton);
    }

    public void setCounterLabel(String display) {
        this.counterLabel.setText(display);
    }

    public Button getStartButton() {
        return startButton;
    }

    public Button getEndButton() {
        return endButton;
    }

    public Button getNextButton() {
        return nextButton;
    }

    public Button getPreviousButton() {
        return previousButton;
    }

}
