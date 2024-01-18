package org.vaadin.example.views.main;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.swing.text.AsyncBoxView;


@Route(value="login", layout = MainLayout.class)
@PageTitle("loginpagepage")
public class LogIn extends VerticalLayout {

    LogIn() {
        add(createLoginForm());
    }


    public Component createLoginForm() {
        LoginForm loginForm = new LoginForm();
                Div parentDiv = new Div();
        parentDiv.getStyle().set("height","100vh").set("width","100%")
                .set("display","grid").set("align-items","center").set("justify-content","center");
        Div childDiv=new Div();
        childDiv.add(loginForm);
        childDiv.addClassName("login-form-child");

        parentDiv.add(childDiv);


        return parentDiv;
    }


}
