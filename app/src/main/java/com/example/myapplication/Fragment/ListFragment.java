package com.example.myapplication.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Adapter.Category_Adapter;
import com.example.myapplication.Adapter.List_Home_Adapter;
import com.example.myapplication.Golobol.Link;
import com.example.myapplication.Model.List_Home_model;
import com.example.myapplication.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;


public class ListFragment extends Fragment {
    View view;
    //list
    RecyclerView recyclerView_category;
    List< List_Home_model > list_category =new ArrayList<> ();
    Category_Adapter category_adapter;
    RequestQueue requestQueue;
    ShimmerFrameLayout shimmerFrameLayout;

    @Override
    public View onCreateView ( LayoutInflater inflater , ViewGroup container ,
                               Bundle savedInstanceState ) {
        view= inflater.inflate ( R.layout.fragment_list , container , false );
        requestQueue = Volley.newRequestQueue ( getContext () );
        recyclerView_category =view.findViewById ( R.id.RecyclerView_category);
        shimmerFrameLayout=view.findViewById ( R.id.shimmerFrameLayout_list );
        shimmerFrameLayout.startShimmer ();
        recyclerView_category.setHasFixedSize ( true );
        recyclerView_category.setLayoutManager ( new LinearLayoutManager ( getContext () ) );
        category_adapter =new Category_Adapter ( getContext (),list_category );
        recyclerView_category.setAdapter ( category_adapter );
        String url = Link.LIST_CATEGORY;
        Response.Listener< JSONArray > listener =new Response.Listener< JSONArray > ( ) {
            @Override
            public void onResponse ( JSONArray response ) {
                Gson gson = new Gson ();
                List_Home_model[]  list_home_models1 = gson.fromJson (response. toString (),List_Home_model[].class);
                for (int i=0;i<list_home_models1.length;i++){
                    shimmerFrameLayout.stopShimmer ();
                    shimmerFrameLayout.setVisibility ( View.GONE );
                    recyclerView_category.setVisibility ( View.VISIBLE );
                    list_category.add( list_home_models1[i] );
                    category_adapter.notifyDataSetChanged ();


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




        return view;


    }
}