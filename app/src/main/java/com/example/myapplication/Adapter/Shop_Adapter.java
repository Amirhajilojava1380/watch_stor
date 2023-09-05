package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Api.Cart;
import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class Shop_Adapter extends RecyclerView.Adapter< Shop_Adapter.MyViewHolder> {
    Context context ;
    List < Cart > data;
    DeleteProductForId deleteProductForId;
    Pric_And_size pric_And_size;


    public Shop_Adapter ( Context context , List< Cart > data , DeleteProductForId deleteProductForId , Pric_And_size pric_And_size ) {
        this.context = context;
        this.data = data;
        this.deleteProductForId = deleteProductForId;
        this.pric_And_size = pric_And_size;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder ( @NonNull ViewGroup parent , int viewType ) {
        View view = LayoutInflater.from ( context ).inflate ( R.layout.item_shop,parent,false );
        return new MyViewHolder ( view );
    }

    @Override
    public void onBindViewHolder ( @NonNull MyViewHolder holder , int position ) {
        holder.name.setText ( data.get ( position ).getName ());
        Picasso.get ().load ( data.get ( position ).getImg_link () ).into ( holder.imageView );

        String pr =data.get ( position ).getPrice ();
        String of =data.get ( position ).getOffprice ();

        if ( pr.equals ( of ) ){
            DecimalFormat decimalFormat =new DecimalFormat ( "###,###" );
            String decimalFormat_price=decimalFormat.format (Integer.valueOf (   data.get ( position ).getPrice ()) );
            holder.price.setText ( decimalFormat_price + " تومان " );

        }else {
            DecimalFormat decimalFormat =new DecimalFormat ( "###,###" );
            String decimalFormat_offprice=decimalFormat.format (Integer.valueOf (   data.get ( position ).getOffprice ()) );
            holder.price.setText ( decimalFormat_offprice + " تومان " );
        }








        holder.delet.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                deleteProductForId.ItemDeletProduct ( data.get ( position ) );
                notifyDataSetChanged ();

            }
        } );


        pric_And_size.Itempeic_And_size ( data.get ( position ).getPrice (),data.size ()+"" );
    }

    @Override
    public int getItemCount ( ) {
        return data.size ();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,price,delet;
        ImageView imageView;
        Button button;


        public MyViewHolder ( @NonNull View itemView ) {
            super ( itemView );
            imageView =itemView.findViewById ( R.id.img_ditel);
            name =itemView.findViewById ( R.id.text_ditel_name );
            price =itemView.findViewById ( R.id.diteli_price );
            delet=itemView.findViewById ( R.id.delet );
            button = itemView.findViewById ( R.id.buttm_go );

        }
    }


    public interface DeleteProductForId{
        void ItemDeletProduct(Cart cart);
    }

    public Shop_Adapter ( Context context , List< Cart > data ) {
        this.context = context;
        this.data = data;
    }

    public interface Pric_And_size{
        void Itempeic_And_size(String price,String data);

    }

   public void deletproduct(Cart cart){
        int index= data.indexOf (cart);
        data.remove ( index );
        notifyDataSetChanged ();

    }
}
