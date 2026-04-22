package com.finalproject.universitymanagementsystem.CellControllers;

import com.finalproject.primary.Course;
import com.finalproject.universitymanagementsystem.DataLists.CourseList;
import com.finalproject.universitymanagementsystem.ListController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AdminCourseController {
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

    private Course course;

    @FXML
    private void deleteCourse() {
        CourseList.removeCourse(course);
        ListController.getInstance().updateCourses();
    }

    public void setCourse(Course course) {
        this.course = course;
        courseName.setText(course.getName());
        courseCode.setText(course.getCode() + " Section " + course.getSectionNumber());
        courseCapacity.setText("Capacity: " + course.getCapacity());
        courseSubject.setText(course.getSubjectCode());
        courseTeacher.setText(course.getTeacherName());
        courseLecture.setText(course.getLectureTimes().toString());
        courseExam.setText(course.getExamDate().toString());
        courseLocation.setText(course.getLocation());
    }
}