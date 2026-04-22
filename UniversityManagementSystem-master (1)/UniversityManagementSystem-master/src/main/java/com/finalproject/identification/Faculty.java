package com.finalproject.identification;

import com.finalproject.primary.Course;
import com.finalproject.universitymanagementsystem.StartApp;
import java.util.ArrayList;
import java.util.List;

public class Faculty extends Person {
    private ArrayList<Course> offeredCourses = new ArrayList<Course>();
    private String degree;
    private String officeLocation;
    private String researchInterest;

    public Faculty(){   //default constructor
        this.setName("Unset");
        this.setEmailAddress("Unset");
        this.degree = "Unset";
        this.officeLocation = "Unset";
        this.researchInterest = "Unset";
        this.setProfilePhoto(StartApp.masterDefaultImage);
    }
    public Faculty(String name, String emailAddress, String degree, String researchInterest){   //management constructor
        this.setName(name);
        this.setEmailAddress(emailAddress);
        this.degree = degree;
        this.officeLocation = "Unset";
        this.researchInterest = researchInterest;
        this.setProfilePhoto(StartApp.masterDefaultImage);
    }
    //below is the constructor for Excel import
    public Faculty(String name, String degree, String researchInterest, String emailAddress, String officeLocation){
        this.setName(name);
        this.setEmailAddress(emailAddress);
        this.degree = degree;
        this.officeLocation = officeLocation;
        this.researchInterest = researchInterest;
        this.setProfilePhoto(StartApp.masterDefaultImage);
    }
    public String getOfficeLocation(){
        return this.officeLocation;
    }
    public void setOfficeLocation(String officeLocation){
        this.officeLocation = officeLocation;
    }
    public String getResearchInterest(){
        return this.researchInterest;
    }
    public void setResearchInterest(String researchInterest){
        this.researchInterest = researchInterest;
    }
    public String getDegree(){
        return this.degree;
    }
    public void setDegree(String degree){
        this.degree = degree;
    }
    public void addCourse(Course course){
        //adds a course to the faculties offered courses as long as it isn't a duplicate
        boolean duplicateSection = false;
        for(Course offeredCourse : this.offeredCourses){
            if(offeredCourse.getSectionNumber().toLowerCase().equals(course.getSectionNumber().toLowerCase()) && offeredCourse.getName().toLowerCase().equals(course.getName().toLowerCase())){
                duplicateSection = true;
            }
        }
        if(duplicateSection == true){
            System.out.println(this.getName() + " already offers this course and section.");
        }else{
            this.offeredCourses.add(course);
            System.out.println("Course " + course.getName() + " added to " + this.getName() + " course offerings.");
        }
    }
    public void removeCourse(String courseName){
        //finds a matching course that the faculty offers based on the provided course name and removes the offered course if a match is found
        for(Course course : this.offeredCourses){
            if(course.getName().toLowerCase().equals(courseName.toLowerCase())){
                this.offeredCourses.remove(course);
                System.out.println("Course match found. Course removed.");
            }
        }
    }
    public String toString(){
        return (this.getName() + " "  + this.degree + " "  + this.researchInterest + " "  + this.getEmailAddress() + " "  + this.officeLocation + " "  + this.offeredCourses);
    }
    public List<Course> getOfferedCourses() {
        return this.offeredCourses;
    }

}
