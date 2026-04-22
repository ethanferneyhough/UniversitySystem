package com.finalproject.universitymanagementsystem.CellControllers;

import com.finalproject.identification.Faculty;
import com.finalproject.universitymanagementsystem.DataLists.FacultyList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddFacultyFormController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField degreeField;
    @FXML
    private TextField researchField;
    @FXML
    private TextField officeField;

    @FXML
    private void onSave() {
        // Create new faculty from form fields
        String name = nameField.getText();
        String email = emailField.getText();
        String degree = degreeField.getText();
        String research = researchField.getText();
        String office = officeField.getText();

        Faculty newFaculty = new Faculty(name, degree, research, email, office);
        FacultyList.addFaculty(newFaculty);

        // Close the dialog
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }
}
