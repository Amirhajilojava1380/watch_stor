package com.example.myapplication.Model;

public class Question {
    private String id,description,titel;

    public String getDescription ( ) {
        return description;
    }

    public void setDescription ( String description ) {
        this.description = description;
    }

    public String getId ( ) {
        return id;
    }

    public void setId ( String id ) {
        this.id = id;
    }

    public String getTitel ( ) {
        return titel;
    }

    public void setTitel ( String titel ) {
        this.titel = titel;
    }

    public Question ( ) {
    }
}
