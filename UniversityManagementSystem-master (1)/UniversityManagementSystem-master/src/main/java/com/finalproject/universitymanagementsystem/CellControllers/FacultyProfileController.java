package com.finalproject.universitymanagementsystem.CellControllers;

import com.finalproject.identification.Faculty;
import com.finalproject.primary.Course;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Collectors;

public class FacultyProfileController {

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
    private ListView<String> coursesListView;  // We'll display course names here

    @FXML
    private ImageView photoView;

    private Faculty faculty;

    /**
     * Populates the profile form fields with the Faculty data.
     */
    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
        if (faculty != null) {
            nameField.setText(faculty.getName());
            emailField.setText(faculty.getEmailAddress());
            degreeField.setText(faculty.getDegree());
            researchField.setText(faculty.getResearchInterest());
            officeField.setText(faculty.getOfficeLocation());

            // Get the offered courses from the faculty. If none, display a placeholder.
            List<Course> offeredCourses = faculty.getOfferedCourses();
            if (offeredCourses == null || offeredCourses.isEmpty()) {
                coursesListView.setItems(FXCollections.observableArrayList("No courses offered"));
            } else {
                List<String> courseNames = offeredCourses.stream()
                        .map(course -> course.getName())
                        .collect(Collectors.toList());
                coursesListView.setItems(FXCollections.observableArrayList(courseNames));
            }

            // Load the profile photo if available; otherwise, load a default image.
//            if (faculty.getProfilePhoto() != null && !faculty.getProfilePhoto().isEmpty()) {
//                photoView.setImage(new Image("file:" + faculty.getProfilePhoto()));
//            } else {
//                photoView.setImage(new Image("file:defaultPhoto.png"));
//            }
        }
    }

    /**
     * Called when the "Change Profile Picture" button is pressed.
     * Opens a file chooser to allow the user to select a new image.
     */
    @FXML
    private void changePhoto() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Profile Photo");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );
        Stage stage = (Stage) nameField.getScene().getWindow();
        java.io.File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            faculty.setProfilePhoto(file.getAbsolutePath());
            photoView.setImage(new Image("file:" + file.getAbsolutePath()));
        }
    }

    /**
     * Called when the "Save Changes" button is pressed.
     * Updates the Faculty object with the data from the form and closes the window.
     */
    @FXML
    private void saveProfile() {
        if (faculty != null) {
            faculty.setName(nameField.getText());
            faculty.setEmailAddress(emailField.getText());
            faculty.setDegree(degreeField.getText());
            faculty.setResearchInterest(researchField.getText());
            faculty.setOfficeLocation(officeField.getText());
            System.out.println("Profile updated for: " + faculty.getName());

            // Optionally, update courses if you allow editing courses here.

            // Close the profile window.
            Stage stage = (Stage) nameField.getScene().getWindow();
            stage.close();
        }
    }
}
