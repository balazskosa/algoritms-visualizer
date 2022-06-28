package com.application.views.visualizer;

import com.application.views.MainLayout;
import com.application.visualizer.AlgorithmSettingsPanel;
import com.application.visualizer.Helper;
import com.application.visualizer.SizeSettingsPanel;
import com.application.visualizer.data.Global;
import com.application.visualizer.presentation.VisualizerController;
import com.application.visualizer.view.ControlPanel;
import com.application.visualizer.view.CurrentStepPanel;
import com.application.visualizer.view.stuki.StukiController;
import com.application.visualizer.view.visualizerelements.Array;
import com.vaadin.flow.component.html.Div;
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
@Route(value = "Visualizer", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@AnonymousAllowed
public class VisualizerView extends VerticalLayout {

    public VisualizerView() {

        ArrayList<Integer> list = IntStream.range(0, Global.size.getSecond())
                .map(i -> ThreadLocalRandom.current().nextInt(1, 100))
                .collect(ArrayList::new, List::add, List::addAll);

        var array = new Array(list);
        VerticalLayout visualizedElements = new VerticalLayout(array);
        var currentStepPanel = new CurrentStepPanel();
        var controlPanel = new ControlPanel();

        var controller = new VisualizerController
                (array,
                        currentStepPanel,
                        controlPanel,
                        visualizedElements);
        var algorithmSettingsPanel = new AlgorithmSettingsPanel(controller);
        var sizeSettingsPanel = new SizeSettingsPanel(controller);

        VerticalLayout middle = new VerticalLayout(currentStepPanel, controlPanel, visualizedElements);
        VerticalLayout left = new VerticalLayout(algorithmSettingsPanel, sizeSettingsPanel);
        Div right = new Div();

        var stukiController = new StukiController(algorithmSettingsPanel, right);
        left.setHeightFull();
        middle.setHeightFull();
        right.setHeightFull();



        left.setAlignItems(Alignment.CENTER);
        middle.getStyle().set("min-width", "50vw");
        middle.getStyle().set("max-width", "50vw");

        HorizontalLayout screen = new HorizontalLayout(left, middle, right);
        add(screen);
        add(new Helper(algorithmSettingsPanel).getHelpButton());



    }
}
