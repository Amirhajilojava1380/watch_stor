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

import com.example.myapplication.Activity.ShowDitel_CategoryActivity;
import com.example.myapplication.Golobol.Key;
import com.example.myapplication.Model.Ditel_category;
import com.example.myapplication.Model.List_Home_model;
import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class Ditel_Category_Adapter extends RecyclerView.Adapter< Ditel_Category_Adapter.MyViewHolder> {
    Context context ;
    List < Ditel_category > data;

    public Ditel_Category_Adapter ( Context context , List< Ditel_category > data ) {
        this.context = context;
        this.data = data;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder ( @NonNull ViewGroup parent , int viewType ) {
        View view = LayoutInflater.from ( context ).inflate ( R.layout.item_ditel_category,parent,false );
        return new MyViewHolder ( view );
    }

    @Override
    public void onBindViewHolder ( @NonNull MyViewHolder holder , int position ) {
        holder.name.setText ( data.get ( position ).getName ());
        DecimalFormat decimalFormat =new DecimalFormat ( "###,###" );
        String decimalFormat_price=decimalFormat.format (Integer.valueOf (   data.get ( position ).getPrice ()) );
        holder.price.setText (  decimalFormat_price +" تومان ");
        Picasso.get ().load ( data.get ( position ).getImg_link () ).into ( holder.imageView );
        holder.itemView.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View view ) {
                Intent intent =new Intent ( context, ShowDitel_CategoryActivity.class );
                intent.putExtra ( Key.id,data.get ( position ).getId () );
                intent.putExtra ( Key.name,data.get ( position ).getName () );
                intent.putExtra ( Key.Img_link,data.get ( position ).getImg_link () );
                intent.putExtra ( Key.Price,data.get ( position ).getPrice () );
                intent.putExtra ( Key.Id_list,data.get ( position ).getId_list () );
                context.startActivity ( intent );
            }
        } );



    }

    @Override
    public int getItemCount ( ) {
        return data.size ();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,price;
        ImageView imageView;


        public MyViewHolder ( @NonNull View itemView ) {
            super ( itemView );
            imageView =itemView.findViewById ( R.id.img_ditel);
            name =itemView.findViewById ( R.id.text_ditel_name );
            price =itemView.findViewById ( R.id.diteli_price );


        }
    }





}
