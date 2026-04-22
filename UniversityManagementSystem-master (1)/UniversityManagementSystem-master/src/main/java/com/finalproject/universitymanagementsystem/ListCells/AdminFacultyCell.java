package com.finalproject.universitymanagementsystem.ListCells;

import com.finalproject.identification.Faculty;
import com.finalproject.universitymanagementsystem.CellControllers.AdminFacultyCellController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;

import java.io.FileNotFoundException;
import java.io.IOException;

public class AdminFacultyCell extends ListCell<Faculty> {
    private HBox cellLayout;
    private AdminFacultyCellController controller;

    @Override
    protected void updateItem(Faculty faculty, boolean empty) {
        super.updateItem(faculty, empty);

        if (empty || faculty == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (cellLayout == null) {
                try {
                    FXMLLoader loader = new FXMLLoader(
                            getClass().getResource("/com/finalproject/universitymanagementsystem/cells/adminFacultyCell.fxml")
                    );
                    cellLayout = loader.load();
                    controller = loader.getController();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // Pass the Faculty object to the FXML controller
            try {
                controller.setFaculty(faculty);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            setGraphic(cellLayout);
            cellLayout.setMaxWidth(Double.MAX_VALUE);
        }
    }
}
