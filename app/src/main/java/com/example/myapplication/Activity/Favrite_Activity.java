package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.Adapter.Favrite_Adapter;
import com.example.myapplication.Api.Apiclient;
import com.example.myapplication.Api.Cart;
import com.example.myapplication.Api.MyProfileManger;
import com.example.myapplication.Api.apicoonect;
import com.example.myapplication.Model.OffAmazing;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Favrite_Activity extends AppCompatActivity {

    apicoonect request;
    MyProfileManger myProfileManger;
    String phon;
    //favrit
    List< Cart >cartList=new ArrayList<> (  );
    Favrite_Adapter favrite_adapter;
    RecyclerView recyclerView_favrit;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_favrite );
        request= Apiclient.getapiclient ().create ( apicoonect.class );
        myProfileManger=new MyProfileManger ( this );
        phon =myProfileManger.getUserData ().get ( MyProfileManger.PHON );
        recyclerView_favrit=findViewById ( R.id.RecyclerView_favrite );
        recyclerView_favrit.setHasFixedSize ( true );
        recyclerView_favrit.setLayoutManager ( new LinearLayoutManager ( this ) );


        Call<List<Cart>>listCall =request.getfaverit ( phon );
        listCall.enqueue ( new Callback< List< Cart > > ( ) {
            @Override
            public void onResponse ( Call< List< Cart > > call , Response< List< Cart > > response ) {
                Toast.makeText ( Favrite_Activity.this , "s" , Toast.LENGTH_SHORT ).show ( );
                cartList=response.body ();
                favrite_adapter=new Favrite_Adapter ( getApplicationContext (),cartList );
                recyclerView_favrit.setAdapter ( favrite_adapter );
                favrite_adapter.notifyDataSetChanged ();

            }

            @Override
            public void onFailure ( Call< List< Cart > > call , Throwable t ) {
                Toast.makeText ( Favrite_Activity.this , ""+t.getMessage () , Toast.LENGTH_SHORT ).show ( );
            }
        } );





    }
}