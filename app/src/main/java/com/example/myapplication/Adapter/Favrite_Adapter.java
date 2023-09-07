package com.example.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.text.SpannableString;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Activity.ShowDitel_CategoryActivity;
import com.example.myapplication.Api.Cart;
import com.example.myapplication.Golobol.FilterSearch;
import com.example.myapplication.Golobol.Key;
import com.example.myapplication.Model.OffAmazing;
import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class Favrite_Adapter extends RecyclerView.Adapter< Favrite_Adapter.MyViewHolder> {
    Context context ;
    List < Cart > data;


    public Favrite_Adapter ( Context context , List< Cart > data  ) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder ( @NonNull ViewGroup parent , int viewType ) {
        View view = LayoutInflater.from ( context ).inflate ( R.layout.item_faverite,parent,false );
        return new MyViewHolder ( view );
    }

    @Override
    public void onBindViewHolder ( @NonNull MyViewHolder holder , int position ) {
        Toast.makeText ( context , ""+data.get ( position ).getId_pr () , Toast.LENGTH_SHORT ).show ( );
        Picasso.get ().load ( data.get (position).getImg_link () ).into ( holder.imageView_off);
        holder.textView_name.setText ( data.get (position).getName () );
        String pric =data.get ( position ).getPrice ();
        String pric_off =data.get ( position ).getOffprice ();
        DecimalFormat decimalFormat = new DecimalFormat("###,###");

        if (pric.equals ( pric_off )  ){
            holder.textView_off.setVisibility ( View.GONE );
            String text_price_=decimalFormat.format(Integer.valueOf(data.get (position).getPrice ()));
            holder.textView_pric.setText ( text_price_+"  تومان  " );


        }else {

            holder.textView_off.setVisibility ( View.VISIBLE );



            SpannableString spannableString = new SpannableString(data.get (position).getPrice ());
            spannableString.setSpan(new StrikethroughSpan (),0,data.get (position).getPrice ().length(),spannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.textView_pric.setText(spannableString);


            String text_price_decmal=decimalFormat.format(Integer.valueOf(data.get (position).getOffprice ()));
            holder.textView_off.setText ( text_price_decmal+"  تومان  " );

        }


    }

    @Override
    public int getItemCount ( ) {
        return data.size ();
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView_off;
        TextView textView_pric,textView_off,textView_name;
        public MyViewHolder ( @NonNull View itemView ) {
            super ( itemView );
            imageView_off =itemView.findViewById ( R.id.img_search);
            textView_name =itemView.findViewById ( R.id.text_search_name );
            textView_pric =itemView.findViewById ( R.id.search_price );
            textView_off =itemView.findViewById ( R.id.search_price_off);

        }
    }





}
