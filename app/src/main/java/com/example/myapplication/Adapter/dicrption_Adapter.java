package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.List_Home_model;
import com.example.myapplication.Model.dickrption;
import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class dicrption_Adapter extends RecyclerView.Adapter< dicrption_Adapter.MyViewHolder> {
    Context context ;
    List < dickrption > data;

    public dicrption_Adapter ( Context context , List< dickrption > data ) {
        this.context = context;
        this.data = data;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder ( @NonNull ViewGroup parent , int viewType ) {
        View view = LayoutInflater.from ( context ).inflate ( R.layout.item_description,parent,false );
        return new MyViewHolder ( view );
    }

    @Override
    public void onBindViewHolder ( @NonNull MyViewHolder holder , int position ) {
        holder.textView.setText ( data.get ( position ).getDescription () );
    }

    @Override
    public int getItemCount ( ) {
        return data.size ();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView;


        public MyViewHolder ( @NonNull View itemView ) {
            super ( itemView );
            textView =itemView.findViewById ( R.id.text_description);



        }
    }





}
