package com.finalproject.universitymanagementsystem.CellControllers;

import com.finalproject.primary.Course;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class UserCourseController {
    @FXML
    private Label courseName;

    @FXML
    private Label courseCode;

    @FXML
    private Label courseCapacity;

    @FXML
    private Label courseSubject;

    @FXML
    private Label courseTeacher;

    @FXML
    private Label courseLecture;

    @FXML
    private Label courseExam;

    @FXML
    private Label courseLocation;

    public void setCourse(Course course) {
        courseName.setText(course.getName());
        courseCode.setText(course.getCode() + " Section " + course.getSectionNumber());
        courseCapacity.setText("Capacity: " + course.getCapacity());
        courseSubject.setText(course.getSubjectName());
        courseTeacher.setText(course.getTeacherName());
        courseLecture.setText(course.getLectureTimes().toString());
        courseExam.setText(course.getExamDate().toString());
        courseLocation.setText(course.getLocation());
    }
}
