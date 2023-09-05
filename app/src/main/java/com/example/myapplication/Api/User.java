package com.example.myapplication.Api;



import com.google.gson.annotations.SerializedName;

public class User {
    private  String massage;
    private  boolean status;

    @SerializedName ("data")
    private UserData userData;

    public User ( ) {
    }

    public String getMassage ( ) {
        return massage;
    }

    public void setMassage ( String massage ) {
        this.massage = massage;
    }

    public boolean isStatus ( ) {
        return status;
    }

    public void setStatus ( boolean status ) {
        this.status = status;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData ( UserData userData ) {
        this.userData = userData;
    }
}
