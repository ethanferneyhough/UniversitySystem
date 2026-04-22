package com.finalproject.primary;

public class Subject {
    private String subjectName;
    private String subjectCode;

    public Subject(){
        this.subjectName = "Unset";
        this.subjectCode = "Unset";
    }
    public Subject(String name, String code) {
        this.subjectName = name;
        this.subjectCode = code;
    }

    public String getSubjectName(){
        return this.subjectName;
    }
    public void setSubjectName(String name){
        this.subjectName = name;
    }
    public String getSubjectCode(){
        return this.subjectCode;
    }
    public void setSubjectCode(String code){
        this.subjectCode = code;
    }
    public String toString(){
        return (this.subjectCode + " "  + this.subjectName);
    }
}
