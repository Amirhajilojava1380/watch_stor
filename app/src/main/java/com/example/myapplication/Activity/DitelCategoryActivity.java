package com.example.myapplication.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Adapter.Ditel_Category_Adapter;
import com.example.myapplication.Adapter.List_Home_Adapter;
import com.example.myapplication.Golobol.Key;
import com.example.myapplication.Golobol.Link;
import com.example.myapplication.Model.Ditel_category;
import com.example.myapplication.Model.List_Home_model;
import com.example.myapplication.R;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DitelCategoryActivity extends AppCompatActivity {
    RequestQueue requestQueue;
    Bundle bundle;
    String id , name ;
    TextView titel ;
    //ditel
    List< Ditel_category > ditel_categoryList =new ArrayList<> (  );
    RecyclerView recyclerView_ditel;
    Ditel_Category_Adapter ditelCategoryAdapter;
    //list
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_ditel_category );
        requestQueue = Volley.newRequestQueue ( this );
        bundle = getIntent ().getExtras ();
        id =bundle.getString ( Key.id );
        name =bundle.getString ( Key.name );
        titel = findViewById ( R.id.text_diteli );
        titel.setText ( name );


        recyclerView_ditel =findViewById ( R.id.RecyclerView_ditelcategori );
        recyclerView_ditel.setHasFixedSize ( true );
        recyclerView_ditel.setLayoutManager ( new LinearLayoutManager ( this ) );
        ditelCategoryAdapter =new Ditel_Category_Adapter ( this ,ditel_categoryList );
        recyclerView_ditel.setAdapter ( ditelCategoryAdapter );
        String url = Link.DITEL_CATEGORY;
        Response.Listener< String > listener =new Response.Listener< String > ( ) {
            @Override
            public void onResponse ( String response ) {
                Gson gson = new Gson ();
                Ditel_category[]  ditel_categories = gson.fromJson (response. toString (),Ditel_category[].class);
                for (int i=0;i<ditel_categories.length;i++){
                    ditel_categoryList.add( ditel_categories[i] );
                    ditelCategoryAdapter.notifyDataSetChanged ();
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