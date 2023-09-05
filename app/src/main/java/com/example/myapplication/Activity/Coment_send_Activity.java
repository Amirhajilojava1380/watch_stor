package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRatingBar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Activity.User.LoginActivity;
import com.example.myapplication.Api.Apiclient;
import com.example.myapplication.Api.Coment_Messeag;
import com.example.myapplication.Api.MyProfileManger;
import com.example.myapplication.Api.apicoonect;
import com.example.myapplication.Golobol.Key;
import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import saman.zamani.persiandate.PersianDate;
import saman.zamani.persiandate.PersianDateFormat;

public class Coment_send_Activity extends AppCompatActivity {
    apicoonect request;
    MyProfileManger myProfileManger;
    Bundle bundle;
    static   String id , Id_list,titel,img,pr,email,date,rating_string;
    TextView textView_name,textView_price;
    ImageView imageView;

    Button button_send;
    AppCompatRatingBar ratingBar;

    EditText direction_edit;



    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activitycomentsend );
        request = Apiclient.getapiclient ().create ( apicoonect.class );

        myProfileManger =new MyProfileManger ( this );
        email =myProfileManger.getUserData ().get ( MyProfileManger.EMAILE );

        bundle =getIntent ().getExtras ();
        id =bundle.getString ( Key.id );
        Id_list=bundle.getString ( Key.Id_list );
        titel =bundle.getString ( Key.name );
        img=bundle.getString ( Key.Img_link );
        pr=bundle.getString ( Key.Price );

        textView_name=findViewById ( R.id.text_titel_send );
        textView_name.setText ( titel );

        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        String text_price_decmal=decimalFormat.format(Integer.valueOf(pr));
        textView_price=findViewById ( R.id.text_price_send );
        textView_price.setText ( text_price_decmal +" تومان ");

        imageView =findViewById ( R.id.img_send );
        Picasso.get ().load ( img ).into ( imageView );

        //send coment
        PersianDate pdate = new PersianDate();
        PersianDateFormat pdformater1 = new PersianDateFormat("Y/m/d");
        date = pdformater1.format(pdate);


        direction_edit=findViewById ( R.id.decreption );

        ratingBar=findViewById ( R.id.rating );
        ratingBar.setOnRatingBarChangeListener ( new RatingBar.OnRatingBarChangeListener ( ) {
            @Override
            public void onRatingChanged ( RatingBar ratingBar , float rating , boolean fromUser ) {
                rating_string=String.valueOf ( rating );
            }
        } );

        button_send =findViewById ( R.id.button_send_coment );
        button_send.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                if ( myProfileManger.islogin () ){
                    String direction = direction_edit.getText ().toString ();
                    sendComent(id,email,direction,date,rating_string);
                }else {
                    Toast.makeText ( Coment_send_Activity.this , " لطفا وارد حساب خود شوید" , Toast.LENGTH_SHORT ).show ( );
                    startActivity ( new Intent ( Coment_send_Activity.this, LoginActivity.class ) );
                    finish ();
                }


            }
        } );




    }

    private void sendComent ( String id , String email , String direction , String date ,String rating_string ) {
        Call< Coment_Messeag > coment_messeagCall=request.sentComent ( id,email,direction,date,rating_string );
        coment_messeagCall.enqueue ( new Callback< Coment_Messeag > ( ) {
            @Override
            public void onResponse ( Call< Coment_Messeag > call , Response< Coment_Messeag > response ) {
                if ( response.isSuccessful ()&&response.body ().isStatus () ){
                    Toast.makeText ( Coment_send_Activity.this , ""+response.body ().getMassage () , Toast.LENGTH_SHORT ).show ( );
                    startActivity ( new Intent ( Coment_send_Activity.this,ShowDitel_CategoryActivity.class ) );
                }



            }

            @Override
            public void onFailure ( Call< Coment_Messeag > call , Throwable t ) {

                Toast.makeText ( Coment_send_Activity.this , "no" , Toast.LENGTH_SHORT ).show ( );

            }
        } );







    }
}