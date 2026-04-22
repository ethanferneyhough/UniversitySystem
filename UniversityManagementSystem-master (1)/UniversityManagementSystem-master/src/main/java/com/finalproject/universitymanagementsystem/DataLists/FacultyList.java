package com.finalproject.universitymanagementsystem.DataLists;

import com.finalproject.identification.Faculty;

import java.util.ArrayList;
import java.util.List;

public class FacultyList {
    private static List<Faculty> facultyList = new ArrayList<>();

    public static void addFaculty(Faculty faculty){
        facultyList.add(faculty);
    }

    public static void removeFaculty(Faculty faculty) {facultyList.remove(faculty); }

    public static List<Faculty> getFacultyList() { return facultyList; }
}
