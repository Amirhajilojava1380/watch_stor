package com.example.myapplication.Activity.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Activity.MainActivity;
import com.example.myapplication.Api.Apiclient;
import com.example.myapplication.Api.MyProfileManger;
import com.example.myapplication.Api.User;
import com.example.myapplication.Api.UserData;
import com.example.myapplication.Api.apicoonect;
import com.example.myapplication.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    apicoonect requset;
    MyProfileManger myProfileManger;
    EditText editText_phon,editText_password;
    Button button_login;
    TextView textView_go_Reqster;


    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_login );
        editText_phon =findViewById ( R.id.phon_Login );
        editText_password=findViewById ( R.id.password_Login );
        textView_go_Reqster=findViewById ( R.id.text_Login );
        button_login =findViewById ( R.id.button_Login );
        requset =Apiclient.getapiclient ().create ( apicoonect.class );
        myProfileManger =new MyProfileManger ( LoginActivity.this );

        textView_go_Reqster.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View view ) {
                startActivity ( new Intent ( LoginActivity.this,ReqsterActivity.class ) );
            }
        } );


        button_login.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View view ) {
                String phon  = editText_phon.getText ().toString ();
                String password =editText_password.getText ().toString ();
                if ( phon.equals ( "" )&&password.equals ( "" ) ){
                    Toast.makeText ( LoginActivity.this , "اطلاعات کامل پر کنید" , Toast.LENGTH_SHORT ).show ( );
                }else {
                    sendLogin(phon,password);
                }
            }

            private void sendLogin ( String phon , String password ) {
                Call< User > userCall_Login = requset.sentLogin ( phon,password );
                userCall_Login.enqueue ( new Callback< User > ( ) {
                    @Override
                    public void onResponse ( Call< User > call , Response< User > response ) {
                        if ( response.isSuccessful ()&&response.body ().isStatus () ){
                            UserData userData =response.body ().getUserData ();
                            myProfileManger.seveUserData ( userData );
                            Toast.makeText ( LoginActivity.this , response.body ().getMassage ()+"" , Toast.LENGTH_SHORT ).show ( );
                            if ( myProfileManger.islogin () ){
                                startActivity ( new Intent ( LoginActivity.this, MainActivity.class ) );
                                finish ();
                            }

                        }else {
                            Toast.makeText ( LoginActivity.this , response.body ().getMassage ()+"" , Toast.LENGTH_SHORT ).show ( );
                        }

                    }

                    @Override
                    public void onFailure ( Call< User > call , Throwable t ) {
                        Toast.makeText ( LoginActivity.this , t.getMessage ()+"" , Toast.LENGTH_SHORT ).show ( );
                    }
                } );







            }

        });



    }
}