package com.example.myapplication.Api;

public class UserData {
    private String id,phon,user_email;

    public UserData ( ) { }

    public String getPhon ( ) { return phon; }

    public void setPhon ( String phon ) {
        this.phon = phon;
    }


    public String getUser_email ( ) {
        return user_email;
    }

    public void setUser_email ( String user_email ) {
        this.user_email = user_email;
    }


    public String getId ( ) {
        return id;
    }

    public void setId ( String id ) {
        this.id = id;
    }
}
