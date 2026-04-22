package com.finalproject.universitymanagementsystem.ListCells;

import com.finalproject.identification.Student;
import com.finalproject.universitymanagementsystem.CellControllers.AdminStudentController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;

import java.io.FileNotFoundException;
import java.io.IOException;

public class AdminStudentCell extends ListCell<Student> {

    private HBox cellLayout;
    private AdminStudentController controller;

    @Override
    protected void updateItem(Student student, boolean empty) {
        super.updateItem(student, empty);

        if (empty || student == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (cellLayout == null) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/finalproject/universitymanagementsystem/cells/adminStudentCell.fxml"));
                    cellLayout = loader.load();
                    controller = loader.getController();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                controller.setStudent(student);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            setGraphic(cellLayout);
            cellLayout.setMaxWidth(Double.MAX_VALUE);
        }
    }
}
