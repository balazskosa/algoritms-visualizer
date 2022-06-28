package com.application.views.visualizer;

import com.application.views.MainLayout;
import com.application.visualizer.AlgorithmSettingsPanel;
import com.application.visualizer.Helper;
import com.application.visualizer.SizeSettingsPanel;
import com.application.visualizer.data.Global;
import com.application.visualizer.presentation.StukiController;
import com.application.visualizer.presentation.VisualizerController;
import com.application.visualizer.view.ControlPanel;
import com.application.visualizer.view.CurrentStepPanel;
import com.application.visualizer.view.visualizerelements.Array;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

@PageTitle("Visualizer")
@CssImport("./styles/visualizer-view.css")
@Route(value = "Visualizer", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@AnonymousAllowed
public class VisualizerView extends VerticalLayout {

    public VisualizerView() {
        ArrayList<Integer> list = IntStream.range(0, Global.size.getSecond())
                .map(i -> ThreadLocalRandom.current().nextInt(1, 100))
                .collect(ArrayList::new, List::add, List::addAll);
        var array = new Array(list);
        var visualizedElements = new VerticalLayout(array);

        var currentStepPanel = new CurrentStepPanel();
        var controlPanel = new ControlPanel();
        var controller = new VisualizerController(array, currentStepPanel, controlPanel, visualizedElements);

        var algorithmSettingsPanel = new AlgorithmSettingsPanel(controller);
        var sizeSettingsPanel = new SizeSettingsPanel(controller);

        var middle = new VerticalLayout(currentStepPanel, controlPanel, visualizedElements);
        middle.setHeightFull();
        var right = new VerticalLayout();
        var left = new VerticalLayout(algorithmSettingsPanel, sizeSettingsPanel);
        HorizontalLayout screen = new HorizontalLayout(left, middle, right);
        screen.addClassName("container");

        new StukiController(algorithmSettingsPanel, right);

        add(screen);
        add(new Helper(algorithmSettingsPanel).getHelpButton());

    }
}
