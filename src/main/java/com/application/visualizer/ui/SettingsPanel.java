package com.application.visualizer.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.listbox.ListBox;

@CssImport("./styles/settings-panel.css")
public class SettingsPanel extends Div {

    private Button loadButton;
    private ListBox<String> algorithmBox;
    private ListBox<String> sizeBox;

    public SettingsPanel() {
        this.addClassName("settings-panel");

        setAlgorithmBox();
        setSizeBox();
        setButtonBox();
    }

    private void setAlgorithmBox() {
        this.algorithmBox = new ListBox<>();
        this.algorithmBox.setItems(
                "Maximum Selection Sort",
                "Insertion Sort",
                "Quicksort",
                "Bubble sort");
        this.algorithmBox.setValue("Maximum Selection Sort");

        Div div = new Div();
        div.add(this.algorithmBox);
        this.add(div);
    }

    private void setSizeBox() {
        this.sizeBox = new ListBox<>();
        this.sizeBox.setItems(
                "Small",
                "Medium",
                "Large");
        this.sizeBox.setValue("Medium");

        Div div = new Div();
        div.add(this.sizeBox);
        this.add(div);
    }

    private void setButtonBox() {
        this.loadButton = new Button(new Icon(VaadinIcon.PLAY_CIRCLE_O));

        Div div = new Div();
        div.add(this.loadButton);
        add(div);
    }

    public String getSize() {
        return this.sizeBox.getValue();
    }

    public String getAlgorithm() {
        return this.algorithmBox.getValue();
    }

    public Button getLoadButton() {
        return loadButton;
    }


}
