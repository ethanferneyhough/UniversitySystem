package com.finalproject.universitymanagementsystem.ListCells;

import com.finalproject.primary.Subject;
import com.finalproject.universitymanagementsystem.CellControllers.UserSubjectController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import java.io.IOException;

public class UserSubjectCell extends ListCell<Subject> {

    private HBox cellLayout;
    private UserSubjectController controller;

    @Override
    protected void updateItem(Subject subject, boolean empty) {
        super.updateItem(subject, empty);

        if (empty || subject == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (cellLayout == null) {

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/finalproject/universitymanagementsystem/cells/userSubjectCell.fxml"));
                    cellLayout = loader.load();
                    controller = loader.getController();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            controller.setSubject(subject);
            setGraphic(cellLayout);
            cellLayout.setMaxWidth(Double.MAX_VALUE);
        }
    }
}