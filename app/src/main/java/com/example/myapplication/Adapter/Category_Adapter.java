package com.example.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Activity.DitelCategoryActivity;
import com.example.myapplication.Golobol.Key;
import com.example.myapplication.Model.List_Home_model;
import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Category_Adapter extends RecyclerView.Adapter< Category_Adapter.MyViewHolder> {
    Context context ;
    List < List_Home_model > data;

    public Category_Adapter ( Context context , List< List_Home_model > data ) {
        this.context = context;
        this.data = data;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder ( @NonNull ViewGroup parent , int viewType ) {
        View view = LayoutInflater.from ( context ).inflate ( R.layout.item_category,parent,false );
        return new MyViewHolder ( view );
    }

    @Override
    public void onBindViewHolder ( @NonNull MyViewHolder holder , int position ) {
        holder.textView.setText ( data.get ( position ).getName () );
        Picasso.get ().load ( data.get ( position ).getImg_link () ).into ( holder.imageView );
        holder.itemView.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View view ) {
                Intent intent =new Intent ( context, DitelCategoryActivity.class );
                intent.putExtra ( Key.id , data.get ( position ).getId ());
                intent.putExtra ( Key.name,data.get ( position ).getName () );
                context.startActivity ( intent );
            }
        } );



    }

    @Override
    public int getItemCount ( ) {
        return data.size ();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;


        public MyViewHolder ( @NonNull View itemView ) {
            super ( itemView );
            imageView =itemView.findViewById ( R.id.img_category );
            textView =itemView.findViewById ( R.id.text_catefoey );



        }
    }





}
