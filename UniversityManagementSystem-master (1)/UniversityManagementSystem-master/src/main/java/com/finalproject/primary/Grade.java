package com.finalproject.primary;

//two variable grade class for tracking course and grades separate from student class
public class Grade {
    private String courseName;
    private float grade;

    public Grade(){
        this.courseName = "Unset";
        this.grade = 0;
    }
    public Grade(String courseName, float grade){
        this.courseName = courseName;
        this.grade = grade;
    }
    public void setGrade(float grade){
        this.grade = grade;
    }
    public float getGrade(){
        return this.grade;
    }
    public void setCourseName(String courseName){
        this.courseName = courseName;
    }
    public String getCourseName(){
        return this.courseName;
    }
}
