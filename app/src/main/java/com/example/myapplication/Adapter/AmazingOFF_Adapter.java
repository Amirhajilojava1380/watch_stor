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
import com.example.myapplication.Model.Amazing;
import com.example.myapplication.Model.FrstAmazing;
import com.example.myapplication.Model.OffAmazing;
import com.example.myapplication.R;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class AmazingOFF_Adapter extends RecyclerView.Adapter< RecyclerView.ViewHolder> {
    Context context;
    List< Amazing > data ;

    public AmazingOFF_Adapter ( Context context , List< Amazing > data ) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder ( @NonNull ViewGroup parent , int viewType ) {
        if (viewType == 0 ){
            View view = LayoutInflater.from ( context ).inflate ( R.layout.item_amazing_offer,parent,false );
            return new Amazing_Offer ( view );
        }else {
            View view = LayoutInflater.from ( context ).inflate ( R.layout.item_amazing_frst,parent,false );
            return new Amazing_Frsit2 ( view );
        }

    }

    @Override
    public void onBindViewHolder ( @NonNull RecyclerView.ViewHolder holder , int position ) {
        if ( getItemViewType ( position )==0 ){
            OffAmazing offAmazing =(OffAmazing)data.get ( position ).getObject ();
            ((Amazing_Offer)holder).setAmazingOff ( offAmazing );

            holder.itemView.setOnClickListener ( new View.OnClickListener ( ) {
                @Override
                public void onClick ( View view ) {
                    Intent intent =new Intent ( context, ShowDitel_CategoryActivity.class );
                    intent.putExtra ( Key.id,( ( OffAmazing ) data.get ( position ).getObject () ).getId () );
                    intent.putExtra ( Key.name,( ( OffAmazing ) data.get ( position ).getObject () ).getName ());
                    intent.putExtra ( Key.Img_link,( ( OffAmazing ) data.get ( position ).getObject () ).getImg_link () );
                    intent.putExtra ( Key.Price,( ( OffAmazing ) data.get ( position ).getObject () ).getOffprice ());
                    intent.putExtra ( Key.Id_list,( ( OffAmazing ) data.get ( position ).getObject () ).getId_list ());
                    context.startActivity ( intent );
                }
            } );






        }else {

           FrstAmazing frstAmazing = ( FrstAmazing ) data.get ( position ).getObject ();
            ((Amazing_Frsit2)holder).setAmamzinfrsit ( frstAmazing );

        }
    }

    @Override
    public int getItemCount ( ) {
        return data.size ();
    }

    @Override
    public int getItemViewType ( int position ) {
        return data.get ( position ).getType ();
    }

    public static class Amazing_Offer extends RecyclerView.ViewHolder{
        ImageView imageView_off;
        TextView textView_pric,textView_off,textView_name,textView_valuoff;

        public Amazing_Offer ( @NonNull View itemView ) {
            super ( itemView );
            imageView_off =itemView.findViewById ( R.id.ImageView_off );
            textView_name =itemView.findViewById ( R.id.text_name_off );
            textView_pric =itemView.findViewById ( R.id.text_pric );
            textView_off =itemView.findViewById ( R.id.text_pric_offer );
            textView_valuoff =itemView.findViewById ( R.id.valeu_off );
        }
        public void setAmazingOff( OffAmazing off ){
            Picasso.get ().load ( off.getImg_link () ).into ( imageView_off );
            textView_name.setText ( off.getName () );
            textView_valuoff.setText ( off.getValue_off ()+"%");
            //خط زدن
            SpannableString spannableString = new SpannableString(off.getPrice ());
            spannableString.setSpan(new StrikethroughSpan (),0,off.getPrice().length(),spannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
            textView_pric.setText(spannableString);
            //000و000
            DecimalFormat decimalFormat = new DecimalFormat("###,###");
            String text_price_decmal=decimalFormat.format(Integer.valueOf(off.getOffprice ()));
            textView_off.setText ( text_price_decmal+"  تومان  " );



        }

    }



public static class Amazing_Frsit2 extends  RecyclerView.ViewHolder{
        ImageView imageView_frst,imageView_titel;
    public Amazing_Frsit2 ( @NonNull View itemView ) {
        super ( itemView );
        imageView_frst =itemView.findViewById ( R.id.ImageView_frst );
        imageView_titel= itemView.findViewById ( R.id.ImageView_titel );
    }

    public void setAmamzinfrsit(FrstAmazing frstAmazing ){
        Picasso.get ().load (frstAmazing.getImg_link ()).into ( imageView_frst );
        Picasso.get ().load (frstAmazing.getimg_link_titel ()).into ( imageView_titel );
    }



}




}
