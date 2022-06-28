package com.application.visualizer;

import com.application.login.data.UserSession;
import com.application.visualizer.data.Global;
import com.application.visualizer.data.algorithms.Sort;
import com.application.visualizer.presentation.VisualizerController;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.internal.Pair;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class SizeSettingsPanel extends SettingsPanel {
    public SizeSettingsPanel(VisualizerController controller) {

        super("Size",
                Global.SIZES.stream().map(Pair::getFirst).collect(Collectors.toList()),
                Global.size.getFirst(), controller);

        this.group.setReadOnly(!UserSession.isLoggedIn());
        group.addValueChangeListener((e) -> setSize());

        this.group.add(new Button(new Icon(VaadinIcon.REFRESH),
                (click) -> setSize()));
    }

    private List<Integer> randomList(int n) {
        Set<Integer> set = new LinkedHashSet<>();
        Random rand = new Random();
        while (set.size() != n) {
            int randomValue = rand.nextInt(100);
            set.add(randomValue);
        }
        return set.stream().toList();
    }

    private void setCustomList() {
        CustomListDialog dialog = new CustomListDialog(controller);
        dialog.open();

        dialog.getCancelButton().addClickListener((click) -> {
            this.group.setValue(Global.size.getFirst());
            dialog.close();
        });

       dialog.getAddSizeButton().addClickListener((click) -> {
           if(!dialog.isSizeValid()) return;

           controller.getArray().setItems(randomList(dialog.getSize()));
           setSort();
           dialog.close();
       });

       dialog.getAddListButton().addClickListener((click) -> {
           if (!dialog.isNumbersValid()) return;

           controller.getArray().setItems(dialog.getNumbers());
           setSort();
           dialog.close();
       });
    }

    private void setSize() {
        Global.SIZES.stream()
                .filter(size -> size.getFirst().equals(this.group.getValue()) && size.getSecond() != null)
                .findFirst()
                .ifPresentOrElse((size) -> {
                    controller.getArray().setItems(randomList(size.getSecond()));
                    setSort();
                }, this::setCustomList);

    }

    private void setSort() {
        try {
            controller.setSort(
                    (Sort) Class.forName(controller.getSort().getClass().getName())
                            .getConstructor(List.class)
                            .newInstance(controller.getArray().getList()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
