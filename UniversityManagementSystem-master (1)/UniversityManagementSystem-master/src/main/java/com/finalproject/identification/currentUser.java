package com.finalproject.identification;

public class currentUser {
    private static int userType = 0;

    public static int getUserType(){
        return userType;
    }

    public static void setUserType(int type){
        userType = type;
    }
}
