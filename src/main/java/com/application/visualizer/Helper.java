package com.application.visualizer;


import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;

import java.io.*;
import java.util.stream.Collectors;
@CssImport("./styles/helper.css")
public class Helper {
    private final Button helpButton;
    private final AlgorithmSettingsPanel algorithmPanel;
    private final ConfirmDialog dialog;

    public Helper(AlgorithmSettingsPanel algorithmPanel) {
        this.algorithmPanel = algorithmPanel;
        helpButton = new Button(new Icon(VaadinIcon.QUESTION_CIRCLE_O));
        helpButton.addClassName("helper-button");

        helpButton.addClickListener((buttonClickEvent -> getAlgorithmHelper()));
        dialog = new ConfirmDialog();
        dialog.setSizeFull();
        dialog.setConfirmText("OK");

    }

    public void getAlgorithmHelper() {
        this.dialog.removeAll();

        String algoName = algorithmPanel.getValue();
        String fileName = algoName.substring(0, 1).toLowerCase()
                .concat(algoName.substring(1).replaceAll("\\s+", ""));

        dialog.setHeader(algoName);
        dialog.add(helperHtml(fileName));
        dialog.open();
    }

    public Button getHelpButton() {
        return helpButton;
    }

    private Html helperHtml(String fileName) {
        File file = new File("src/main/java/com/application/visualizer/data/helper/" + fileName + ".txt");
        FileInputStream fs;
        try {
            fs = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            Notification.show("Not found helper to this algorithm");
            throw new RuntimeException(e);
        }
        String result = new BufferedReader(new InputStreamReader(fs))
                .lines().collect(Collectors.joining("\n"));

        return new Html(result);
    }
}
