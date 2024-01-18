package org.vaadin.example.views.main;


import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.treegrid.TreeGrid;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoIcon;
import org.apache.catalina.Manager;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Route("")
public class CreatingUi extends VerticalLayout {

    Button button = new Button("Button", (ComponentEventListener<ClickEvent<Button>>) x -> {
        ConfirmDialog confirmDialog = new ConfirmDialog();
        confirmDialog.setText("Your form has changes! Are you sure you want to leave?");

        // Create a ScheduledExecutorService
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

        // Schedule a task with a delay of 2 seconds
        executorService.schedule(() -> {
            // Open the ConfirmDialog after the delay
            confirmDialog.open();
        }, 2, TimeUnit.SECONDS);

        // Shutdown the executor service when the UI is detached or closed
        UI.getCurrent().addDetachListener(detachEvent -> executorService.shutdown());

    });

    public CreatingUi() {
        createHeader();
    }

    public void createHeader() {
        add(button);
    }
}


