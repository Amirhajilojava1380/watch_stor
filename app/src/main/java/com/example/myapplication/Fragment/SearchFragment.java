package com.example.myapplication.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Adapter.Allkala_Adapter;
import com.example.myapplication.Adapter.Search_Adapter;
import com.example.myapplication.Golobol.Link;
import com.example.myapplication.Model.OffAmazing;
import com.example.myapplication.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment {
    View view;
    RequestQueue requestQueue;
    EditText editText_search;
    RecyclerView recyclerView_search;
    List<OffAmazing> offAmazingList =new ArrayList<> (  );
    Search_Adapter search_adapter;
    ShimmerFrameLayout shimmerFrameLayout;
    @Override
    public View onCreateView ( LayoutInflater inflater , ViewGroup container ,
                               Bundle savedInstanceState ) {
        view=  inflater.inflate ( R.layout.fragment_search , container , false );
        requestQueue= Volley.newRequestQueue ( getContext () );
        editText_search=view.findViewById ( R.id.editText_search );
        recyclerView_search =view.findViewById ( R.id.RecyclerView_search );
        shimmerFrameLayout=view.findViewById ( R.id.shimmer_search );
        shimmerFrameLayout.startShimmer ();

        editText_search.addTextChangedListener ( new TextWatcher ( ) {
            @Override
            public void beforeTextChanged ( CharSequence constraint , int start , int count , int after ) {

            }

            @Override
            public void onTextChanged ( CharSequence constraint , int start , int before , int count ) {
                search_adapter.getFilter ().filter ( constraint );
            }

            @Override
            public void afterTextChanged ( Editable s ) {

            }
        } );

        getserch();
        return view;
    }

    private void getserch ( ) {
        recyclerView_search =view.findViewById ( R.id.RecyclerView_search );
        recyclerView_search.setHasFixedSize ( true );
        recyclerView_search.setLayoutManager ( new LinearLayoutManager ( getContext () ) );
        search_adapter =new Search_Adapter ( getContext (),offAmazingList );
        recyclerView_search.setAdapter ( search_adapter );

        String url = Link.SEARCH;
        Response.Listener< JSONArray > listener =new Response.Listener< JSONArray > ( ) {
            @Override
            public void onResponse ( JSONArray response ) {
                Gson gson = new Gson ();
                OffAmazing[]  OffAmazings = gson.fromJson (response. toString (),OffAmazing[].class);
                for (int i=0;i<OffAmazings.length;i++){
                    shimmerFrameLayout.stopShimmer ();
                    shimmerFrameLayout.setVisibility ( View.GONE );
                    recyclerView_search.setVisibility ( View.VISIBLE );
                    offAmazingList.add( OffAmazings[i] );
                    search_adapter.notifyDataSetChanged ();

                }
            }
        };

        Response.ErrorListener listener_erorr =new Response.ErrorListener ( ) {
            @Override
            public void onErrorResponse ( VolleyError error ) {
                Toast.makeText ( getContext (), "no"+error.getMessage () , Toast.LENGTH_SHORT ).show ( );

            }
        };

        JsonArrayRequest request = new JsonArrayRequest ( Request.Method.GET,url,null,listener,listener_erorr );
        requestQueue.add ( request );


    }
}