package com.example.myapplication.Api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Apiclient {
     private   static String BASE_URL="http://192.168.1.3/viewpajer/";
     public  static   Retrofit retrofit = null;
     public  static  Retrofit getapiclient(){
         if ( retrofit==null ){
             Gson gson =new GsonBuilder ().setLenient ().create ();
             retrofit = new Retrofit.Builder ().baseUrl ( BASE_URL ).addConverterFactory ( GsonConverterFactory.create (gson) ).build ();

         }
         return retrofit;
     }


}
