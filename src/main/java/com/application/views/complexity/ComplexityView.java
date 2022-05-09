package com.application.views.complexity;

import com.application.complexity.ComplexityPanel;
import com.application.views.MainLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@PageTitle("Complexity")
@Route(value = "complexity", layout = MainLayout.class)
@AnonymousAllowed
public class ComplexityView extends VerticalLayout {
    public ComplexityView() {

        add(new ComplexityPanel());
    }
}
