package org.vaadin.example;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.server.VaadinService;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.vaadin.example.dto.Response;
import org.vaadin.example.dto.User;
import org.vaadin.example.service.CustomerService;

/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and use @Route
 * annotation to announce it in a URL as a Spring managed bean.
 * <p>
 * A new instance of this class is created for every new user and every browser
 * tab/window.
 * <p>
 * The main view contains a text field for getting the user name and a button
 * that shows a greeting message in a notification.
 */
@Route("mainview")
public class MainView extends VerticalLayout {
    Binder<User> binder = new Binder<>(User.class);
    private TextField username = new TextField("Username");
    private PasswordField password = new PasswordField("Password");

    @Autowired
    CustomerService customerService;
//    @Autowired
//     UserServiceFeignClient userServiceFeignClient;


    public MainView() {

//        if(userServiceFeignClient!=null)
//        {
//
//        System.out.println(userServiceFeignClient.getUser());
//        }


        User user = new User();
        binder.setBean(user);

        // Bind the text fields to the properties of the User class
        binder.bind(username, "userName");
        binder.bind(password, "password");


        setSizeFull();
        setSpacing(false);
        System.out.println(isPadding());
        System.out.println(isSpacing());
        HorizontalLayout layout = new HorizontalLayout();
        setSizeFull();
        setSpacing(false);
        System.out.println(isPadding());
        System.out.println(isSpacing());


//        LoginForm loginForm = new LoginForm();
//        loginForm.getElement().getStyle().set("display", "flex")
//                .set("align-items", "center")
//                .set("justify-content", "center").set("padding","34px");
//
//
        LoginOverlay loginOverlay = new LoginOverlay();


        Div div = new Div();
        Div imagediv = new Div();
        imagediv.getStyle().set("width", "300px").set("height", "200px").set("overflow", "hidden");

        Component image = getImage();
        image.getStyle().set("max-width", "100%").set("height", "auto").set("display", "block").set("margin", "0 auto");
        imagediv.add(image);
        layout.setSpacing(false);

        layout.setWidthFull();
        layout.setHeightFull();

        // Component customloginForm = getLoginForm();
        //div.add(customloginForm);

        LoginForm loginForm = new LoginForm();
        loginForm.addLoginListener(event -> {
            String username = event.getUsername();
            String pass = event.getPassword();
            // Perform authentication logic here
            Object object = customerService.validateUSer("http://localhost:8081/customer/validateCustomer?emailAddress=" + username + "&contactNumber=" + pass);
            Response response = (Response) object;
            System.out.println("responsis ======>" + response);
            System.out.println("response.isSuccess()===>"+response.isSuccess());
            if (response.isSuccess()) {
                Notification.show("Login successful!   \n userName is ==>" + username + "\n Pasword is ==>" + pass + "Objet is ===>\n" + object);
                getUI().ifPresent(ui -> ui.navigate("home"));
            } else {
                // Show an error message
                Notification.show("Authentication failed. Please check your credentials.");
            }
        });

        div.add(loginForm);
        div.getElement().getStyle().setAlignItems(Style.AlignItems.CENTER).set("display", "flex").set("justify-content", "center").set("padding", "10px");

        div.getStyle().set("background-color", "#e3e6eb");
        layout.setFlexGrow(1, imagediv);
        layout.setFlexGrow(2, div);
        layout.add(image, div);
        layout.setAlignItems(Alignment.CENTER);


        add(layout);
    }

    public Component getImage() {

        String resourceUrl = VaadinService.getCurrent().resolveResource("https://images.pexels.com/photos/1595385/pexels-photo-1595385.jpeg?auto=compress&cs=tinysrgb&w=600");
        Image image = new Image(resourceUrl, "Alt Text");
//         Image image=new Image("H:/Vadin/Project Created Online/HRM/frontend/images/employee.png","Image");
        return image;
    }


    public Component getLoginForm() {


        Component logIn = new Text("Log in");
        Component usernameText = new Text("Username");
        Component passwordText = new Text("Password");
        Component textField = new TextField();
        Component passwordField = new PasswordField();

        Button button = new Button("log in");
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        Text forgotPasswordText = new Text("Forgot Password");
        VerticalLayout verticalLayout = new VerticalLayout(usernameText, textField, passwordText, passwordField, button, forgotPasswordText);

        verticalLayout.setSpacing(false);
        verticalLayout.setPadding(false);
        verticalLayout.setAlignItems(Alignment.CENTER);
        Div div = new Div();
        div.add(verticalLayout);
        div.getElement().getStyle().set("background-color", "white").set("padding", "21px").set("margin", "107px").set("border-radius", "15px");
        return div;
    }


}
