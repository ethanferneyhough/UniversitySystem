package com.finalproject.universitymanagementsystem;

import com.finalproject.identification.Faculty;
import com.finalproject.identification.Student;
import com.finalproject.identification.currentUser;
import com.finalproject.primary.Course;
import com.finalproject.primary.Event;
import com.finalproject.primary.Subject;
import com.finalproject.universitymanagementsystem.DataLists.*;
import com.finalproject.universitymanagementsystem.ListCells.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class ListController {
    @FXML
    private ListView<Subject> userSubjectList;

    @FXML
    private ListView<Subject> adminSubjectList;

    @FXML
    private ListView<Course> userCourseList;

    @FXML
    private ListView<Course> adminCourseList;

    @FXML
    private ListView<Student> adminStudentList;

    @FXML
    private ListView<Student> facultyStudentList;

    @FXML
    private ListView<Faculty> adminFacultyList;

    @FXML
    private ListView<Faculty> studentFacultyList;

    @FXML
    private ListView<Event> adminEventList;

    @FXML
    private ListView<Event> userEventList;

    private static ListController instance;

    public ListController() {
        instance = this;
    }

    public static ListController getInstance(){
        return instance;
    }

    public void updateSubjects() {
        ObservableList<Subject> subjects = FXCollections.observableArrayList(SubjectList.getSubjectList());
        if (currentUser.getUserType() == 3)
            adminSubjectList.setItems(subjects);
        else
            userSubjectList.setItems(subjects);
    }

    public void updateCourses() {
        ObservableList<Course> courses = FXCollections.observableArrayList(CourseList.getCourseList());
        if (currentUser.getUserType() == 3)
            adminCourseList.setItems(courses);
        else
            userCourseList.setItems(courses);
    }

    public void updateStudents() {
        ObservableList<Student> students = FXCollections.observableArrayList(StudentList.getStudentList());
        if (currentUser.getUserType() == 3)
            adminStudentList.setItems(students);
        else
            facultyStudentList.setItems(students);
    }

    public void updateFaculty() {
        ObservableList<Faculty> faculties = FXCollections.observableArrayList(FacultyList.getFacultyList());
        if (currentUser.getUserType() == 3)
            adminFacultyList.setItems(faculties);
        else
            studentFacultyList.setItems(faculties);
    }

    public void updateEvent() {
        ObservableList<Event> events = FXCollections.observableArrayList(EventList.getEventList());
        if (currentUser.getUserType() == 3)
            adminEventList.setItems(events);
        else
            userEventList.setItems(events);
    }

    public void adminSubject() {
        updateSubjects();
        adminSubjectList.setCellFactory(param -> new AdminSubjectCell());
    }

    public void userSubject() {
        updateSubjects();
        userSubjectList.setCellFactory(param -> new UserSubjectCell());
    }

    public void adminCourse() {
        updateCourses();
        adminCourseList.setCellFactory(param -> new AdminCourseCell());
    }

    public void userCourse() {
        updateCourses();
        userCourseList.setCellFactory(param -> new UserCourseCell());
    }

    public void adminStudent() {
        updateStudents();
        adminStudentList.setCellFactory(param -> new AdminStudentCell());
    }

    public void facultyStudent() {
        updateStudents();
        facultyStudentList.setCellFactory(param -> new FacultyStudentCell());
    }

    public void adminFaculty() {
        updateFaculty();
        adminFacultyList.setCellFactory(param -> new AdminFacultyCell());
    }

    public void studentFaculty() {
        updateFaculty();
        studentFacultyList.setCellFactory(param -> new StudentFacultyCell());
    }

    public void adminEvent() {
        updateEvent();
        adminEventList.setCellFactory(param -> new AdminEventCell());
    }

    public void userEvent() {
        updateEvent();
        userEventList.setCellFactory(param -> new UserEventCell());
    }
}
