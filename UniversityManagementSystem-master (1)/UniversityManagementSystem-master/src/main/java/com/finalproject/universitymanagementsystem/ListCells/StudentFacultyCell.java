package com.finalproject.universitymanagementsystem.ListCells;

import com.finalproject.identification.Faculty;
import com.finalproject.universitymanagementsystem.CellControllers.StudentFacultyController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class StudentFacultyCell extends ListCell<Faculty> {

    private VBox cellLayout;
    private StudentFacultyController controller;

    @Override
    protected void updateItem(Faculty faculty, boolean empty) {
        super.updateItem(faculty, empty);

        if (empty || faculty == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (cellLayout == null) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/finalproject/universitymanagementsystem/cells/studentFacultyCell.fxml"));
                    cellLayout = loader.load();
                    controller = loader.getController();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            controller.setFaculty(faculty);
            setGraphic(cellLayout);
            cellLayout.setMaxWidth(Double.MAX_VALUE);
        }
    }
}