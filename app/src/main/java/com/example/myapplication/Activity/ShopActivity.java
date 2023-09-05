package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Adapter.Shop_Adapter;
import com.example.myapplication.Api.Apiclient;
import com.example.myapplication.Api.Cart;
import com.example.myapplication.Api.Coment_Messeag;
import com.example.myapplication.Api.MyProfileManger;
import com.example.myapplication.Api.apicoonect;
import com.example.myapplication.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopActivity extends AppCompatActivity {
    MyProfileManger myProfileManger;
    String phon ;
    apicoonect request;
    private  int TOTAL_PRIC,ALL_SIZE;
    //
    RecyclerView recyclerView_shop;
    List< Cart > cartList =new ArrayList<> (  );
    Shop_Adapter shop_adapter;
    Button button1;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_shop );
        request = Apiclient.getapiclient ().create ( apicoonect.class );
        myProfileManger =new MyProfileManger ( this );
        button1 =findViewById ( R.id.buttm_go );
        phon = myProfileManger.getUserData ().get ( MyProfileManger.PHON );


        recyclerView_shop =findViewById ( R.id.RecyclerView_shop );
        recyclerView_shop.setHasFixedSize ( true );
        recyclerView_shop.setLayoutManager ( new LinearLayoutManager ( this ) );

        Call<List<Cart>>listCall =request.getcart ( phon );
        listCall.enqueue ( new Callback< List< Cart > > ( ) {
            @Override
            public void onResponse ( Call< List< Cart > > call , Response< List< Cart > > response ) {
                cartList =response.body ();
                shop_adapter =new Shop_Adapter ( getApplicationContext (),cartList , new Shop_Adapter.DeleteProductForId ( ) {
                    @Override
                    public void ItemDeletProduct ( Cart cart ) {
                        Call< Coment_Messeag >delet =request.delet ( cart.getId_cart () );
                        delet.enqueue ( new Callback< Coment_Messeag > ( ) {
                            @Override
                            public void onResponse ( Call< Coment_Messeag > call , Response< Coment_Messeag > response ) {
                                if ( response.body ().getMassage ().equals ("ok") ){
                                    shop_adapter.deletproduct ( cart );
                                    shop_adapter.notifyDataSetChanged ();



                                    Toast.makeText ( ShopActivity.this , "حذف شد", Toast.LENGTH_SHORT ).show ( );
                                }
                            }

                            @Override
                            public void onFailure ( Call< Coment_Messeag > call , Throwable t ) {
                                Toast.makeText ( ShopActivity.this , "no"+t.getMessage () , Toast.LENGTH_SHORT ).show ( );
                            }
                        } );

                    }




                } , new Shop_Adapter.Pric_And_size ( ) {
                    @Override
                    public void Itempeic_And_size ( String price , String data ) {
                        TOTAL_PRIC += Integer.parseInt(price);
                        ALL_SIZE = Integer.parseInt(data);
                        button1.setOnClickListener ( new View.OnClickListener ( ) {
                            @Override
                            public void onClick ( View v ) {
                                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(ShopActivity.this);
                                View Layuot_shit_bottom = LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottem_cart,
                                        findViewById(androidx.constraintlayout.widget.R.id.parent));
                                TextView text_shop= Layuot_shit_bottom.findViewById(R.id.shop);
                                TextView text_qymat = Layuot_shit_bottom.findViewById(R.id.qeymat);
                                Button  button = Layuot_shit_bottom.findViewById(R.id.button_buy);

                                text_shop.setText(ALL_SIZE+"");
                                text_qymat.setText(TOTAL_PRIC+" تومان ");


                                button.setOnClickListener ( new View.OnClickListener ( ) {
                                    @Override
                                    public void onClick ( View v ) {

                                    }
                                } );

                                shop_adapter.notifyDataSetChanged ();



                                bottomSheetDialog.setContentView(Layuot_shit_bottom);
                                bottomSheetDialog.show();
                            }
                        } );


                    }
                }



                );
                recyclerView_shop.setAdapter(shop_adapter);
            }

            @Override
            public void onFailure ( Call< List< Cart > > call , Throwable t ) {
                Toast.makeText ( ShopActivity.this , "no"+t.getMessage () , Toast.LENGTH_SHORT ).show ( );

            }
        } );








    }
}