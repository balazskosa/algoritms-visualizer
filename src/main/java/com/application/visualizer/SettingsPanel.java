package com.application.visualizer;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;

import java.util.List;

@CssImport("./styles/settings-panel.css")
public abstract class SettingsPanel extends Div {
    private final RadioButtonGroup<String> group;

    public SettingsPanel(String title, List<String> items, String selectedItem) {
        this.addClassName("settings-panel");

        this.group = new RadioButtonGroup<>();
        this.group.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        this.group.setLabel(title);
        this.group.setItems(items);
        this.group.setValue(selectedItem);
        this.add(this.group);
    }

    public RadioButtonGroup<String> getGroup() {
        return group;
    }

    public String getSelectedValue() {
        return this.group.getValue();
    }

}
