package com.finalproject.identification;

import com.finalproject.primary.Course;
import com.finalproject.primary.Grade;
import com.finalproject.primary.Tuition;
import com.finalproject.universitymanagementsystem.StartApp;

import java.util.ArrayList;

public class Student extends Person {
    private static long globalId = 20250001; //unique id for Student class
    private String address;
    private String telephone;
    private Tuition tuition = new Tuition();
    private ArrayList<Course> enrolledCourses = new ArrayList<Course>();
    private ArrayList<Grade> grades = new ArrayList<Grade>();
    private String currentSemester;
    private ArrayList<String> enrolledSubjects = new ArrayList<String>();
    private String academicLevel;
    private String thesisTitle;
    private double progress;

    public Student(){   //default constructor
        this.setName("Unset");
        this.setUniversityId("S"+globalId);
        globalId++;
        this.address = "Unset";
        this.telephone = "Unset";
        this.setEmailAddress("Unset");
        this.currentSemester = "Unset";
        this.academicLevel = "Unset";
        this.thesisTitle = "Unset";
        this.progress = 0;
        this.setProfilePhoto(StartApp.masterDefaultImage);
    }
    public Student(String name, String address, String telephone, String academicLevel, String emailAddress, String currentSemester){   //management constructor
        this.setName(name);
        this.setUniversityId("S"+globalId);
        globalId++; //increment unique Student class id
        this.address = address;
        this.telephone = telephone;
        this.setEmailAddress(emailAddress);
        this.currentSemester = currentSemester;
        this.academicLevel = academicLevel;
        if(this.academicLevel.equals("Undergraduate")){
            this.tuition.setFee(5000);
            this.tuition.setPaymentStatus(false);
        }else{
            this.tuition.setFee(4000);
            this.tuition.setPaymentStatus(false);
        }
        this.thesisTitle = "Unset";
        this.progress = 0;
        this.setProfilePhoto(StartApp.masterDefaultImage);
    }
    //below is a constructor for Excel import
    public Student(String id, String name, String address, String telephone, String emailAddress, String academicLevel, String currentSemester, String profilePhoto, ArrayList<String> enrolledSubjects, String thesisTitle, double progress){
        this.setName(name);
        this.setUniversityId(id);
        this.address = address;
        this.telephone = telephone;
        this.setEmailAddress(emailAddress);
        this.currentSemester = currentSemester;
        this.academicLevel = academicLevel;
        if(this.academicLevel.equals("Undergraduate")){
            this.tuition.setFee(5000);
            this.tuition.setPaymentStatus(false);
        }else{
            this.tuition.setFee(4000);
            this.tuition.setPaymentStatus(false);
        }
        this.setProfilePhoto(profilePhoto);
        this.enrolledSubjects = enrolledSubjects;
        this.thesisTitle = thesisTitle;
        this.progress = progress;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public String getAddress(){
        return this.address;
    }
    public void setTelephone(String telephone){
        this.telephone = telephone;
    }
    public String getTelephone(){
        return this.telephone;
    }
    public void setAcademicLevel(String academicLevel){
        //sets both the academic level and corresponding tuition
        this.academicLevel = academicLevel;
        if(this.academicLevel.equalsIgnoreCase("Undergraduate")){
            this.tuition.setFee(5000);
            this.tuition.setPaymentStatus(false);
        }else{
            this.tuition.setFee(4000);
            this.tuition.setPaymentStatus(false);
        }
    }
    public String getAcademicLevel(){
        return this.academicLevel;
    }
    public void setCurrentSemester(String currentSemester){
        this.currentSemester = currentSemester;
    }
    public String getCurrentSemester(){
        return this.currentSemester;
    }
    public void setThesisTitle(String thesisTitle){
        this.thesisTitle = thesisTitle;
    }
    public String getThesisTitle(){
        return this.thesisTitle;
    }
    public double getProgress(){
        return this.progress;
    }
    public ArrayList<String> getEnrolledSubjects(){
        return this.enrolledSubjects;
    }
    public Tuition getTuition(){
        return this.tuition;
    }
    public ArrayList<Course> getEnrolledCourses(){
        return this.enrolledCourses;
    }
    public ArrayList<Grade> getGrades(){
        return this.grades;
    }
    public void setProgress(float progress){
        this.progress = progress;
    }
    public boolean enrollCourse(Course course){
        //enrolls a student into a provided course as long as the student isn't already enrolled in a course of the same subject
        boolean duplicateSubject = false;
        for(Course enrolledCourse : this.enrolledCourses){
            if(enrolledCourse.getSubjectCode().equalsIgnoreCase(course.getSubjectCode())){
                duplicateSubject = true;
            }
        }
        if(duplicateSubject == true){
            System.out.println("Student is already enrolled in this subject. Please remove the enrolled subject course first.");
            return false;
        }else{
            this.enrolledCourses.add(course);
            this.enrolledSubjects.add(course.getSubjectCode());
            System.out.println("Course added. Subject added.");
            course.setCapacity(course.getCapacity()-1);  //decrement course capacity as long as they are not in a duplicate subject
            return true;
        }
    }
    public boolean removeCourse(String courseName){
        //finds a matching course that the student is enrolled in based on the provided course name and removes both the enrolled course and its corresponding subject and grade if a match is found
        for(Course course : this.enrolledCourses){
            if(course.getName().toLowerCase().equals(courseName.toLowerCase())){
                for(String enrolledSubject : this.enrolledSubjects){
                    if(course.getSubjectCode().toLowerCase().equals(enrolledSubject.toLowerCase())){
                        this.enrolledSubjects.remove(enrolledSubject);
                        System.out.println("Course match found. Subject removed.");
                    }
                }
                for(Grade currentGrade : this.grades){
                    if(currentGrade.getCourseName().toLowerCase().equals(courseName.toLowerCase())){
                        grades.remove(currentGrade);
                        System.out.println("Course match found. Grade removed.");
                    }
                    this.updateProgress();
                }
                this.enrolledCourses.remove(course);
                System.out.println("Course match found. Course removed.");
                course.setCapacity(course.getCapacity()+1); //increment course capacity after student removal
                return true;
            }
        }
        System.out.println("No matching courses found for removal.");
        return false;
    }
    public void setGrade(String courseName, float grade){
        //checks to see if the student is enrolled in a course, and adds a new grade or updates and existing one
        for(Course course : this.enrolledCourses){
            if(course.getName().toLowerCase().equals(courseName.toLowerCase())){
                for(Grade currentGrade : this.grades){
                    if(currentGrade.getCourseName().toLowerCase().equals(courseName.toLowerCase())){
                        currentGrade.setGrade(grade);
                        System.out.println("Course match found. Grade updated.");
                    }else{
                        this.grades.add(new Grade(courseName,grade));
                        System.out.println("Course match found. Grade added.");
                    }
                    this.updateProgress();
                }
            }else{
                System.out.println("Failed to find enrolled course to update student grade.");
            }
        }
    }
    private void updateProgress(){
        this.progress = ((float)this.grades.size())/((float)this.enrolledCourses.size())*100;
    }
    @Override
    public String toString(){
        return (this.getUniversityId() + " "  + this.getName() + " "  + this.address + " "  + this.telephone + " "  + this.getEmailAddress() + " "  + this.academicLevel + " "  + this.currentSemester + " " + this.enrolledSubjects + " " + this.thesisTitle + " " + this.progress + " " + this.tuition.getFee() + " " + this.tuition.getPaymentStatus());
    }
}
