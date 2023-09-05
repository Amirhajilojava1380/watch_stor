package com.example.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.text.SpannableString;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Activity.ShowDitel_CategoryActivity;
import com.example.myapplication.Golobol.Key;
import com.example.myapplication.Model.OffAmazing;
import com.example.myapplication.R;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class Allkala_Adapter extends RecyclerView.Adapter< Allkala_Adapter.MyViewHolder> {
    Context context ;
    List < OffAmazing > data;

    public Allkala_Adapter ( Context context , List< OffAmazing > data ) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder ( @NonNull ViewGroup parent , int viewType ) {
        View view = LayoutInflater.from ( context ).inflate ( R.layout.item_all_kala,parent,false );
        return new MyViewHolder ( view );
    }

    @Override
    public void onBindViewHolder ( @NonNull MyViewHolder holder , int position ) {
        Picasso.get ().load ( data.get (position).getImg_link () ).into ( holder.imageView_off);
        holder.textView_name.setText ( data.get (position).getName () );
        String pric =data.get ( position ).getPrice ();
        String pric_off =data.get ( position ).getOffprice ();
        DecimalFormat decimalFormat = new DecimalFormat("###,###");

        if (pric.equals ( pric_off )  ){
            holder.itemView.setOnClickListener ( new View.OnClickListener ( ) {
                @Override
                public void onClick ( View view ) {
                    Intent intent =new Intent ( context, ShowDitel_CategoryActivity.class );
                    intent.putExtra ( Key.id, data.get ( position ).getId () );
                    intent.putExtra ( Key.name,  data.get ( position ).getName ());
                    intent.putExtra ( Key.Img_link,data.get ( position ).getImg_link () );
                    intent.putExtra ( Key.Price,data.get ( position ).getPrice ());
                    intent.putExtra ( Key.Id_list,data.get ( position ).getId_list ());
                    context.startActivity ( intent );
                }
            } );
            holder.textView_valuoff.setVisibility ( View.GONE );
            holder.textView_off.setVisibility ( View.GONE );
            String text_price_=decimalFormat.format(Integer.valueOf(data.get (position).getPrice ()));

            holder.textView_pric.setText ( text_price_+"  تومان  " );

        }else {
            holder.itemView.setOnClickListener ( new View.OnClickListener ( ) {
                @Override
                public void onClick ( View view ) {
                    Intent intent =new Intent ( context, ShowDitel_CategoryActivity.class );
                    intent.putExtra ( Key.id, data.get ( position ).getId () );
                    intent.putExtra ( Key.name,  data.get ( position ).getName ());
                    intent.putExtra ( Key.Img_link,data.get ( position ).getImg_link () );
                    intent.putExtra ( Key.Price,data.get ( position ).getOffprice ());
                    intent.putExtra ( Key.Id_list,data.get ( position ).getId_list ());
                    context.startActivity ( intent );
                }
            } );






            holder.textView_valuoff.setVisibility ( View.VISIBLE );
            holder.textView_off.setVisibility ( View.VISIBLE );

            holder.textView_valuoff.setText ( data.get (position).getValue_off ()+"%");

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
        TextView textView_pric,textView_off,textView_name,textView_valuoff;
        public MyViewHolder ( @NonNull View itemView ) {
            super ( itemView );
            imageView_off =itemView.findViewById ( R.id.ImageView_kala);
            textView_name =itemView.findViewById ( R.id.text_name_kala );
            textView_pric =itemView.findViewById ( R.id.text_pric_kala );
            textView_off =itemView.findViewById ( R.id.text_pric_kala_off);
            textView_valuoff =itemView.findViewById ( R.id.valeu_kala );


        }
    }





}
