package org.vaadin.example.views.main;

import com.vaadin.flow.component.BlurNotifier;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.BinderValidationStatus;
import com.vaadin.flow.router.BeforeLeaveEvent;
import com.vaadin.flow.router.BeforeLeaveObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import javassist.bytecode.analysis.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.example.dto.Customer;
import org.vaadin.example.dto.Response;
import org.vaadin.example.service.CustomerService;
import org.vaadin.example.utility.Utility;

import java.util.concurrent.atomic.AtomicBoolean;

@Route(value = "registrs", layout = MainLayout.class)
@PageTitle("registration-page")
public class RegisterPage extends VerticalLayout implements BeforeLeaveObserver {


    @Autowired
    CustomerService customerService;
    Binder<Customer> binder = new Binder<>(Customer.class);
    Customer customer = new Customer();



    public RegisterPage() {

        add(getRegistrationForm());

        System.out.println(binder.hasChanges());
    }

    public Component getRegistrationForm() {

        TextField name = new TextField("Name");
        name.setRequiredIndicatorVisible(true);
        name.setErrorMessage("This field is required");

        TextField surName = new TextField("Surname");
        surName.setRequiredIndicatorVisible(true);
        surName.setErrorMessage("This field is required");

        PasswordField password = new PasswordField("Password");
        password.setRequiredIndicatorVisible(true);
        password.setErrorMessage("This field is required");


        TextField contactNumber = new TextField("Contact Number");
        contactNumber.setPattern("^[+]?[(]?[0-9]{3}[)]?[-s.]?[0-9]{3}[-s.]?[0-9]{4,6}$");
        contactNumber.setRequiredIndicatorVisible(true);
        ;
        contactNumber.setErrorMessage("This field is required");


        contactNumber.addBlurListener(this::validateTextField);


        TextField emailAddress = new TextField("Email Address");
        emailAddress.setRequiredIndicatorVisible(true);
        emailAddress.setErrorMessage("This field is required");

        TextField address = new TextField("Address");
        address.setRequiredIndicatorVisible(true);
        address.setErrorMessage("This field is required");

        Button registerButton = new Button("Register", event -> {
            try {
                BinderValidationStatus<Customer> status = binder.validate();
                if (status.hasErrors()) {
                    Notification.show(status.getValidationErrors().toString());

                }
                binder.writeBean(customer);
                Response response = customerService.addCustomer(customer);
                if (response.isSuccess()) {
                    Notification.show("Sign Up done Successfully.");
                } else {

                    response.getErrorMessage().forEach(x -> Notification.show(x));

                }
            } catch (Exception e) {
//                Notification.show("Error is===>" + e.getMessage());
                Notification.show("Error is===>" + e);
            }
        });

        registerButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        binder.bind(name, Customer::getName, Customer::setName);
        binder.bind(surName, Customer::getSurName, Customer::setSurName);
        binder.bind(password, Customer::getPassword, Customer::setPassword);
        binder.bind(contactNumber, Customer::getContactNumber, Customer::setContactNumber);
        binder.bind(emailAddress, Customer::getEmailAddress, Customer::setEmailAddress);
        binder.bind(address, Customer::getAddress, Customer::setAddress);

        binder.readBean(customer);
        VerticalLayout layout = new VerticalLayout();
        layout.setJustifyContentMode(JustifyContentMode.CENTER);
        layout.setSizeFull();
        layout.setAlignItems(Alignment.CENTER);
        Div div = new Div();
        div.add(new H1("Register Customer"));
        div.add(name);
        div.add(surName);
        div.add(password);
        div.add(contactNumber);
        div.add(emailAddress);
        div.add(address);
        div.getStyle().set("display", "flex").set("flex-direction", "column").set("padding", "25px");
        div.addClassName("register-div");
        registerButton.getStyle().set("margin-top", "25px");

        div.add(registerButton);
        layout.add(div);
        return layout;
    }

    private void showConfirmationDialog() {
        ConfirmDialog confirmDialog = new ConfirmDialog();
        confirmDialog.setText("Your form has changes! Are you sure you want to leave?");
        confirmDialog.setCancelable(true);
        confirmDialog.addConfirmListener(__ -> navigateToLoginPage());
        confirmDialog.addCancelListener(__ -> Notification.show("Cancel;"));

        confirmDialog.open();
    }

    private void navigateToLoginPage() {
        UI.getCurrent().navigate("loginpage");
    }


    public void beforeLeave(BeforeLeaveEvent event) {

        Notification.show("beforeLeave" + hasChanges());
        if (hasChanges()) {
            BeforeLeaveEvent.ContinueNavigationAction action =
                    event.postpone();
            ConfirmDialog confirmDialog = new ConfirmDialog();
            confirmDialog.setText("Your form has changes! Are you sure you want to leave?");
            confirmDialog.setCancelable(true);
            confirmDialog.addConfirmListener(__ -> action.proceed());
            confirmDialog.addCancelListener(__ -> action.cancel());
            confirmDialog.open();
        }
    }

    AtomicBoolean value = new AtomicBoolean(false);

    private boolean hasChanges() {
//        Notification.show("value of hashchang in  "+binder.hasChanges());
//        binder.addStatusChangeListener(event -> {
//            boolean hasChanges = event.getBinder().hasChanges();
//            Notification.show("value of hashchange is inside  listener  function  "+hasChanges);
//            value.set(hasChanges);
//        });

        return true;
    }


    // To close the session
//    protected void onAttach(AttachEvent attachEvent) {
//        UI ui = getUI().get();
//        Button button = new Button("Logout", event -> {
//            // Redirect this page immediately
//            ui.getPage().executeJs("window.location.href='logout.html'");
//
//            // Close the session
//            ui.getSession().close();
//        });
//
//        add(button);
//
//        // Notice quickly if other UIs are closed
//        ui.setPollInterval(3000);
//    }
    private void validateTextField(BlurNotifier.BlurEvent<TextField> event) {
        TextField textField = event.getSource();

        // Get the entered value
        String enteredValue = textField.getValue();

        // Set the pattern for the validator
        String pattern = "^[+]?[(]?[0-9]{3}[)]?[-s.]?[0-9]{3}[-s.]?[0-9]{4,6}$";

        // Check if the entered value matches the pattern
        if (!enteredValue.matches(pattern)) {
            // Show an error message
            Notification.show("Invalid format", 3000, Notification.Position.BOTTOM_START);
        }

    }


}


