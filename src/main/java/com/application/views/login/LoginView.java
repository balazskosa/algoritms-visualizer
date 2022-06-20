package com.application.views.login;

import com.application.UserSession;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

/**
 * Adds a link that the user has to click to login.
 * <p>
 * This view is marked with {@code @AnonymousAllowed} to allow all users access
 * to the login page.
 */
@Route("login")
@PageTitle("Login")
@AnonymousAllowed
@CssImport("./styles/login.css")
public class LoginView extends VerticalLayout {

    /**
     * URL that Spring uses to connect to Google services
     */
    private static final String OAUTH_URL = "/oauth2/authorization/google";
    private static final String MAIN_URL = "/";
    public LoginView(@Autowired Environment env) {
        if (UserSession.isLoggedIn()) {
            UI.getCurrent().getPage().setLocation(MAIN_URL);
            return;
        }

        Button loginButton = new Button("Login with Google", e -> UI.getCurrent().getPage().setLocation(OAUTH_URL));
        Button mainButton = new Button("Return", e -> UI.getCurrent().getPage().setLocation(MAIN_URL));

        Div div = new Div();
        div.addClassName("centered");
        div.add(loginButton, mainButton);
        add(div);
    }
}

