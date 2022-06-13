package com.application.visualizer;

import com.application.visualizer.presentation.VisualizerController;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextArea;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class CustomListDialog extends Dialog {
    private List<Integer> numbers;
    private final Button cancelButton;
    private final Button addSizeButton;
    private final Button addListButton;
    private final IntegerField inputSize;
    private final TextArea numbersTextArea;

    public CustomListDialog(VisualizerController controller) {
        this.setHeaderTitle("Custom List");
        inputSize = integerField();
        numbersTextArea = numbersTextField();

        cancelButton = new Button(new Icon(VaadinIcon.CLOSE_CIRCLE));
        addSizeButton = new Button(new Icon(VaadinIcon.CHECK_CIRCLE));
        addListButton = new Button(new Icon(VaadinIcon.CHECK_CIRCLE));

        HorizontalLayout sizeLayout = new HorizontalLayout(inputSize, addSizeButton);
        HorizontalLayout numbersLayout = new HorizontalLayout(numbersTextArea, addListButton);
        sizeLayout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.BASELINE);
        numbersLayout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.BASELINE);

        VerticalLayout dialogLayout = new VerticalLayout();
        this.add(dialogLayout);
        dialogLayout.add(sizeLayout, numbersLayout);
        this.getFooter().add(cancelButton);
    }

    private IntegerField integerField() {
        IntegerField integerField = new IntegerField();
        integerField.setLabel("Size");
        integerField.setHelperText("Min 4, Max 16");
        integerField.setMin(4);
        integerField.setMax(16);
        integerField.setValue(8);
        integerField.setHasControls(true);
        return integerField;
    }

    private TextArea numbersTextField() {
        TextArea textArea = new TextArea();
        textArea.setClearButtonVisible(true);
        textArea.setValue("2 8 12 4 20 7 43 9 5 23 1 6");
        textArea.setHelperText("Example: 2 8 12 4 20 7");
        textArea.addValueChangeListener(e -> textArea.setInvalid(checkNumbers(textArea)));
        return textArea;
    }

    private boolean checkNumbers(TextArea textArea) {
        List<Integer> numbers;
        try {
            String[] values = textArea.getValue().split(" ");
            numbers = Arrays.stream(values).map(Integer::valueOf).toList();


            if (new HashSet<>(numbers).size() != numbers.size())
                throw new IllegalArgumentException("Not unique values");
            if (numbers.size() < 4 || numbers.size() > 16)
                throw new Exception("Min 4 Max 16 values");

            this.numbers = numbers;
        } catch (NumberFormatException e) {
            textArea.setErrorMessage("Wrong format, only numbers and whitespaces");
            return true;
        } catch (Exception e) {
            textArea.setErrorMessage(e.getMessage());
            return true;
        }
        return false;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public Button getCancelButton() {
        return cancelButton;
    }
    public Button getAddSizeButton() {
        return addSizeButton;
    }

    public Button getAddListButton() {
        return addListButton;
    }

    public int getSize() {
        return inputSize.getValue();
    }

    public boolean isValid() {
        return !numbersTextArea.isInvalid();
    }
}