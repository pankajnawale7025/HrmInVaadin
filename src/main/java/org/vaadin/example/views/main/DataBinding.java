package org.vaadin.example.views.main;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.BinderValidationStatus;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;
import org.vaadin.example.dto.User;

@Route("dataBinding")
public class DataBinding extends VerticalLayout {


    Binder<User> binder = new Binder<>(User.class);

    TextField password = new TextField("password");
    TextField userName = new TextField("userName");

    public DataBinding() throws ValidationException {

        User user = new User("Pankaj", "Nawale");
        //binder.bindInstanceFields(this);


        binder.forField(userName).withValidator(name -> name.length() > 3, "name not less than 3 word").asRequired()
                .bind(User::getUserName, User::setUserName);


        binder.forField(password).withValidator(name -> name.length() > 3, "Password is required ").asRequired()
                .bind(User::getPassword, User::setPassword);

        //  binder.setBean(user);
        binder.readBean(user);

        Button saveButton = new Button("Save", event -> {
            try {
                BinderValidationStatus<User> status = binder.validate();
                if (status.hasErrors()) {
                    Notification.show(status.getValidationErrors().toString());
                }
                binder.writeBean(user);
                System.out.println(binder);

                Notification.show("user  is===>" + user);
            } catch (Exception e) {
                Notification.show("Error is===>" + e.getMessage());
            }
        });


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
