package com.finalproject.universitymanagementsystem.CellControllers;

import com.finalproject.identification.Student;
import com.finalproject.universitymanagementsystem.AppController;
import com.finalproject.universitymanagementsystem.DataLists.StudentList;
import com.finalproject.universitymanagementsystem.ListController;
import com.finalproject.universitymanagementsystem.StartApp;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AdminStudentController {
    @FXML
    private Label name;

    @FXML
    private Label id;

    @FXML
    private ImageView image;

    private Student student;

    @FXML
    private void deleteStudent() {
        StudentList.removeStudent(student);
        ListController.getInstance().updateStudents();
    }

    @FXML
    private void openProfile() throws IOException {
        AppController.getInstance().openStudentProfile(student);
    }

    public void setStudent(Student student) throws FileNotFoundException {
        this.student = student;
        name.setText(student.getName());
        id.setText(student.getUniversityId());
        if (student.getProfilePhoto() != null) {
            image.setImage(student.getProfilePhoto());
        }else{
            image.setImage(new Image(new FileInputStream(StartApp.masterDefaultImage)));
        }
    }
}
