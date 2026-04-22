package com.finalproject.identification;

import java.util.List;

public class User {
    private String universityId;
    private String password;
    private int userType; //0 = Invalid, 1 = Student, 2 = Faculty, 3 = Admin

    public User() {
        this.universityId = "Unset";
        this.password = "Unset";
        this.userType = 0;
    }
    public User(String universityId, String password, int userType) {
        this.universityId = universityId;
        this.password = password;
        this.userType = userType;
    }

    public String getUniversityId() {
        return universityId;
    }
    public void setUniversityId(String id){
        this.universityId = id;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public int getUserType() {
        return userType;
    }
    public void setUserType(int type) {
        this.userType = type;
    }
    @Override
    public String toString() {
        return "ID: " + universityId + ", Password: " + password + ", Type: " + userType;
    }
    public static int GetUserInfo(String TypedUniversityId, String TypedPassword, List<User> GrabbedArraylist) {

        //GrabbedArrayList = GrabbedArrayList()

        for (User element : GrabbedArraylist) {
            if (element.getUniversityId().equals(TypedUniversityId) && element.getPassword().equals(TypedPassword)) {
                if(element.getUserType() == 1) {
                    return 1; //student
                } else if (element.getUserType() == 2){
                    return 2; //faculty
                } else if (element.getUserType() == 3){
                    return 3; //admin
                }
            }
        }
        return 0;
    }
}
