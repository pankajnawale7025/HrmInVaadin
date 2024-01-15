package org.vaadin.example.views.main;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;
import org.vaadin.example.model.User;

@Route("")
public class DataBinding extends VerticalLayout {



    Binder<User> binder = new Binder<>(User.class);

    public DataBinding() throws ValidationException {
        User user = new User();

        TextField userName = new TextField("userName");
        TextField password = new TextField("password");

        user.setUserName("userName");
        user.setPassword("password");
        Button saveButton = new Button("Save",  event -> {
            try {
                binder.writeBean(user);
                System.out.println(binder);
                // A real application would also save
                // the updated person
                // using the application's backend
                Notification.show("user  is===>"+user);
            } catch (ValidationException e) {
                Notification.show("Error is===>"+e.getMessage());
            }
        });

        binder.readBean(user);



        binder.writeBean(user);

        Div div = new Div();
//        binder.bind(userName, User::getUserName, User::setUserName);
//        binder.bind(password, User::getPassword, User::setPassword);




        System.out.println(binder);




        saveButton.getStyle().set("width", "270px");
        div.add(userName, password, saveButton);




        userName.addClassName("input");
        password.addClassName("input");
        Div outerDiv = new Div();
        div.addClassName("inner-div");
        outerDiv.add(div);
        outerDiv.addClassName("outer-div");
        add(outerDiv);
    }

}
