package com.finalproject.universitymanagementsystem.CellControllers;

import com.finalproject.identification.Faculty;
import com.finalproject.primary.Course;
import com.finalproject.universitymanagementsystem.DataLists.FacultyList;
import com.finalproject.universitymanagementsystem.StartApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class AdminFacultyCellController {

    @FXML
    private Label nameLabel;

    @FXML
    private Label coursesLabel;

    @FXML
    private ImageView imageView;

    private Faculty faculty;

    /**
     * Called to pass the Faculty object into the cell controller and update the UI.
     */
    public void setFaculty(Faculty faculty) throws FileNotFoundException {
        this.faculty = faculty;
        if (faculty != null) {
            nameLabel.setText(faculty.getName());
            // Update coursesLabel as needed. For now, a placeholder:
            ArrayList<String> courseNames = new ArrayList<String>();
            for(Course course : faculty.getOfferedCourses()){
                courseNames.add(course.getName().concat(":"+course.getSectionNumber()));
            }
            coursesLabel.setText(courseNames.toString());
            // Optionally, load an image into imageView if available:
             if (faculty.getProfilePhoto() != null) {
                 imageView.setImage(faculty.getProfilePhoto());
             }else{
                 imageView.setImage(new Image(new FileInputStream(StartApp.masterDefaultImage)));
             }
        }
    }

    /**
     * Called when the "View/Edit Profile" button is pressed.
     * Loads the facultyProfile.fxml, passes the faculty object to its controller,
     * and opens a new window.
     */
    @FXML
    private void openProfile() {
        if (faculty == null) return;
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/finalproject/universitymanagementsystem/interface/facultyProfile.fxml")
            );
            Parent root = loader.load();
            FacultyProfileController profileController = loader.getController();
            profileController.setFaculty(faculty);

            Stage stage = new Stage();
            stage.setTitle("Faculty Profile: " + faculty.getName());
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Called when the "Delete Faculty" button is pressed.
     * Removes the faculty from the master list.
     */
    @FXML
    private void deleteFaculty() {
        if (faculty == null) return;
        FacultyList.removeFaculty(faculty);
        System.out.println("Deleted: " + faculty.getName());
        // The ListView should refresh on the next update.
    }
}
