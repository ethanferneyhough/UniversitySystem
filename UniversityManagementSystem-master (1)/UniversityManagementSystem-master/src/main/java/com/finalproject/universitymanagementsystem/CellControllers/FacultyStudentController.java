package com.finalproject.universitymanagementsystem.CellControllers;

import com.finalproject.identification.Student;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class FacultyStudentController {
    @FXML
    private Label name;

    @FXML
    private Label id;

    @FXML
    private Label courses;

    public void setStudent(Student student) {
        name.setText(student.getName());
        id.setText(student.getUniversityId());
    }
}
