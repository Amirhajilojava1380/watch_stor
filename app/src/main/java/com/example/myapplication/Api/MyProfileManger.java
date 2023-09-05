package com.example.myapplication.Api;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.HashMap;

public class MyProfileManger {
    private Context context ;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor ;

    public static final String IS_LOGIN = "IS_LOGIN";
    public static final String ID = "ID";
    public static final String PHON = "PHON";
    public static final String EMAILE = "EMAILE";



    public MyProfileManger ( Context context ) {
        this.context = context;
        sharedPreferences =PreferenceManager.getDefaultSharedPreferences(context);
        editor =sharedPreferences.edit();
    }

    public void seveUserData(UserData userData){
        editor.putString ( ID,userData.getId () );
        editor.putString ( PHON, userData.getPhon () );
        editor.putString ( EMAILE,userData.getUser_email () );
        editor.putBoolean ( IS_LOGIN,true );
        editor.commit ();

    }
    public void Loguot(){
       editor.clear ();
       editor.commit ();

    }

    public HashMap<String , String> getUserData(){
        HashMap <String , String>userdata = new HashMap <>();
        userdata.put ( ID,sharedPreferences.getString ( ID,null ) );
        userdata.put ( PHON,sharedPreferences.getString ( PHON,null ) );
        userdata.put ( EMAILE,sharedPreferences.getString ( EMAILE,null ) );
        return userdata;
    }


    public boolean islogin(){

    return sharedPreferences.getBoolean ( IS_LOGIN,false );

    }







}
