package com.finalproject.universitymanagementsystem.DataLists;

import com.finalproject.identification.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentList {
    private static List<Student> students = new ArrayList<>();

    public static void removeStudent(Student student) {students.remove(student); }

    public static void addStudent(Student student){
        students.add(student);
    }

    public static List<Student> getStudentList() {
        return students;
    }
}
