package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Adapter.Brand_Adapter;
import com.example.myapplication.Adapter.Question_Adapter;
import com.example.myapplication.Adapter.Shop_Adapter;
import com.example.myapplication.Api.Cart;
import com.example.myapplication.Golobol.Link;
import com.example.myapplication.Model.Brand;
import com.example.myapplication.Model.Question;
import com.example.myapplication.R;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity {
    RecyclerView recyclerView_q;
    List< Question   > qList =new ArrayList<> (  );
    Question_Adapter question_adapter;

    RequestQueue requestQueue;
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_question );
        requestQueue = Volley.newRequestQueue ( this );
        recyclerView_q =findViewById ( R.id.RecyclerView_question );
        recyclerView_q.setHasFixedSize ( true );
        recyclerView_q.setLayoutManager ( new LinearLayoutManager ( this ) );
        question_adapter =new Question_Adapter ( this,qList );
        recyclerView_q.setAdapter ( question_adapter );

        String url = Link.QUESTION;
        Response.Listener< JSONArray > listener =new Response.Listener< JSONArray > ( ) {
            @Override
            public void onResponse ( JSONArray response ) {
                Gson gson = new Gson ();
                Question[]  Q = gson.fromJson (response. toString (),Question[].class);
                for (int i=0;i<Q.length;i++){
                    qList.add( Q[i] );
                    question_adapter.notifyDataSetChanged ();
                }
            }
        };

        Response.ErrorListener listener_erorr =new Response.ErrorListener ( ) {
            @Override
            public void onErrorResponse ( VolleyError error ) {
                Toast.makeText ( getApplicationContext (), "no"+error.getMessage () , Toast.LENGTH_SHORT ).show ( );

            }
        };

        JsonArrayRequest request = new JsonArrayRequest ( Request.Method.GET,url,null,listener,listener_erorr );
        requestQueue.add ( request );








    }
}