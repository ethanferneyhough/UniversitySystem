package com.finalproject.universitymanagementsystem.CellControllers;

import com.finalproject.primary.Subject;
import com.finalproject.universitymanagementsystem.AppController;
import com.finalproject.universitymanagementsystem.DataLists.SubjectList;
import com.finalproject.universitymanagementsystem.ListController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class AdminSubjectController {
    @FXML
    private Label label;

    private Subject subject;

    @FXML
    private void viewCourses() throws IOException {
        AppController.getInstance().openCourse();
    }

    @FXML
    private void deleteSubject() {
        SubjectList.removeSubject(subject);
        ListController.getInstance().updateSubjects();
    }

    public void setSubject(Subject subject){
        this.subject = subject;
        label.setText(subject.getSubjectName() + " " + subject.getSubjectCode());
    }


}
