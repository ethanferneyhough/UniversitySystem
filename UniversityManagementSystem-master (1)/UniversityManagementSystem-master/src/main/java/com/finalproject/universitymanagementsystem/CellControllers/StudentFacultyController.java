package com.finalproject.universitymanagementsystem.CellControllers;

import com.finalproject.identification.Faculty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StudentFacultyController {
    @FXML
    private Label name;

    @FXML
    private Label email;

    @FXML
    private Label office;

    @FXML
    private Label interests;

    public void setFaculty(Faculty faculty) {
        name.setText(faculty.getName());
        email.setText(faculty.getEmailAddress());
        office.setText(faculty.getOfficeLocation());
        interests.setText(faculty.getResearchInterest());
    }
}
