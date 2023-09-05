package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.Adapter.Pey_Off_Adapter;
import com.example.myapplication.Adapter.Shop_Adapter;
import com.example.myapplication.Api.Apiclient;
import com.example.myapplication.Api.Cart;
import com.example.myapplication.Api.MyProfileManger;
import com.example.myapplication.Api.apicoonect;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PeyOff_Activity extends AppCompatActivity {
    MyProfileManger myProfileManger;
    String phon ;
    apicoonect request;

    //
    RecyclerView recyclerView_pey;
    List< Cart> peylsit =new ArrayList<> (  );
    Pey_Off_Adapter pey_off_adapter;
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_peyoff );
        request = Apiclient.getapiclient ().create ( apicoonect.class );
        myProfileManger =new MyProfileManger ( this );
        phon =myProfileManger.getUserData ().get ( MyProfileManger.PHON );

        recyclerView_pey =findViewById ( R.id.RecyclerView_pey_off );
        recyclerView_pey.setHasFixedSize (true);
        recyclerView_pey.setLayoutManager ( new LinearLayoutManager ( this ) );
        Call<List<Cart>>call =request.getpeyoff ( phon );
        call.enqueue ( new Callback< List< Cart > > ( ) {
            @Override
            public void onResponse ( Call< List< Cart > > call , Response< List< Cart > > response ) {
                peylsit=  response.body ( ) ;
                pey_off_adapter =new Pey_Off_Adapter ( getApplicationContext (),  peylsit  );
                recyclerView_pey.setAdapter ( pey_off_adapter );

            }

            @Override
            public void onFailure ( Call< List< Cart > > call , Throwable t ) {
                Toast.makeText ( PeyOff_Activity.this , ""+t.getMessage () , Toast.LENGTH_SHORT ).show ( );
            }
        } );






    }
}