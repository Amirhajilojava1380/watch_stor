package com.example.myapplication.Fragment;

import static android.os.SystemClock.sleep;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Activity.All_kala_Activity;
import com.example.myapplication.Adapter.Allkala_Adapter;
import com.example.myapplication.Adapter.AmazingOFF_Adapter;
import com.example.myapplication.Adapter.Brand_Adapter;
import com.example.myapplication.Adapter.List_Home_Adapter;
import com.example.myapplication.Adapter.Slider_Adapter;
import com.example.myapplication.Golobol.Link;
import com.example.myapplication.Model.Amazing;
import com.example.myapplication.Model.Brand;
import com.example.myapplication.Model.FrstAmazing;
import com.example.myapplication.Model.List_Home_model;
import com.example.myapplication.Model.OffAmazing;
import com.example.myapplication.Model.Slider_bander;
import com.example.myapplication.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    ViewPager viewPager;
    TabLayout tabLayout;
    List< Slider_bander > slider_banderList = new ArrayList<> ();
    Slider_Adapter slider_adapter;

    //list
    RecyclerView recyclerView_list;
    List< List_Home_model > list_home_models =new ArrayList<> ();
    List_Home_Adapter list_home_adapter;
    RequestQueue requestQueue;
    //amazing off
    RecyclerView recyclerView_amazing;
    List< Amazing > amazings =new ArrayList<> ();
    AmazingOFF_Adapter amazingOFF_adapter;
    //allkala
    RecyclerView recyclerView_allkala;
    List< OffAmazing > allkala =new ArrayList<> ();
    Allkala_Adapter allkala_adapter;
    TextView textView_text_kala;
    //brand
    RecyclerView recyclerView_brand;
    List< Brand > brandList =new ArrayList<> ();
    Brand_Adapter brand_adapter;
    View view;
    ShimmerFrameLayout shimmerFrameLayout;
    ScrollView scrollView;
    @Override
    public View onCreateView ( LayoutInflater inflater , ViewGroup container ,
                               Bundle savedInstanceState ) {
       view = inflater.inflate ( R.layout.fragment_home , container , false );

        requestQueue = Volley.newRequestQueue ( getContext () );
        shimmerFrameLayout=view.findViewById ( R.id.ShimmerFrameLayout );
        scrollView=view.findViewById ( R.id.ScrollView );
        shimmerFrameLayout.startShimmer ();
        textView_text_kala=view.findViewById ( R.id.text_kala );
      



        //بنر
        getSlider_bande();
        //لیست
        getlist_home();
        //amazin off
        getamazigoff();
        //allkala
        getallkala();
        //brand
        getbrand();


        return view;
    }

    private void getbrand ( ) {

        recyclerView_brand =view.findViewById ( R.id.RecyclerView_brand );
        recyclerView_brand.setHasFixedSize ( true );
        recyclerView_brand.setLayoutManager ( new GridLayoutManager ( getContext (),1 ) );
        brand_adapter =new Brand_Adapter ( getContext (),brandList );
        recyclerView_brand.setAdapter ( brand_adapter );

        String url = Link.BRAND;
        Response.Listener< JSONArray > listener =new Response.Listener< JSONArray > ( ) {
            @Override
            public void onResponse ( JSONArray response ) {
                Gson gson = new Gson ();
                Brand[]  brands = gson.fromJson (response. toString (),Brand[].class);
                for (int i=0;i<brands.length;i++){
                    brandList.add( brands[i] );
                    brand_adapter.notifyDataSetChanged ();
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

    private void getallkala ( ) {
        textView_text_kala.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {

                startActivity ( new Intent ( getContext (), All_kala_Activity.class ) );
            }
        } );

        recyclerView_allkala =view.findViewById ( R.id.RecyclerView_all_kala );
        recyclerView_allkala.setHasFixedSize ( true );
        recyclerView_allkala.setLayoutManager ( new GridLayoutManager ( getContext (),2 ) );
        allkala_adapter =new Allkala_Adapter ( getContext (),allkala );
        recyclerView_allkala.setAdapter ( allkala_adapter );

        String url = Link.ALL_KALA;
        Response.Listener< JSONArray > listener =new Response.Listener< JSONArray > ( ) {
            @Override
            public void onResponse ( JSONArray response ) {
                Gson gson = new Gson ();
                OffAmazing[]  OffAmazings = gson.fromJson (response. toString (),OffAmazing[].class);
                for (int i=0;i<OffAmazings.length;i++){
                    allkala.add( OffAmazings[i] );
                    allkala_adapter.notifyDataSetChanged ();
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

    private void getamazigoff ( ) {
        recyclerView_amazing =view.findViewById ( R.id.RecyclerView_offer );
        recyclerView_amazing.setHasFixedSize ( true );
        recyclerView_amazing.setLayoutManager ( new LinearLayoutManager ( getContext (), RecyclerView.HORIZONTAL,false ) );
        FrstAmazing frstAmazing = new FrstAmazing ("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSuVcLhYarxFRA9JYaij7HBFbvGeasR1JXmrA&usqp=CAU" ,"https://www.digikala.com/statics/img/png/specialCarousel/Electronics.webp" );
        amazings.add ( new Amazing ( frstAmazing,1 ) );
        amazingOFF_adapter =new AmazingOFF_Adapter ( getContext (),amazings );
        recyclerView_amazing.setAdapter ( amazingOFF_adapter );




        String url = Link.AMAZING_OFFER;
        Response.Listener< JSONArray > listener =new Response.Listener< JSONArray > ( ) {
            @Override
            public void onResponse ( JSONArray response ) {
                Gson gson = new Gson ();
                OffAmazing[]  offAmazings = gson.fromJson (response. toString (),OffAmazing[].class);
                for (int i=0;i<offAmazings.length;i++){
                    amazings.add(new Amazing ( offAmazings[i] ,0 ) );
                    amazingOFF_adapter.notifyDataSetChanged ();
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

    private void getlist_home ( ) {

        recyclerView_list =view.findViewById ( R.id.RecyclerView_list );
        recyclerView_list.setHasFixedSize ( true );
        recyclerView_list.setLayoutManager ( new GridLayoutManager ( getContext (),3 ) );
        list_home_adapter =new List_Home_Adapter ( getContext (),list_home_models );
        recyclerView_list.setAdapter ( list_home_adapter );
        String url = Link.LIST_HOEM;
        Response.Listener< JSONArray > listener =new Response.Listener< JSONArray > ( ) {
            @Override
            public void onResponse ( JSONArray response ) {
                Gson gson = new Gson ();
                List_Home_model[]  list_home_models1 = gson.fromJson (response. toString (),List_Home_model[].class);
                for (int i=0;i<list_home_models1.length;i++){
                    shimmerFrameLayout.stopShimmer ();
                    shimmerFrameLayout.setVisibility ( View.GONE );
                    scrollView.setVisibility ( View.VISIBLE );
                    list_home_models.add( list_home_models1[i] );
                    list_home_adapter.notifyDataSetChanged ();
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

    private void getSlider_bande ( ) {
        viewPager =view.findViewById ( R.id.ViewPager );
        tabLayout =view.findViewById ( R.id.TabLayout );
        slider_adapter =new Slider_Adapter (getContext (),slider_banderList);
        viewPager.setRotationY ( 180 );
        tabLayout.setupWithViewPager ( viewPager,true );

        viewPager.setAdapter ( slider_adapter );

        ///انیمیشن
        final int paddingPx = 150;
        final float MIN_SCALE = 0.9f;
        final float MAX_SCALE = 1f;
        viewPager.setClipToPadding(false);
        viewPager.setPadding(paddingPx, 0, paddingPx, 0);
        ViewPager.PageTransformer transformer = null;
        viewPager.setPageTransformer(false, transformer);
        transformer = new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                float pagerWidthPx = ((ViewPager) page.getParent()).getWidth();
                float pageWidthPx = pagerWidthPx - 2 * paddingPx;
                float maxVisiblePages = pagerWidthPx / pageWidthPx;
                float center = maxVisiblePages / 2f;
                float scale;
                if (position + 0.5f < center - 0.5f || position > center) {
                    scale = MIN_SCALE;
                } else {
                    float coef;
                    if (position + 0.5f < center) {
                        coef = (position + 1 - center) / 0.5f;
                    } else {
                        coef = (center - position) / 0.5f;
                    }
                    scale = coef * (MAX_SCALE - MIN_SCALE) + MIN_SCALE;
                }
                page.setScaleX(scale);
                page.setScaleY(scale);
            }
        };
        viewPager.setPageTransformer(false,transformer);

        String url = Link.SLIDER_BANER;
        Response.Listener< JSONArray > listener =new Response.Listener< JSONArray > ( ) {
            @Override
            public void onResponse ( JSONArray response ) {
                Gson gson = new Gson ();
                Slider_bander[]  slider_bander = gson.fromJson (response. toString (),Slider_bander[].class);
                for (int i=0;i<slider_bander.length;i++){
                    slider_banderList.add ( slider_bander[i] );
                    slider_adapter.notifyDataSetChanged ();
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


        final  boolean runing_thread =true;
        Thread thread = new Thread(){
            @Override
            public void run() {
                while (runing_thread){
                    try {
                        sleep(4000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    //برای ارور لودینگ سریع بنر و 4 ثانیه
                    if (getActivity()==null)
                        return;
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(viewPager.getCurrentItem()<slider_banderList.size()-1){
                                viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
                            }else {
                                viewPager.setCurrentItem(0);
                            }
                        }
                    });
                }
            }
        };
        thread.start();







    }




}