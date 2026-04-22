package com.finalproject.universitymanagementsystem.ListCells;

import com.finalproject.primary.Subject;
import com.finalproject.universitymanagementsystem.CellControllers.AdminSubjectController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class AdminSubjectCell extends ListCell<Subject> {

    private VBox cellLayout;
    private AdminSubjectController controller;

    @Override
    protected void updateItem(Subject subject, boolean empty) {
        super.updateItem(subject, empty);

        if (empty || subject == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (cellLayout == null) {

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/finalproject/universitymanagementsystem/cells/adminSubjectCell.fxml"));
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