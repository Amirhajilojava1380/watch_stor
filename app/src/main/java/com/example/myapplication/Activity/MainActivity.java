package com.example.myapplication.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.myapplication.Activity.Uniti.NetworkChengeLisener;
import com.example.myapplication.Fragment.HomeFragment;
import com.example.myapplication.Fragment.ProfileFragment;
import com.example.myapplication.Fragment.SearchFragment;
import com.example.myapplication.Fragment.ListFragment;
import com.example.myapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    NetworkChengeLisener networkChengeLisener;


    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        networkChengeLisener =new NetworkChengeLisener ();



        bottomNavigationView =findViewById ( R.id.bottomnavigation );
        HomeFragment homeFragment  =new HomeFragment ();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
        fragmentTransaction.replace ( R.id.framelayout,homeFragment );
        fragmentTransaction.commit ();

        bottomNavigationView.setOnNavigationItemSelectedListener ( new BottomNavigationView.OnNavigationItemSelectedListener ( ) {
            @Override
            public boolean onNavigationItemSelected ( @NonNull MenuItem item ) {
                int id =item.getItemId ();

                switch ( id ){
                    case R.id.home:
                        HomeFragment homeFragment  =new HomeFragment ();
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
                        fragmentTransaction.replace ( R.id.framelayout,homeFragment );
                        fragmentTransaction.commit ();


                        break;
                    case R.id.List:
                        ListFragment listFragment  =new ListFragment ();
                        FragmentTransaction fragmentTransaction1 = getSupportFragmentManager ().beginTransaction ();
                        fragmentTransaction1.replace ( R.id.framelayout,listFragment );
                        fragmentTransaction1.commit ();
                        break;
                    case R.id.search:
                        SearchFragment searchFragment  =new SearchFragment ();
                        FragmentTransaction fragmentTransaction2 = getSupportFragmentManager ().beginTransaction ();
                        fragmentTransaction2.replace ( R.id.framelayout,searchFragment );
                        fragmentTransaction2.commit ();

                        break;
                    case R.id.profile:
                        ProfileFragment profileFragment  =new ProfileFragment ();
                        FragmentTransaction fragmentTransaction3 = getSupportFragmentManager ().beginTransaction ();
                        fragmentTransaction3.replace ( R.id.framelayout,profileFragment );
                        fragmentTransaction3.commit ();
                        break;
                    default:
                        throw new IllegalStateException ( "Unexpected value: " + id );
                }






                return true;
            }} );


    }

    @Override
    protected void onStart ( ) {
        IntentFilter filter =new IntentFilter ( ConnectivityManager.CONNECTIVITY_ACTION );
        registerReceiver ( networkChengeLisener,filter );
        super.onStart ( );
    }

    @Override
    protected void onStop ( ) {
        unregisterReceiver ( networkChengeLisener );
        super.onStop ( );
    }
}