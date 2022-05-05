package com.application.visualizer.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;

@CssImport("./styles/control.css")
public class Control extends Div {

    private final Label counterLabel;
    private final Array array;

    public Control(Array array) {
        this.addClassName("control");
        this.array = array;

        this.counterLabel = new Label();
        this.counterLabel.setText(this.array.displayCounter());

        Button startButton = new Button(new Icon(VaadinIcon.ANGLE_DOUBLE_LEFT));
        Button endButton = new Button(new Icon(VaadinIcon.ANGLE_DOUBLE_RIGHT));
        Button nextButton = new Button(new Icon(VaadinIcon.ANGLE_RIGHT));
        Button previousButton = new Button(new Icon(VaadinIcon.ANGLE_LEFT));

        startButton.addClickListener((buttonClickEvent -> {
            this.array.setStart();
            setCounterLabel();
        }));
        endButton.addClickListener((buttonClickEvent -> {
            this.array.setEnd();
            setCounterLabel();
        }));
        nextButton.addClickListener((buttonClickEvent -> {
            this.array.play();
            setCounterLabel();
        }));

        previousButton.setDisableOnClick(true);

        this.add(startButton, previousButton, counterLabel, nextButton, endButton);

    }

    private void setCounterLabel() {
        this.counterLabel.setText(this.array.displayCounter());
    }
}
