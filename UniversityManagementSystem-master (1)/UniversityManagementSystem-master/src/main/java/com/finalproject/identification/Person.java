package com.finalproject.identification;

import com.finalproject.universitymanagementsystem.StartApp;
import javafx.scene.image.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public abstract class Person extends User {
    private String name;
    private String emailAddress;
    private String profilePhoto;

    public Person(){
        this.name = "Unset";
        this.emailAddress = "Unset";
        this.profilePhoto = StartApp.masterDefaultImage;
    }
    public Person(String name, String emailAddress, String imagePath) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.profilePhoto = imagePath;
    }
    public Person(String name, String emailAddress, String id, String password, String imagePath, int userType){ //constructor including user id and password
        this.name = name;
        this.emailAddress = emailAddress;
        this.setUniversityId(id);
        this.setPassword(password);
        this.setUserType(userType);
        this.profilePhoto = imagePath;
    }
    public void setName(String name){
        //sets student name and updates profile photo filepath to match
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setEmailAddress(String emailAddress){
        this.emailAddress = emailAddress;
    }
    public String getEmailAddress(){
        return this.emailAddress;
    }
    public Image getProfilePhoto() throws FileNotFoundException {
        Image profilephoto = new Image(new FileInputStream(this.profilePhoto));
        return profilephoto;
    }
    public void setProfilePhoto(String imagePath){
        this.profilePhoto = imagePath;
    }
    public abstract String toString();
}
