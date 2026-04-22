package com.finalproject.universitymanagementsystem.CellControllers;

import com.finalproject.primary.Event;
import com.finalproject.universitymanagementsystem.AppController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class UserEventController {
    @FXML
    private Label name;

    @FXML
    private Label date;

    @FXML
    private Label capacity;

    private Event event;

    @FXML
    private void openDetails() throws IOException {
        AppController.getInstance().openEventDetails(event);
    }

    public void setEvent(Event event) {
        this.event = event;
        name.setText(event.getName());
        date.setText(event.getDateTime().toString());
        capacity.setText(event.getCapacity() + "");
    }
}
