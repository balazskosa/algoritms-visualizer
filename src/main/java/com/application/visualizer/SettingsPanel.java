package com.application.visualizer;

import com.application.visualizer.presentation.VisualizerController;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;

import java.util.List;

@CssImport("./styles/settings-panel.css")
public abstract class SettingsPanel extends Div {
    protected final RadioButtonGroup<String> group;
    protected final VisualizerController controller;

    public SettingsPanel(String title, List<String> items, String selectedItem, VisualizerController controller) {
        this.addClassName("settings-panel");
        this.controller = controller;

        this.group = new RadioButtonGroup<>();
        this.group.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        this.group.setLabel(title);
        this.group.setItems(items);
        this.group.setValue(selectedItem);
        this.add(this.group);
    }


}
