package com.finalproject.universitymanagementsystem;

import com.finalproject.primary.Event;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import com.finalproject.identification.*;
import java.io.IOException;
import javafx.scene.Parent;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;



public class AppController {
    @FXML
    private Button subjectButton;

    @FXML
    private Button courseButton;

    @FXML
    private Button studentButton;

    @FXML
    private Button facultyButton;

    @FXML
    private Button eventButton;

    @FXML
    private StackPane sideMenu;
    boolean menuOn = false;

    @FXML
    private Button logOutButton;

    @FXML
    private void logOut() throws IOException {
        Stage stage = (Stage) logOutButton.getScene().getWindow();
        stage.close();
        Stage newStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(StartApp.class.getResource("interface/loginScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 450, 300);
        newStage.setTitle("Login");
        newStage.setScene(scene);
        newStage.show();
    }

    @FXML
    private VBox showSubScene;

    private static AppController instance;

    public AppController() {
        instance = this;
    }

    public static AppController getInstance() {
        return instance;
    }

    /**
     * Updated method to load FXML without forcing a VBox cast.
     */
    private void putScene(String fxmlFile) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();

        if (showSubScene != null) {
            showSubScene.getChildren().clear();
            showSubScene.getChildren().add(root);

            if (root instanceof Region) {
                Region regionRoot = (Region) root;
                regionRoot.setMaxWidth(Double.MAX_VALUE);
                regionRoot.setMaxHeight(Double.MAX_VALUE);
            }

            // If you need to iterate over children and set grow properties, check if root is a VBox.
            if (root instanceof VBox) {
                VBox vboxRoot = (VBox) root;
                for (Node child : vboxRoot.getChildren()) {
                    if (child instanceof ListView<?>) {
                        VBox.setVgrow(child, Priority.ALWAYS);
                        if (child instanceof Region) {
                            ((Region) child).setMaxHeight(Double.MAX_VALUE);
                        }
                    }
                }
            }
        }
    }


    @FXML
    public void initialize() throws IOException {
        System.out.println(currentUser.getUserType());
        if (currentUser.getUserType() == 1) {
            putScene("interface/studentDashboard.fxml");
            subjectButton.setText("View Subjects");
            courseButton.setText("Course Page");
            studentButton.setText("My Profile");
            facultyButton.setText("My Faculties");
            eventButton.setText("Events");
        }
        else if (currentUser.getUserType() == 2) {
            putScene("interface/facultyDashboard.fxml");
            subjectButton.setText("View Subjects");
            courseButton.setText("Course Page");
            studentButton.setText("My Students");
            facultyButton.setText("My Profile");
            eventButton.setText("Events");
        }
        else if (currentUser.getUserType() == 3) {
            putScene("interface/adminDashboard.fxml");
            subjectButton.setText("Manage Subjects");
            courseButton.setText("Manage Courses");
            studentButton.setText("Manage Students");
            facultyButton.setText("Manage Faculties");
            eventButton.setText("Manage Events");
        }
    }

    @FXML
    private Button menuButton;

    @FXML
    private void toggleMenu() {
        ObservableList<Node> children = sideMenu.getChildren();
        for (Node child : children) {
            child.setVisible(!child.isVisible());
            child.setDisable(!child.isDisable());
            child.setManaged(!child.isManaged());
        }
        sideMenu.setDisable(!sideMenu.isDisable());
        sideMenu.setVisible(!sideMenu.isVisible());
        sideMenu.setManaged(!sideMenu.isManaged());

        menuOn = !menuOn;
        if(menuOn) {
            menuButton.setText("Close Menu <");
        }
        else {
            menuButton.setText("Open Menu >");
        }
    }

    @FXML
    private Button currentModule;

    public String getModule() {
        return currentModule.getText();
    }

    @FXML
    public void openDashboard() throws IOException {
        if (currentUser.getUserType() == 1)
            putScene("interface/studentDashboard.fxml");
        else if (currentUser.getUserType() == 2)
            putScene("interface/facultyDashboard.fxml");
        else
            putScene("interface/adminDashboard.fxml");
        currentModule.setText("Dashboard");
    }

    @FXML
    public void openSubject() throws IOException {
        if (currentUser.getUserType() == 3) {
            putScene("interface/adminSubjectManagement.fxml");
            currentModule.setText("Subject Management");
            ListController.getInstance().adminSubject();
        }
        else {
            putScene("interface/userSubjectManagement.fxml");
            currentModule.setText("View Subjects");
            ListController.getInstance().userSubject();
        }
    }

    @FXML
    public void openCourse() throws IOException {
        if (currentUser.getUserType() == 3) {
            putScene("interface/adminCourseManagement.fxml");
            currentModule.setText("Course Management");
            ListController.getInstance().adminCourse();
        }
        else {
            putScene("interface/userCourseManagement.fxml");
            currentModule.setText("View Courses");
            ListController.getInstance().userCourse();
        }
    }

    @FXML
    public void openStudent() throws IOException {
        if (currentUser.getUserType() == 1) {
            putScene("interface/studentProfile.fxml");
            currentModule.setText("My Profile");
        }
        else if (currentUser.getUserType() == 2) {
            putScene("interface/facultyStudentManagement.fxml");
            currentModule.setText("View Students");
            ListController.getInstance().facultyStudent();
        }
        else {
            putScene("interface/adminStudentManagement.fxml");
            currentModule.setText("Student Management");
            ListController.getInstance().adminStudent();
        }
    }

    public void openStudentProfile(Student student) throws IOException {
        putScene("interface/studentProfile.fxml");
        currentModule.setText(student.getName() + "'s Profile");
    }

    @FXML
    public void openFaculty() throws IOException {
        if (currentUser.getUserType() == 1) {
            putScene("interface/studentFacultyManagement.fxml");
            currentModule.setText("View Faculties");
            ListController.getInstance().studentFaculty();
        }
        else if (currentUser.getUserType() == 2) {
            putScene("interface/facultyProfile.fxml");
            currentModule.setText("My Profile");
        }
        else {
            putScene("interface/adminFacultyManagement.fxml");
            currentModule.setText("Faculty Management");
            // Removed: ListController.getInstance().adminFaculty();
        }
    }


    public void openFacultyProfile(Faculty faculty) throws IOException {
        putScene("interface/facultyProfile.fxml");
        currentModule.setText(faculty.getName() + "'s Profile");
    }

    @FXML
    public void openEvent() throws IOException {
        if (currentUser.getUserType() == 3) {
            putScene("interface/adminEventManagement.fxml");
            ListController.getInstance().adminEvent();
        }
        else {
            putScene("interface/userEventManagement.fxml");
            ListController.getInstance().userEvent();
        }
        currentModule.setText("Events");
    }

    public void openEventDetails(Event event) throws IOException {
        putScene("interface/eventPage.fxml");
        currentModule.setText(event.getName() + " Details");
    }
}
