package com.application.visualizer.data.helper;


import com.application.visualizer.view.AlgorithmSettingsPanel;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;

public class Helper {
    private final Button helpButton;
    private final AlgorithmSettingsPanel algorithmPanel;
    private final ConfirmDialog dialog;

    public Helper(AlgorithmSettingsPanel algorithmPanel) {
        this.helpButton = new Button(new Icon(VaadinIcon.QUESTION_CIRCLE_O));
        this.helpButton.getStyle().set("position", "absolute").set("right", "30px").set("bottom", "10px");
        this.algorithmPanel = algorithmPanel;
        this.helpButton.addClickListener((buttonClickEvent -> getAlgorithmHelper()));
        this.dialog = new ConfirmDialog();

        this.dialog.setHeight("60vh");
        this.dialog.setWidth("50vw");
        this.dialog.setConfirmText("OK");

    }
    public void getAlgorithmHelper() {
        this.dialog.removeAll();
        Div sortingHelperDiv = new Div();
        switch (algorithmPanel.getSelectedValue()) {
            case "Maximum Selection Sort" -> sortingHelperDiv.add(new MaximumSelectionSortHelper());
            case "Insertion sort" -> sortingHelperDiv.add(new InsertionShortHelper());
            case "Bubble sort" -> sortingHelperDiv.add(new BubbleSortHelper());
            case "Quicksort" -> sortingHelperDiv.add(new QuickSortHelper());
            default -> throw new IllegalArgumentException("Wrong value");
        }

        dialog.setHeader(algorithmPanel.getSelectedValue());
        dialog.add(sortingHelperDiv);
        dialog.open();
    }

    public Button getHelpButton() {
        return helpButton;
    }

}
