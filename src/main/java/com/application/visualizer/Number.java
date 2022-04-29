package com.application.visualizer;

import com.application.visualizer.fixed.Global;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;

@CssImport("./styles/number.css")
public class Number extends Div {

    private int value;
    private Boolean isAnimated = false;

    public Number(Integer value) {
        setValue(value);
        setBackgroundColor(Global.BASE_BACKGROUND_COLOR);
        this.addClassName("number");
    }

    public int getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
        this.setText(String.valueOf(value));
    }

    public void animation() {
        if (this.isAnimated) {
            this.getElement().getStyle().set("animation", "switchFirst 3s");
            this.isAnimated = false;
            return;
        }
        this.getElement().getStyle().set("animation", "switchSecond 3s");
        this.isAnimated = true;
    }

    public void setBackgroundColor(String color) {
        this.getElement().getStyle().set("background", color);
    }

    public void setBorder(String border) {
        this.getElement().getStyle().set("border", border);
    }

    public void resetStyle() {
        setBackgroundColor(Global.BASE_BACKGROUND_COLOR);
        setBorder(Global.BASE_BORDER);
    }

    public void selectedStyle() {
        setBackgroundColor(Global.SELECTED_BACKGROUND_COLOR);
        setBorder(Global.SELECTED_BORDER);
    }

    public void secondSelectedStyle() {
        setBackgroundColor(Global.SECOND_SELECTED_BACKGROUND_COLOR);
        setBorder(Global.SELECTED_BORDER);
    }

    public void thirdSelectedStyle() {
        setBackgroundColor(Global.THIRD_SELECTED_BACKGROUND_COLOR);
        setBorder(Global.SELECTED_BORDER);
    }

    public void sortedStyle() {
        setBackgroundColor(Global.SORTED_BACKGROUND_COLOR);
        setBorder(Global.SORTED_BORDER);
    }
}
