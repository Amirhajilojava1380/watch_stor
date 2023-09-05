package com.example.myapplication.Activity.Uniti;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;

import com.example.myapplication.R;

public class NetworkChengeLisener extends BroadcastReceiver {


    @Override
    public void onReceive ( Context context , Intent intent ) {
        if ( !Comen.isCooenctInternet ( context ) ) {
            AlertDialog.Builder builder = new AlertDialog.Builder ( context );
            View l_alerDialog = LayoutInflater.from ( context ).inflate ( R.layout.check_internet_dialog , null );
            builder.setView ( l_alerDialog );

            AppCompatButton buttonre = l_alerDialog.findViewById ( R.id.bt_re );


            AlertDialog dialog = builder.create ( );
            dialog.show ();
            dialog.setCancelable ( false );
            dialog.getWindow ( ).setGravity ( Gravity.CENTER );

            buttonre.setOnClickListener ( new View.OnClickListener ( ) {
                @Override
                public void onClick ( View v ) {
                    dialog.dismiss ( );
                    onReceive ( context , intent );


                }
            } );
        }
    }
}
