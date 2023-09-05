package com.example.myapplication.Activity.Uniti;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

public class Comen {

    public static boolean isCooenctInternet ( Context context ) {
        ConnectivityManager connectivityManager = ( ConnectivityManager )
                context.getSystemService ( context.CONNECTIVITY_SERVICE );

        if ( connectivityManager != null ) {
            NetworkInfo[] info = connectivityManager.getAllNetworkInfo ( );
            if ( info != null ) {

                for ( int i = 0 ; i < info.length ; i++ ) {
                    if ( info[ i ].getState ( ) == NetworkInfo.State.CONNECTED )
                        return true;
                }

            }
        }

        return false;
    }


}
