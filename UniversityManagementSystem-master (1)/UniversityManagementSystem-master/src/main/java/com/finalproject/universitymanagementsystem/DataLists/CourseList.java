package com.finalproject.universitymanagementsystem.DataLists;

import com.finalproject.primary.Course;
import java.util.ArrayList;
import java.util.List;

public class CourseList {
    private static List<Course> courseList = new ArrayList<>();

    public static void addCourse(Course course) {
        courseList.add(course);
    }

    public static void removeCourse(Course course) {
        courseList.remove(course);
    }

    public static List<Course> getCourseList(){
        return courseList;
    }
}
