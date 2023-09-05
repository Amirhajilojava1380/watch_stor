package com.example.myapplication.Adapter;

import static com.example.myapplication.Golobol.Key.Id_list;
import static com.example.myapplication.Golobol.Key.Img_link;
import static com.example.myapplication.Golobol.Key.Price;
import static com.example.myapplication.Golobol.Key.id;
import static com.example.myapplication.Golobol.Key.name;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Activity.Coment_send_Activity;
import com.example.myapplication.Activity.ShowDitel_CategoryActivity;
import com.example.myapplication.Golobol.Key;
import com.example.myapplication.Model.List_Home_model;
import com.example.myapplication.Model.OffAmazing;
import com.example.myapplication.Model.Similar;
import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class Similar_Adapter extends RecyclerView.Adapter< Similar_Adapter.MyViewHolder> {
    Context context ;
    List < Similar > data;

    public Similar_Adapter ( Context context , List< Similar > data ) {
        this.context = context;
        this.data = data;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder ( @NonNull ViewGroup parent , int viewType ) {
        View view = LayoutInflater.from ( context ).inflate ( R.layout.item_similar,parent,false );
        return new MyViewHolder ( view );
    }

    @Override
    public void onBindViewHolder ( @NonNull MyViewHolder holder , int position ) {
        holder.textView_name.setText ( data.get ( position ).getName () );
        Picasso.get ().load ( data.get ( position ).getImg_link () ).into ( holder.imageView );
        DecimalFormat decimalFormat =new DecimalFormat ( "###,###" );
        String decimalFormat_price=decimalFormat.format (Integer.valueOf (   data.get ( position ).getPrice ()) );
        holder.textView_price.setText ( decimalFormat_price + " تومان " );
        holder.itemView.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                Intent intent =new Intent ( context, ShowDitel_CategoryActivity.class );
                intent.putExtra ( Key.id, data.get ( position ).getId ());
                intent.putExtra ( Key.name,data.get ( position ).getName ());
                intent.putExtra ( Key.Img_link, data.get ( position ).getImg_link ());
                intent.putExtra ( Key.Id_list,data.get ( position ).getId_list () );
                intent.putExtra ( Key.Price,data.get ( position ).getPrice () );
                context.startActivity ( intent );
            }
        } );




    }

    @Override
    public int getItemCount ( ) {
        return data.size ();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView_name,textView_price;
        ImageView imageView;

        public MyViewHolder ( @NonNull View itemView ) {
            super ( itemView );
            imageView =itemView.findViewById ( R.id.ImageView_similar );
            textView_name =itemView.findViewById ( R.id.text_name_similar );
            textView_price=itemView.findViewById ( R.id.text_pric_similar_off );


        }
    }





}
