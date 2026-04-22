package com.finalproject.universitymanagementsystem;

import com.finalproject.identification.Faculty;
import com.finalproject.identification.Student;
import com.finalproject.identification.User;
import com.finalproject.primary.Course;
import com.finalproject.primary.Event;
import com.finalproject.primary.LectureTime;
import com.finalproject.primary.Subject;
import com.finalproject.universitymanagementsystem.DataLists.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.finalproject.universitymanagementsystem.ExcelReader.*;

public class StartApp extends javafx.application.Application {
    public static String masterFilePath = "src/main/resources/com/finalproject/universitymanagementsystem/UMS_Data.xlsx";  //filepath to Excel import sheet
    public static List<User> masterUserList = new ArrayList<>();    //global user list
    public static String masterDefaultImage = "src/main/resources/com/finalproject/universitymanagementsystem/default.jpg";    //filepath to default profile image
    public static String masterFallbackImageFiletype = ".jpg";  //fallback image filetype if type is not specified
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartApp.class.getResource("interface/loginScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 450, 300);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        if(args.length != 0){
            masterFilePath = args[0];
        }
        masterUserList.add(new User("A001","admin123",3));  //add default admin user
        masterUserList.addAll(loadUsersFromExcel(masterFilePath));
        for(Student student : loadStudentsFromExcel(masterFilePath)){
            StudentList.addStudent(student);
            System.out.println(student.toString()); //remove later, just used for Excel import debugging
        }
        for(Faculty faculty : loadFacultyFromExcel(masterFilePath)){
            FacultyList.addFaculty(faculty);
            System.out.println(faculty.toString()); //remove later, just used for Excel import debugging
        }
        for(Subject subject : loadSubjectsFromExcel(masterFilePath)){
            SubjectList.addSubject(subject);
            System.out.println(subject.toString()); //remove later, just used for Excel import debugging
        }
        for(Course course : loadCoursesFromExcel(masterFilePath)){
            CourseList.addCourse(course);
            boolean facultyFound = false;
            for(Faculty faculty : FacultyList.getFacultyList()){
                if(course.getTeacherName().toLowerCase().contains(faculty.getName().toLowerCase())){
                    faculty.addCourse(course);
                    facultyFound = true;
                }
            }
            if(facultyFound == false){
                System.out.println(course.getTeacherName() + " not found in faculty list. Cannot add course " + course.getName() + " to their course offerings.");
            }
            System.out.println(course.toString());  //remove later, just used for Excel import debugging
            for(LectureTime lectureTime : course.getLectureTimes()){    //remove later, just used for Excel import debugging
                System.out.println(lectureTime.toString());
            }
            for(Student student : StudentList.getStudentList()){
                for(String enrolledSubject : student.getEnrolledSubjects()){
                    if(enrolledSubject.equalsIgnoreCase(course.getSubjectCode())){    //enroll student into first instance of matching course subject (section)
                        if(student.enrollCourse(course)){   //enroll student into course as long as they are not in a duplicate subject
                            System.out.println(student.getName() + " successfully enrolled into course " + course.getName() + " section " + course.getSectionNumber());
                            break;
                        }
                    }
                }
            }
        }
        for(Event event : loadEventsFromExcel(masterFilePath)) {
            EventList.addEvent(event);
            System.out.println(event.toString());   //remove later, just used for Excel import debugging
        }
        launch();   //invokes start method in this class (above)
    }
}