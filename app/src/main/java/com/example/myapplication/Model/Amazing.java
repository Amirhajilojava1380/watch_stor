package com.example.myapplication.Model;

public class Amazing {
 private  Object object;

    public Amazing ( Object object , int type ) {
        this.object = object;
        this.type = type;
    }

    private int type ;

    public Object getObject ( ) {
        return object;
    }

    public void setObject ( Object object ) {
        this.object = object;
    }

    public int getType ( ) {
        return type;
    }

    public void setType ( int type ) {
        this.type = type;
    }


}
