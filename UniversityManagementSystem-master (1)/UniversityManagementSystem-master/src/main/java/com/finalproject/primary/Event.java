package com.finalproject.primary;

import com.finalproject.universitymanagementsystem.StartApp;

import javafx.scene.image.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Event {
    private String name;
    private String code;
    public static int globalCode = 1; //unique id for Event class
    private String description;
    private String headerImage;
    private String location;
    private LocalDateTime dateTime;
    private int capacity;
    private double cost;
    private ArrayList<String> registeredStudents = new ArrayList<String>();

    public Event(){
        this.name = "Unset";
        this.code = "EV"+globalCode;
        globalCode++; //increment unique Event class id
        this.headerImage = StartApp.masterDefaultImage;
        this.description = "Unset";
        this.location = "Unset";
        this.dateTime = LocalDateTime.now();
        this.capacity = 0;
        this.cost = 0;
    }
    public Event(String name, String description, String location, int capacity, double cost, String headerImage){ //management constructor
        this.name = name;
        this.code = "E"+globalCode;
        globalCode++; //increment unique Event class id
        this.headerImage = headerImage;
        this.description = description;
        this.location = location;
        this.dateTime = LocalDateTime.now();
        this.capacity = capacity;
        this.cost = cost;
    }
    //below is a constructor for Excel import
    public Event(String code, String name, String description, String location, LocalDateTime dateTime, double capacity, double cost, String headerImage, ArrayList<String> students){
        this.name = name;
        this.code = code;
        this.headerImage = headerImage;
        this.description = description;
        this.location = location;
        this.dateTime = dateTime;
        this.capacity = (int)capacity;
        this.cost = cost;
        this.registeredStudents = students;
    }
    public LocalDateTime getDateTime() {
        return dateTime;
    }
    public void setDateTime(int day, int month, int year, int hour, int minute){
        this.dateTime = LocalDateTime.of(year,month,day,hour,minute);
    }
    public void setDateTime(LocalDateTime dateTime){
        this.dateTime = dateTime;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getLocation(){
        return this.location;
    }
    public void setLocation(String location){
        this.location = location;
    }
    public int getCapacity(){
        return this.capacity;
    }
    public void setCapacity(int capacity){
        this.capacity = capacity;
    }
    public double getCost(){
        return this.cost;
    }
    public void setName(double cost){
        this.cost = cost;
    }
    public String getCode(){
        return this.code;
    }
    public String toString(){
        return (this.code + " " + this.name + " "  + this.description + " "  + this.location + " " + this.dateTime.toString() + " " + this.capacity + " "  + this.cost + " " + this.registeredStudents);
    }
    public void registerStudent(String student){
        this.registeredStudents.add(student);
    }
    public void removeStudent(String name){
        for(String student : this.registeredStudents){
            if(student.toLowerCase().contains(name.toLowerCase())){}
            registeredStudents.remove(student);
        }
    }
    public ArrayList<String> getRegisteredStudents(){
        return this.registeredStudents;
    }
    public Image getHeaderImage() throws FileNotFoundException {
        Image headerImg = new Image(new FileInputStream(this.headerImage));
        return headerImg;
    }
    public void setHeaderImage(String headerImage){
        this.headerImage = headerImage;
    }
}
