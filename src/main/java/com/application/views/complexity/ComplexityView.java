package com.application.views.complexity;

import com.application.complexity.data.*;
import com.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
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

    public ComplexityView() {
        Grid<SortComplexity> grid = new Grid<>(SortComplexity.class, true);
        grid.setMultiSort(true);
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.addThemeVariants(GridVariant.LUMO_COLUMN_BORDERS);


        Button setData = new Button("Calculate");
        setData.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        IntegerField integerField = integerField();

        integerField.addValueChangeListener(e ->
                setData.setVisible(!(integerField.getValue() < integerField.getMin() || integerField.getValue() > integerField.getMax())));

        setData.addClickListener(e -> {
            grid.setItems(generateData(integerField.getValue()));
            Notification notification = Notification.show("Calculation finished", 5000, Notification.Position.BOTTOM_END);
            notification.addThemeVariants(NotificationVariant.LUMO_PRIMARY);
        });

        HorizontalLayout layout = new HorizontalLayout(integerField, setData);
        layout.setDefaultVerticalComponentAlignment(Alignment.BASELINE);

        add(layout, grid);
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
