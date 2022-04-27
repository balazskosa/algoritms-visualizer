package com.application.visualizer;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;

@CssImport("./styles/number.css")
public class Number extends Div {

    private int value;
    private final String baseColor;
    private Boolean isAnimated = false;

    public Number(Integer value, String baseColor) {
        setValue(value);
        this.baseColor = baseColor;
        setBackgroundColor(this.baseColor);
        this.addClassName("number");
    }

    public int getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
        this.setText(String.valueOf(value));
    }

    public void setBackgroundColor(String color) {
        this.getElement().getStyle().set("background", color);
    }

    public void resetBackgroundColor() {
        this.getElement().getStyle().set("background", this.baseColor);
    }

    public void animation() {
        if(this.isAnimated) {
            this.getElement().getStyle().set("animation", "switchFirst 3s");
            this.isAnimated = false;
            return;
        }
        this.getElement().getStyle().set("animation", "switchSecond 3s");
        this.isAnimated = true;
    }
}
