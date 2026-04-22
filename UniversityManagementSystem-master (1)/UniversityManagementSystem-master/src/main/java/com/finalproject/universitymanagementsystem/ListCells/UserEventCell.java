package com.finalproject.universitymanagementsystem.ListCells;

import com.finalproject.primary.Event;
import com.finalproject.universitymanagementsystem.CellControllers.UserEventController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class UserEventCell extends ListCell<Event> {

    private VBox cellLayout;
    private UserEventController controller;

    @Override
    protected void updateItem(Event event, boolean empty) {
        super.updateItem(event, empty);

        if (empty || event == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (cellLayout == null) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/finalproject/universitymanagementsystem/cells/userEventCell.fxml"));
                    cellLayout = loader.load();
                    controller = loader.getController();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            controller.setEvent(event);
            setGraphic(cellLayout);
            cellLayout.setMaxWidth(Double.MAX_VALUE);
        }
    }
}