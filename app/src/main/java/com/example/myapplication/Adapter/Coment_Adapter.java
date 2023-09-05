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
import com.example.myapplication.Model.coment;
import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Coment_Adapter extends RecyclerView.Adapter< Coment_Adapter.MyViewHolder> {
    Context context ;
    List < coment > data;

    public Coment_Adapter ( Context context , List< coment > data ) {
        this.context = context;
        this.data = data;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder ( @NonNull ViewGroup parent , int viewType ) {
        View view = LayoutInflater.from ( context ).inflate ( R.layout.item_coment,parent,false );
        return new MyViewHolder ( view );
    }

    @Override
    public void onBindViewHolder ( @NonNull MyViewHolder holder , int position ) {
        holder.textView_email.setText ( data.get ( position ).getUser_email () );
        holder.textView_dicrption.setText ( data.get ( position ).getDecreption () );
        holder.textView_date.setText ( data.get ( position ).getData () );
        holder.textView_rite.setText ( data.get ( position ).getRating () );

        String rativ =data.get ( position ).getRating ();
        if (rativ.startsWith ( "1" )  ){holder.textView_rite.setBackgroundResource ( R.drawable.bg_rative_1 );}
        else if ( rativ.startsWith ( "2" ) ) {holder.textView_rite.setBackgroundResource ( R.drawable.bg_rative_2 );}
        else if ( rativ.startsWith ( "3" ) ) {holder.textView_rite.setBackgroundResource ( R.drawable.bg_rative_3);}
        else if ( rativ.startsWith ( "4" ) ) {holder.textView_rite.setBackgroundResource ( R.drawable.bg_rative_4);}
        else if ( rativ.startsWith ( "5" ) ) {holder.textView_rite.setBackgroundResource ( R.drawable.bg_rative_5); }
    }

    @Override
    public int getItemCount ( ) {
        return data.size ();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView_dicrption ,textView_date,textView_rite,textView_email;


        public MyViewHolder ( @NonNull View itemView ) {
            super ( itemView );
            textView_email=itemView.findViewById ( R.id.text_email_coment );
            textView_dicrption =itemView.findViewById ( R.id.text_direction_coment );
            textView_date=itemView.findViewById ( R.id.text_deta_coment );
            textView_rite=itemView.findViewById ( R.id.ritive );
        }
    }





}
