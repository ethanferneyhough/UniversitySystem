package com.finalproject.universitymanagementsystem.CellControllers;

import com.finalproject.primary.Subject;
import com.finalproject.universitymanagementsystem.AppController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class UserSubjectController {
    @FXML
    private Label label;

    @FXML
    private void onButtonPress() throws IOException {
        AppController.getInstance().openCourse();
    }

    public void setSubject(Subject subject) {
        label.setText(subject.getSubjectName() + " " + subject.getSubjectCode());
    }
}
