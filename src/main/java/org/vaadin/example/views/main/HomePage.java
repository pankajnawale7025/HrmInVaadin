package org.vaadin.example.views.main;


import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("home")
public class HomePage extends VerticalLayout {
    public HomePage() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSizeFull();
        layout.setAlignItems(Alignment.CENTER);
        layout.setSpacing(false);
        layout.setJustifyContentMode(JustifyContentMode.CENTER);
        layout.add("Welcome To Home Page");
        add(layout);
    }

    public Div loginForm()
    {


        return null;
    }

}
