package com.example.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Activity.Brand_Ditel_Activity;
import com.example.myapplication.Golobol.Key;
import com.example.myapplication.Model.Brand;
import com.example.myapplication.Model.Question;
import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Question_Adapter extends RecyclerView.Adapter< Question_Adapter.MyViewHolder> {
    Context context ;
    List < Question > data;

    public Question_Adapter ( Context context , List< Question > data ) {
        this.context = context;
        this.data = data;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder ( @NonNull ViewGroup parent , int viewType ) {
        View view = LayoutInflater.from ( context ).inflate ( R.layout.item_question,parent,false );
        return new MyViewHolder ( view );
    }

    @Override
    public void onBindViewHolder ( @NonNull MyViewHolder holder , int position ) {
        holder.textView.setText ( data.get ( position ).getTitel () );
        holder.textViewgone.setText ( data.get ( position ).getDescription ());

        holder.relativeLayout.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                if (holder.relativeLayout_gone.getVisibility ()==View.GONE  ){
                    holder.relativeLayout_gone.setVisibility ( View.VISIBLE );
                }else {
                    holder.relativeLayout_gone.setVisibility ( View.GONE );
                }

            }

        } );


    }

    @Override
    public int getItemCount ( ) {
        return data.size ();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView,textViewgone;
        RelativeLayout relativeLayout,relativeLayout_gone;
        public MyViewHolder ( @NonNull View itemView ) {
            super ( itemView );

            textView =itemView.findViewById ( R.id.text_visibel );
            textViewgone =itemView.findViewById ( R.id.text_gone );
            relativeLayout =itemView.findViewById ( R.id.relativeLayout );
            relativeLayout_gone =itemView.findViewById ( R.id.relativeLayout_gone );
        }
    }





}
