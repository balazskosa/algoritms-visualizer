package com.application.views.complexity;

import com.application.complexity.data.*;
import com.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.UnorderedList;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;
import java.util.ArrayList;
import java.util.List;

@PageTitle("Complexity")
@Route(value = "complexity", layout = MainLayout.class)
@PermitAll
public class ComplexityView extends VerticalLayout {

    private final Button calculate;
    private final IntegerField size;
    private final Grid<SortComplexity> grid;
    private final Icon helpIcon;
    public ComplexityView() {
        grid = createGrid();
        calculate = new Button("Calculate");
        calculate.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        size = integerField();
        helpIcon = new Icon(VaadinIcon.QUESTION_CIRCLE_O);

        setListener();

        HorizontalLayout layout = new HorizontalLayout(calculate, helpIcon);
        layout.setDefaultVerticalComponentAlignment(Alignment.CENTER);

        HorizontalLayout controlLayout = new HorizontalLayout(size, layout);
        controlLayout.setDefaultVerticalComponentAlignment(Alignment.BASELINE);

        add(controlLayout, grid);
    }

    private void setListener() {
        size.addValueChangeListener(e ->
                calculate.setEnabled(!(size.getValue() < size.getMin() || size.getValue() > size.getMax())));

        calculate.addClickListener(e -> {
            grid.setItems(generateData(size.getValue()));
            Notification notification = Notification.show("Calculation finished", 5000, Notification.Position.BOTTOM_END);
            notification.addThemeVariants(NotificationVariant.LUMO_PRIMARY);
        });

        helpIcon.addClickListener(e -> helpIconDialog());
    }
    private Grid<SortComplexity> createGrid() {
        Grid<SortComplexity> grid = new Grid<>(SortComplexity.class, true);
        grid.setMultiSort(true);
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.addThemeVariants(GridVariant.LUMO_COLUMN_BORDERS);

        return grid;
    }

    private void helpIconDialog() {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Swap counter difference");

        dialog.add("The swap counter is different by");
        UnorderedList ul = new UnorderedList(new ListItem("Tournament Sort"), new ListItem("Insertion Sort"), new ListItem("Mergesort"));
        dialog.add(ul);
        dialog.add("That's means, that not swapping two numbers, but also overriding a number with another number");

        Button closeButton = new Button("OK");
        dialog.getFooter().add(closeButton);
        closeButton.addClickListener(e -> dialog.close());

        dialog.open();
    }

    private List<SortComplexity> generateData(int size) {
        List<SortComplexity> sortComplexities = new ArrayList<>();

        sortComplexities.add(new MaximumSelectionSortComplexity(size));
        sortComplexities.add(new TournamentSortComplexity(size));
        sortComplexities.add(new InsertionSortComplexity(size));
        sortComplexities.add(new BubbleSortComplexity(size));
        sortComplexities.add(new MergesortComplexity(size));
        sortComplexities.add(new QuickSortComplexity(size));
        sortComplexities.add(new HeapsortComplexity(size));

        return sortComplexities;
    }

    private IntegerField integerField() {
        IntegerField integerField = new IntegerField();
        integerField.setValue(1000);
        integerField.setMin(500);
        integerField.setMax(20000);
        integerField.setLabel("Size");
        integerField.setHelperText("Min 500, Max 20 000");
        integerField.setHasControls(true);
        integerField.setStep(500);

        return integerField;
    }


}
