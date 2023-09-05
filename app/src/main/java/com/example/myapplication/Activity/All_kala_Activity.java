package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import com.android.volley.toolbox.Volley;
import com.example.myapplication.Adapter.Allkala_ditel_Adapter;

import com.example.myapplication.Golobol.Link;

import com.example.myapplication.Model.OffAmazing;
import com.example.myapplication.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;

import java.util.List;


public class All_kala_Activity extends AppCompatActivity {
    List< OffAmazing > offAmazings_all =new ArrayList<> (  );
    RequestQueue requestQueue;
    RecyclerView recyclerView_all;
    Allkala_ditel_Adapter allkala_adapter;
    //filter
    TextView textView_filter;
    public  static int SET_CHET_RDB=1;
    public  static final int  RDB_ALL=1;
    public  static final int  RDB_GRON=2;
    public  static final int  RDB_ARZON=3;
    public  static final int  RDB_VIEW=4;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_all_kala );
        requestQueue= Volley.newRequestQueue ( this);


        //filter
        textView_filter =findViewById ( R.id.text_diteli_fitel );
        textView_filter.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(All_kala_Activity.this);
                View Layuot_shit_bottom = LayoutInflater.from(getApplicationContext())
                        .inflate(R.layout.item_radio, findViewById(androidx.constraintlayout.widget.R.id.parent));
                RadioButton gron =Layuot_shit_bottom.findViewById ( R.id.gron );
                RadioButton arzon =Layuot_shit_bottom.findViewById ( R.id.arzon );
                RadioButton view =Layuot_shit_bottom.findViewById ( R.id.view );
                RadioButton all =Layuot_shit_bottom.findViewById ( R.id.all );


                switch ( SET_CHET_RDB ){
                    case RDB_ALL:all.setChecked ( true );
                        break;

                    case RDB_GRON:gron.setChecked ( true );
                    break;

                    case RDB_ARZON:arzon.setChecked ( true );
                        break;

                    case RDB_VIEW:view.setChecked ( true );
                        break;
                }
                all.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        offAmazings_all.clear();
                        getAll_kala_ditel();
                        bottomSheetDialog.dismiss();
                        SET_CHET_RDB =RDB_ALL;
                    }
                });
                gron.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        offAmazings_all.clear();
                        getAll_gron_ditel();
                        bottomSheetDialog.dismiss();
                        SET_CHET_RDB =RDB_GRON;
                    }
                });

                arzon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        offAmazings_all.clear();
                        getAll_arzon_ditel();
                        bottomSheetDialog.dismiss();
                        SET_CHET_RDB =RDB_ARZON;
                    }
                });


                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        offAmazings_all.clear();
                        getAll_view_ditel();
                        bottomSheetDialog.dismiss();
                        SET_CHET_RDB =RDB_VIEW;
                    }
                });

                bottomSheetDialog.setContentView(Layuot_shit_bottom);
                bottomSheetDialog.show();

            }
        } );





        getAll_kala_ditel();

    }

    private void getAll_view_ditel ( ) {
        recyclerView_all =findViewById ( R.id.RecyclerViewall );
        recyclerView_all.setHasFixedSize ( true );
        recyclerView_all.setLayoutManager ( new LinearLayoutManager ( this ) );
        allkala_adapter =new Allkala_ditel_Adapter ( this ,offAmazings_all );
        recyclerView_all.setAdapter ( allkala_adapter );
        String url = Link.VIEW;
        Response.Listener< JSONArray > listener =new Response.Listener< JSONArray > ( ) {
            @Override
            public void onResponse ( JSONArray response ) {
                Gson gson = new Gson ();
                OffAmazing[]  OffAmazings = gson.fromJson (response. toString (),OffAmazing[].class);
                for (int i=0;i<OffAmazings.length;i++){
                    offAmazings_all.add( OffAmazings[i] );
                    allkala_adapter.notifyDataSetChanged ();
                }
            }
        };

        Response.ErrorListener listener_erorr =new Response.ErrorListener ( ) {
            @Override
            public void onErrorResponse ( VolleyError error ) {
                Toast.makeText ( getApplicationContext (), "no"+error.getMessage () , Toast.LENGTH_SHORT ).show ( );

            }
        };

        JsonArrayRequest request = new JsonArrayRequest ( Request.Method.GET,url,null,listener,listener_erorr );
        requestQueue.add ( request );
    }

    private void getAll_arzon_ditel ( ) {
        recyclerView_all =findViewById ( R.id.RecyclerViewall );
        recyclerView_all.setHasFixedSize ( true );
        recyclerView_all.setLayoutManager ( new LinearLayoutManager ( this ) );
        allkala_adapter =new Allkala_ditel_Adapter ( this ,offAmazings_all );
        recyclerView_all.setAdapter ( allkala_adapter );
        String url = Link.ARZON;
        Response.Listener< JSONArray > listener =new Response.Listener< JSONArray > ( ) {
            @Override
            public void onResponse ( JSONArray response ) {
                Gson gson = new Gson ();
                OffAmazing[]  OffAmazings = gson.fromJson (response. toString (),OffAmazing[].class);
                for (int i=0;i<OffAmazings.length;i++){
                    offAmazings_all.add( OffAmazings[i] );
                    allkala_adapter.notifyDataSetChanged ();
                }
            }
        };

        Response.ErrorListener listener_erorr =new Response.ErrorListener ( ) {
            @Override
            public void onErrorResponse ( VolleyError error ) {
                Toast.makeText ( getApplicationContext (), "no"+error.getMessage () , Toast.LENGTH_SHORT ).show ( );

            }
        };

        JsonArrayRequest request = new JsonArrayRequest ( Request.Method.GET,url,null,listener,listener_erorr );
        requestQueue.add ( request );
    }

    private void getAll_gron_ditel ( ) {
        recyclerView_all =findViewById ( R.id.RecyclerViewall );
        recyclerView_all.setHasFixedSize ( true );
        recyclerView_all.setLayoutManager ( new LinearLayoutManager ( this ) );
        allkala_adapter =new Allkala_ditel_Adapter ( this ,offAmazings_all );
        recyclerView_all.setAdapter ( allkala_adapter );
        String url = Link.GERON;
        Response.Listener< JSONArray > listener =new Response.Listener< JSONArray > ( ) {
            @Override
            public void onResponse ( JSONArray response ) {
                Gson gson = new Gson ();
                OffAmazing[]  OffAmazings = gson.fromJson (response. toString (),OffAmazing[].class);
                for (int i=0;i<OffAmazings.length;i++){
                    offAmazings_all.add( OffAmazings[i] );
                    allkala_adapter.notifyDataSetChanged ();
                }
            }
        };

        Response.ErrorListener listener_erorr =new Response.ErrorListener ( ) {
            @Override
            public void onErrorResponse ( VolleyError error ) {
                Toast.makeText ( getApplicationContext (), "no"+error.getMessage () , Toast.LENGTH_SHORT ).show ( );

            }
        };

        JsonArrayRequest request = new JsonArrayRequest ( Request.Method.GET,url,null,listener,listener_erorr );
        requestQueue.add ( request );
    }



    private void getAll_kala_ditel ( ) {
        recyclerView_all =findViewById ( R.id.RecyclerViewall );
        recyclerView_all.setHasFixedSize ( true );
        recyclerView_all.setLayoutManager ( new LinearLayoutManager ( this ) );
        allkala_adapter =new Allkala_ditel_Adapter ( this ,offAmazings_all );
        recyclerView_all.setAdapter ( allkala_adapter );
        String url = Link.ALL_KALA_DITEL;
        Response.Listener< JSONArray > listener =new Response.Listener< JSONArray > ( ) {
            @Override
            public void onResponse ( JSONArray response ) {
                Gson gson = new Gson ();
                OffAmazing[]  OffAmazings = gson.fromJson (response. toString (),OffAmazing[].class);
                for (int i=0;i<OffAmazings.length;i++){
                    offAmazings_all.add( OffAmazings[i] );
                    allkala_adapter.notifyDataSetChanged ();
                }
            }
        };

        Response.ErrorListener listener_erorr =new Response.ErrorListener ( ) {
            @Override
            public void onErrorResponse ( VolleyError error ) {
                Toast.makeText ( getApplicationContext (), "no"+error.getMessage () , Toast.LENGTH_SHORT ).show ( );

            }
        };

        JsonArrayRequest request = new JsonArrayRequest ( Request.Method.GET,url,null,listener,listener_erorr );
        requestQueue.add ( request );
    }




}