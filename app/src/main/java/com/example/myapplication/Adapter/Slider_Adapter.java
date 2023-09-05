package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.myapplication.Model.Slider_bander;

import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Slider_Adapter extends PagerAdapter {
    Context context;
    List< Slider_bander > data;

    public Slider_Adapter ( Context context , List< Slider_bander > data ) {
        this.context = context;
        this.data = data;
    }




    @NonNull
    @Override
    public Object instantiateItem ( @NonNull ViewGroup container , int position ) {
        View view =LayoutInflater.from ( context ).inflate (R.layout.item_slider,container,false  );
        ImageView imageView =view.findViewById ( R.id.img_slider );
        Picasso.get ().load ( data.get ( position ).getImg_link () ).into ( imageView );

        view.setRotationY (-180);
        container.addView ( view );

        return view;
    }

    @Override
    public int getCount ( ) {
        return data.size ();
    }

    @Override
    public boolean isViewFromObject ( @NonNull View view , @NonNull Object object ) {
        return view==object;
    }

    @Override
    public void destroyItem ( @NonNull ViewGroup container , int position , @NonNull Object object ) {
        container.removeView ( (View)object );
    }
}
