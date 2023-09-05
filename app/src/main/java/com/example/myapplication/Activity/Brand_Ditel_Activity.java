package com.example.myapplication.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Adapter.Ditel_Brand_Adapter;
import com.example.myapplication.Adapter.Ditel_Category_Adapter;
import com.example.myapplication.Golobol.Key;
import com.example.myapplication.Golobol.Link;
import com.example.myapplication.Model.Ditel_category;
import com.example.myapplication.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Brand_Ditel_Activity extends AppCompatActivity {
    RequestQueue requestQueue;
    Bundle bundle;
    String id,titel;
    TextView textView_titel;
    //brand
    List< Ditel_category > categoryList = new ArrayList<> (  );
    Ditel_Brand_Adapter ditel_brand_adapter;
    RecyclerView recyclerView_banrd;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_brand );
        requestQueue = Volley.newRequestQueue ( this );
        bundle =getIntent ().getExtras ();
        id = bundle.getString ( Key.id);
        titel = bundle.getString ( Key.name );
        Toast.makeText ( this , ""+id , Toast.LENGTH_SHORT ).show ( );
        textView_titel=findViewById ( R.id.text_diteli );
        textView_titel.setText ( titel );

        recyclerView_banrd =findViewById ( R.id.RecyclerView_Brand_ditel );
        recyclerView_banrd.setHasFixedSize ( true );
        recyclerView_banrd.setLayoutManager ( new LinearLayoutManager ( this ) );
        ditel_brand_adapter =new Ditel_Brand_Adapter ( this ,categoryList );
        recyclerView_banrd.setAdapter ( ditel_brand_adapter );
        String url = Link.BRAND_DITEL;
        Response.Listener< String > listener =new Response.Listener< String > ( ) {
            @Override
            public void onResponse ( String response ) {
                Gson gson = new Gson ();
                Ditel_category[]  ditel_categories = gson.fromJson (response. toString (),Ditel_category[].class);
                for (int i=0;i<ditel_categories.length;i++){
                    categoryList.add( ditel_categories[i] );
                    ditel_brand_adapter.notifyDataSetChanged ();
                }
            }
        };

        Response.ErrorListener listener_erorr =new Response.ErrorListener ( ) {
            @Override
            public void onErrorResponse ( VolleyError error ) {
                Toast.makeText ( getApplicationContext (), "no"+error.getMessage () , Toast.LENGTH_SHORT ).show ( );

            }
        };

        StringRequest request = new StringRequest ( Request.Method.POST,url,listener,listener_erorr ){
            @Nullable
            @Override
            protected Map< String, String > getParams ( ) throws AuthFailureError {
                HashMap<String , String > map = new HashMap<> (  );
                map.put ( Key.id,id );
                return map;
            }
        };
        requestQueue.add ( request );









    }
}