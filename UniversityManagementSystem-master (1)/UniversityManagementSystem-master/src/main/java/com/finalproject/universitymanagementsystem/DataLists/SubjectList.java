package com.finalproject.universitymanagementsystem.DataLists;

import com.finalproject.primary.Subject;

import java.util.ArrayList;
import java.util.List;

public class SubjectList {
    private static List<Subject> subjectList = new ArrayList<>();

    public static void addSubject(Subject subject){
        subjectList.add(subject);
    }

    public static void removeSubject(Subject subject) {
        subjectList.remove(subject);
    }

    public static List<Subject> getSubjectList(){
        return subjectList;
    }
}
