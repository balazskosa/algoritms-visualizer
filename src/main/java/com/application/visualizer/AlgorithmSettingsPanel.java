package com.application.visualizer;

import com.application.visualizer.data.Global;
import com.application.visualizer.data.algorithms.Sort;
import com.application.visualizer.presentation.VisualizerController;
import com.vaadin.flow.component.notification.Notification;

import java.util.List;

public class AlgorithmSettingsPanel extends SettingsPanel {
    public AlgorithmSettingsPanel(VisualizerController controller) {
        super("Algorithm", Global.ALGORITHMS, Global.algorithm, controller);

        this.group.addValueChangeListener((e) -> setAlgorithm());
    }


    private void setAlgorithm() {
        String algo = this.group.getValue();
        Sort sort;
        try {
            sort = (Sort) Class.forName("com.application.visualizer.data.algorithms." + algo.replaceAll("\\s+", ""))
                    .getConstructor(List.class).newInstance(controller.getArray().getList());
        } catch (Exception e) {
            Notification.show("Something wrong with the sorting algorithm's initializing");
            return;
        }
        controller.setSort(sort);
    }
}
