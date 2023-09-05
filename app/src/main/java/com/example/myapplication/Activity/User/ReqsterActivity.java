package com.example.myapplication.Activity.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Api.Apiclient;
import com.example.myapplication.Api.MyProfileManger;
import com.example.myapplication.Api.User;
import com.example.myapplication.Api.UserData;
import com.example.myapplication.Api.apicoonect;
import com.example.myapplication.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReqsterActivity extends AppCompatActivity {
    ImageView imageView_back;
    EditText editText_name,editText_phon,editText_email,editText_password;
    Button button_regster;
    TextView textView_email,textView_phon,textView_click;
    apicoonect request;
    MyProfileManger myProfileManger ;
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_reqster );


        request = Apiclient.getapiclient ().create ( apicoonect.class );
        myProfileManger = new MyProfileManger ( ReqsterActivity.this);




        editText_name =findViewById ( R.id.name_regster );
        editText_email=findViewById ( R.id.email_regster );
        editText_phon =findViewById ( R.id.phon_regster );
        editText_password=findViewById ( R.id.password_regster );
        button_regster =findViewById ( R.id.button_regster );

        textView_click =findViewById ( R.id.text_regster);
        textView_click.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View view ) {
                startActivity (new Intent ( ReqsterActivity.this,LoginActivity.class ) );

            }
        } );




        button_regster.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                String phon      = editText_phon.getText ().toString ();
                String name      = editText_name.getText ().toString ();
                String email     = editText_email.getText ().toString ();
                String password  = editText_password.getText ().toString ();
                if (email.equals ( "" )||phon.equals ( "" )||password.equals ( "" )||phon.length ()>=12){
                    Toast.makeText ( ReqsterActivity.this , "اطلاعات کامل پر کنید" , Toast.LENGTH_SHORT ).show ( );
                }else{
                    setRegster(phon,name,email,password);
                }}

            private void setRegster ( String phon , String name , String email , String password ) {

                Call< User > userCall = request.sentRegster (phon ,name ,email , password  );
                userCall.enqueue ( new Callback< User > ( ) {
                    @Override
                    public void onResponse ( Call< User > call , Response< User > response ) {
                        if ( response.isSuccessful() && response.body ().isStatus ()){

                            Toast.makeText ( ReqsterActivity.this , response.body().getMassage()+"" , Toast.LENGTH_SHORT ).show ( );

                            UserData userData =response.body ().getUserData ();
                            myProfileManger.seveUserData ( userData );
                            startActivity(new Intent(ReqsterActivity.this,LoginActivity.class));
                            finish();

                        }else {

                            Toast.makeText ( ReqsterActivity.this , response.body ().getMassage () , Toast.LENGTH_SHORT ).show ( );
                        }


                    }

                    @Override
                    public void onFailure ( Call< User > call , Throwable t ) {
                        Toast.makeText ( ReqsterActivity.this , t.getMessage ()+"", Toast.LENGTH_SHORT ).show ( );
                        Log.d("erorr", t.getMessage () + "");
                    }

                });

            }
        });
    }
}