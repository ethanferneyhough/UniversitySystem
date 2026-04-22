package com.finalproject.universitymanagementsystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

import com.finalproject.identification.*;

public class LoginController {

    @FXML
    private PasswordField pwBar;

    @FXML
    private TextField idBar;

    @FXML
    private Button loginButton;

    @FXML
    public void IdAction() {
    }

    @FXML
    public void PasswordAction() {
    }

    @FXML
    public void OnLoginButton() throws IOException, InterruptedException {
        int TypeOfUser = 0;

        String TypedUniversityId = idBar.getText();
        String TypedPassword = pwBar.getText();

        TypeOfUser = User.GetUserInfo(TypedUniversityId, TypedPassword, StartApp.masterUserList);

        currentUser.setUserType(TypeOfUser);

        if(TypeOfUser == 0){
            System.out.println("Invalid user credentials entered.");
        }else{
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.close();
            Stage newStage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(StartApp.class.getResource("interface/mainApp.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
            switch(TypeOfUser){
                case 1:
                    newStage.setTitle("University Management System - Logged in as User (Student)");
                    break;
                case 2:
                    newStage.setTitle("University Management System - Logged in as User (Faculty)");
                    break;
                case 3:
                    newStage.setTitle("University Management System - Logged in as Admin");
                    break;
                default:
                    System.out.println("Could not fetch user role.");
            }
            newStage.setScene(scene);
            newStage.setMaximized(true);
            newStage.show();
        }

    }
}