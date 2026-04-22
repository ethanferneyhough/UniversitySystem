package com.finalproject.primary;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Course extends Subject {
    private String name;
    private int code;
    private String sectionNumber;
    private String teacherName;
    private int capacity;
    private ArrayList<LectureTime> lectureTimes = new ArrayList<LectureTime>();
    private LocalDateTime examDateTime;
    private String location;
    private static int globalId = 1;

    public Course(){    //default constructor
        this.name = "Unset";
        this.code = globalId;
        globalId++;
        this.setSubjectCode("Unset");
        this.sectionNumber = "Unset";
        this.teacherName = "Unset";
        this.capacity = 0;
        this.examDateTime = LocalDateTime.now();
        this.location = "Unset";
    }
    //below is a constructor for Excel import
    public Course(double code, String name, String subjectCode, String sectionNumber, double capacity, LocalDateTime examDateTime, String location, String teacherName) {
        this.name = name;
        this.code = (int)code;
        this.setSubjectCode(subjectCode);
        this.sectionNumber = sectionNumber;
        this.teacherName = teacherName;
        this.capacity = (int)capacity;
        this.location = location;
        this.examDateTime = examDateTime;
    }
    public void setExamDate(LocalDateTime examDateTime){
        this.examDateTime = examDateTime;
    }
    public void setExamDate(int day, int month, int year, int hour, int minute){
        this.examDateTime = LocalDateTime.of(year,month,day,hour,minute);
    }
    public LocalDateTime getExamDate(){
        return this.examDateTime;
    }
    public void addLectureTime(LectureTime lectureTime){
        boolean overlap = false;
        if(this.lectureTimes.isEmpty()){
            this.lectureTimes.add(lectureTime);
        }else{
            for(LectureTime currentLectureTime : this.lectureTimes){
                if(currentLectureTime.getDayOfWeek() == lectureTime.getDayOfWeek()){
                    if((currentLectureTime.getEndHour() == lectureTime.getStartHour() && currentLectureTime.getEndMinute() >= lectureTime.getStartMinute()) || (currentLectureTime.getStartHour() == lectureTime.getEndHour() && currentLectureTime.getEndMinute() >= lectureTime.getStartMinute())){
                        //check if newly proposed lecture time would overlap any existing
                        overlap = true;
                    }
                }
                if(overlap == true){
                    System.out.println("Lecture time overlaps with an existing lecture time.");
                }else{
                    this.lectureTimes.add(lectureTime);
                    System.out.println("Lecture time added to course.");
                    break;
                }
            }
        }
    }
    public void removeLectureTime(LectureTime lectureTime){
        for(LectureTime currentLectureTime : lectureTimes){
            //search for matching lectureTime object
            if(lectureTime.getId() == currentLectureTime.getId()){
                lectureTimes.remove(currentLectureTime);
                System.out.println("Matching lecture time found. Lecture time removed.");
            }
        }
    }
    public void removeLectureTime(int id){
        for(LectureTime currentLectureTime : lectureTimes){
            //search for matching lectureTime id
            if(id == currentLectureTime.getId()){
                lectureTimes.remove(currentLectureTime);
                System.out.println("Matching lecture time found. Lecture time removed.");
            }
        }
    }
    public void clearLectureTimes(){
        lectureTimes.clear();
        System.out.println("Lecture times cleared.");
    }
    public ArrayList<LectureTime> getLectureTimes(){
        return lectureTimes;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getCode(){
        return this.code;
    }
    public void setCode(int code){
        this.code = code;
    }
    public String getSectionNumber(){
        return this.sectionNumber;
    }
    public void setSectionNumber(String sectionNumber){
        this.sectionNumber = sectionNumber;
    }
    public String getTeacherName(){
        return this.teacherName;
    }
    public void setTeacherName(String teacherName){
        this.teacherName = teacherName;
    }
    public int getCapacity(){
        return this.capacity;
    }
    public void setCapacity(int capacity){
        this.capacity = capacity;
    }
    public String getLocation(){
        return this.location;
    }
    public void setLocation(String location){
        this.location = location;
    }
    public String toString(){
        return (this.code + " "  + this.name + " "  + this.getSubjectCode() + " "  + this.sectionNumber + " "  + this.capacity + " " + this.examDateTime.toString() + " " + this.location + " "  + this.teacherName);
    }
}
