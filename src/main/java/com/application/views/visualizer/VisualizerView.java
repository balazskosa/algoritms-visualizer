package com.application.views.visualizer;

import com.application.views.MainLayout;
import com.application.visualizer.model.algorithms.QuickSort;
import com.application.visualizer.ui.Array;
import com.application.visualizer.ui.Control;
import com.application.visualizer.ui.DisplayCurrentStep;
import com.application.visualizer.ui.SettingsPanel;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import java.util.ArrayList;
import java.util.List;

@PageTitle("Visualizer")
@Route(value = "Visualizer", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@AnonymousAllowed
public class VisualizerView extends VerticalLayout {
    public VisualizerView() {
        ArrayList<Integer> list = new ArrayList<>(List.of(10, 11, 5, 5, 45, 7, 19, 19, 6, 55, 1, 4));

        DisplayCurrentStep displayCurrentStep = new DisplayCurrentStep();
        var array = new Array(list, displayCurrentStep, new QuickSort(list).getMovements());
        Control control = new Control(array);
        SettingsPanel settingsPanel = new SettingsPanel();
        add(control, array, array.getDisplayCurrentStep());

    }
}
