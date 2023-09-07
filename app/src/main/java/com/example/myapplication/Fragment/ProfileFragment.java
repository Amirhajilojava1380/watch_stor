package com.example.myapplication.Fragment;

import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication.Activity.Favrite_Activity;
import com.example.myapplication.Activity.MainActivity;
import com.example.myapplication.Activity.PeyOff_Activity;
import com.example.myapplication.Activity.QuestionActivity;
import com.example.myapplication.Activity.ShopActivity;
import com.example.myapplication.Activity.User.LoginActivity;
import com.example.myapplication.Activity.User.ReqsterActivity;
import com.example.myapplication.Adapter.Pey_Off_Adapter;
import com.example.myapplication.Api.MyProfileManger;
import com.example.myapplication.R;
import com.facebook.shimmer.ShimmerFrameLayout;

public class ProfileFragment extends Fragment {

    View view;

    MyProfileManger myProfileManger;
    CardView cardView1,cardView2,cardView3,cardView4,cardView5,cardView6;
    TextView textView_email,textView_phon,textView_open_exit;
    RelativeLayout relativeLayout;

    @Override
    public View onCreateView ( LayoutInflater inflater , ViewGroup container ,
                               Bundle savedInstanceState ) {
        view=   inflater.inflate ( R.layout.fragment_profile , container , false );
        myProfileManger =new MyProfileManger ( getContext () );
        textView_open_exit=view.findViewById ( R.id.open_exit );
        textView_email =view.findViewById ( R.id.email_show );
        textView_phon =view.findViewById ( R.id.phon_shwo );
        cardView5=view.findViewById ( R.id.cart5 );
        textView_email.setText ( myProfileManger.getUserData().get (MyProfileManger.EMAILE) );
        textView_phon.setText ( myProfileManger.getUserData ().get ( MyProfileManger.PHON ) );

        //shop
        cardView1 =view.findViewById ( R.id.cart1 );
        cardView1.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                if ( myProfileManger.islogin () ){
                    Intent intent = new Intent ( getContext (), ShopActivity.class );
                    startActivity ( intent );
                }else {
                    startActivity ( new Intent ( getContext (), LoginActivity.class ) );
                }

            }
        } );
        //peyoff
        cardView2 =view.findViewById ( R.id.cart2 );
        cardView2.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                if ( myProfileManger.islogin () ){
                    Intent intent =new Intent ( getContext (), PeyOff_Activity.class );
                    startActivity ( intent );
                }else {
                    startActivity ( new Intent ( getContext (), LoginActivity.class ) );
                }
            }
        } );

        cardView3=view.findViewById ( R.id.cart3 );
        cardView3.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                startActivity ( new Intent ( getContext (), QuestionActivity.class ) );
            }
        } );





        //regster
        cardView4 =view.findViewById ( R.id.cart4 );
        cardView4.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                Intent intent = new Intent ( getContext (), ReqsterActivity.class );
                startActivity ( intent );
            }});
        if ( myProfileManger.islogin () ){
            cardView4.setVisibility ( View.GONE );
            cardView5.setVisibility ( View.VISIBLE );
        }else {
            cardView4.setVisibility ( View.VISIBLE );
            cardView5.setVisibility ( View.GONE );
        }

        if ( cardView5.getVisibility () ==View.VISIBLE ){
            cardView5.setOnClickListener ( new View.OnClickListener ( ) {
                @Override
                public void onClick ( View view ) {
                    myProfileManger.Loguot ();
                  Intent intent   =new Intent ( getContext (), MainActivity.class ) ;
                  getActivity().onBackPressed();
                  startActivity ( intent );


                }
            } );





        }else {
            myProfileManger.islogin ();

        }

        cardView6 =view.findViewById ( R.id.cart6 );
        cardView6.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                if ( myProfileManger.islogin ( ) ){
                    startActivity ( new Intent ( getContext (), Favrite_Activity.class ) );



                }else {startActivity ( new Intent ( getContext (),LoginActivity.class ) );
                }


            }
        } );



        return view;

    }
}