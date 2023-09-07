package com.example.myapplication.Activity;

import static com.example.myapplication.Golobol.Key.Id_list;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Activity.User.LoginActivity;
import com.example.myapplication.Adapter.Coment_Adapter;
import com.example.myapplication.Adapter.Imgshow_ditel_Adapter;
import com.example.myapplication.Adapter.Similar_Adapter;
import com.example.myapplication.Adapter.dicrption_Adapter;
import com.example.myapplication.Api.Apiclient;
import com.example.myapplication.Api.Coment_Messeag;
import com.example.myapplication.Api.MyProfileManger;
import com.example.myapplication.Api.apicoonect;
import com.example.myapplication.Golobol.Key;
import com.example.myapplication.Golobol.Link;
import com.example.myapplication.Model.Amazing;
import com.example.myapplication.Model.Ditel_category;
import com.example.myapplication.Model.OffAmazing;
import com.example.myapplication.Model.Similar;
import com.example.myapplication.Model.Slider_bander;
import com.example.myapplication.Model.coment;
import com.example.myapplication.Model.dickrption;
import com.example.myapplication.R;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class ShowDitel_CategoryActivity extends AppCompatActivity {
    RequestQueue requestQueue;
    Bundle bundle;
    String id, name, pric, img, id_list;
    TextView textView_name, textView_pric, textView_go_to_shop;


    //img
    List< Slider_bander > slider_banderList = new ArrayList<> ( );
    ViewPager viewPager;
    TabLayout tabLayout;
    Imgshow_ditel_Adapter imgshow_ditel_adapter;
    //diccrption
    List< dickrption > dickrptionList = new ArrayList<> ( );
    RecyclerView recyclerView_dickrption;
    dicrption_Adapter dicrption_adapter;
    //similar
    List< Similar > similarList = new ArrayList<> ( );
    RecyclerView recyclerView_similar;
    Similar_Adapter similar_adapter;
    //coment
    RecyclerView recyclerView_coment;
    Coment_Adapter coment_adapter;
    List< coment > comentList = new ArrayList<> ( );
    TextView textView_send_coment;
    //
    MyProfileManger myProfileManger;
    String phon;
    apicoonect requset;

    //favorite
   ImageView imageView_favrit;

   public  static String select ="1";
   public  static final String add= "2";
   public  static final String delet= "1";

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_show_ditel_category );
        requestQueue = Volley.newRequestQueue ( this );
        requset = Apiclient.getapiclient ( ).create ( apicoonect.class );
        myProfileManger = new MyProfileManger ( this );
        phon = myProfileManger.getUserData ( ).get ( MyProfileManger.PHON );
        bundle = getIntent ( ).getExtras ( );

        //favorite


        imageView_favrit=findViewById ( R.id.img_favrit );


        imageView_favrit.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                if ( myProfileManger.islogin () ){

                 Sendfavorite ( id,phon );
                }else {
                    startActivity ( new Intent ( ShowDitel_CategoryActivity.this, LoginActivity.class ) );
                }
            }
        } );



        //
        id   = bundle.getString ( Key.id );
        name = bundle.getString ( Key.name );
        pric = bundle.getString ( Key.Price );
        id_list = bundle.getString ( Key.Id_list );
        img  = bundle.getString ( Key.Img_link );
        Toast.makeText ( this , "" + id + id_list , Toast.LENGTH_SHORT ).show ( );

        textView_name = findViewById ( R.id.text_name_show );
        textView_name.setText ( " اسم : " + name );

        DecimalFormat decimalFormat = new DecimalFormat ( "###,###" );
        String text_price_decmal = decimalFormat.format ( Integer.valueOf ( pric ) );
        textView_pric = findViewById ( R.id.price );
        textView_pric.setText ( text_price_decmal + " تومان " );

        textView_go_to_shop = findViewById ( R.id.go_to_shop );
        viewPager = findViewById ( R.id.viewpager_show );
        tabLayout = findViewById ( R.id.TabLayout_show );


        if ( myProfileManger.islogin ( ) ) {
            textView_go_to_shop.setOnClickListener ( new View.OnClickListener ( ) {
                @Override
                public void onClick ( View v ) {
                    send_shop ( id , phon );
                }
            } );
        }


        textView_send_coment = findViewById ( R.id.text_coment_send );
        textView_send_coment.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                Intent intent = new Intent ( ShowDitel_CategoryActivity.this , Coment_send_Activity.class );
                intent.putExtra ( Key.id , id );
                intent.putExtra ( Key.name , name );
                intent.putExtra ( Key.Img_link , img );
                intent.putExtra ( Key.Id_list , id_list );
                intent.putExtra ( Key.Price , pric );
                startActivity ( intent );
            }
        } );

        getimg_show_ditel ( id );
        getdirction_ditel ( id );
        getmahsolmoshabeh ( Id_list );
        getComent ( id );
    }

    private void deletfavorite ( String id  ) {
        Call<Coment_Messeag> call_delet =requset.deletfaverit ( id );
        call_delet.enqueue ( new Callback< Coment_Messeag > ( ) {
            @Override
            public void onResponse ( Call< Coment_Messeag > call , retrofit2.Response< Coment_Messeag > response ) {
                if ( response.isSuccessful ()&&response.body ().getMassage ().equals ("ok") ){
                    Toast.makeText ( ShowDitel_CategoryActivity.this , "از لیست حذف شد" , Toast.LENGTH_SHORT ).show ( );


                }

            }

            @Override
            public void onFailure ( Call< Coment_Messeag > call , Throwable t ) {
                Toast.makeText ( ShowDitel_CategoryActivity.this , ""+t.getMessage () , Toast.LENGTH_SHORT ).show ( );
            }
        } );




    }

    private void Sendfavorite ( String id , String phon ) {
        Call<Coment_Messeag> call =requset.sendfaverit (id,phon);
        call.enqueue ( new Callback< Coment_Messeag > ( ) {
            @Override
            public void onResponse ( Call< Coment_Messeag > call , retrofit2.Response< Coment_Messeag > response ) {
                if ( response.isSuccessful ()&&response.body ().isStatus () ){
                    Toast.makeText ( ShowDitel_CategoryActivity.this , ""+response.body ().getMassage () , Toast.LENGTH_SHORT ).show ( );
                    select=add;
                    imageView_favrit.setImageResource ( R.drawable.baseline_favorite_24 );

                }else {
                    deletfavorite ( id );
                    select=delet;
                    imageView_favrit.setImageResource ( R.drawable.baseline_favorite_border_24 );

                }
            }

            @Override
            public void onFailure ( Call< Coment_Messeag > call , Throwable t ) {
                Toast.makeText ( ShowDitel_CategoryActivity.this , ""+t.getMessage () , Toast.LENGTH_SHORT ).show ( );
            }
        } );






    }

    private void send_shop ( String id , String phon ) {
        Call< Coment_Messeag > call = requset.sentcart ( id , phon );
        call.enqueue ( new Callback< Coment_Messeag > ( ) {
            @Override
            public void onResponse ( Call< Coment_Messeag > call , retrofit2.Response< Coment_Messeag > response ) {
                if ( response.isSuccessful ( ) && response.body ( ).isStatus ( ) ) {
                    Toast.makeText ( ShowDitel_CategoryActivity.this , "" + response.body ( ).getMassage ( ) , Toast.LENGTH_SHORT ).show ( );
                }

            }

            @Override
            public void onFailure ( Call< Coment_Messeag > call , Throwable t ) {
                Toast.makeText ( ShowDitel_CategoryActivity.this , "" + t.getMessage ( ) , Toast.LENGTH_SHORT ).show ( );
            }
        } );


    }

    private void getComent ( String id ) {

        recyclerView_coment = findViewById ( R.id.recyclerview_coment );
        recyclerView_coment.setHasFixedSize ( true );
        coment_adapter = new Coment_Adapter ( this , comentList );
        recyclerView_coment.setLayoutManager ( new LinearLayoutManager ( this , RecyclerView.HORIZONTAL , false ) );
        recyclerView_coment.setAdapter ( coment_adapter );

        String url = Link.COMENT_DITEL_CATEGORY;
        Response.Listener< String > listener = new Response.Listener< String > ( ) {
            @Override
            public void onResponse ( String response ) {
                Gson gson = new Gson ( );
                coment[] coments = gson.fromJson ( response.toString ( ) , coment[].class );
                for ( int i = 0 ; i < coments.length ; i++ ) {
                    comentList.add ( coments[ i ] );
                    coment_adapter.notifyDataSetChanged ( );
                }
            }
        };

        Response.ErrorListener listener_erorr = new Response.ErrorListener ( ) {
            @Override
            public void onErrorResponse ( VolleyError error ) {
                Toast.makeText ( getApplicationContext ( ) , "no" + error.getMessage ( ) , Toast.LENGTH_SHORT ).show ( );

            }
        };

        StringRequest request = new StringRequest ( Request.Method.POST , url , listener , listener_erorr ) {
            @Nullable
            @Override
            protected Map< String, String > getParams ( ) throws AuthFailureError {
                HashMap< String, String > map = new HashMap<> ( );
                map.put ( Key.id , id );
                return map;
            }
        };
        requestQueue.add ( request );
    }

    private void getmahsolmoshabeh ( String Id_list ) {

        recyclerView_similar = findViewById ( R.id.recyclerview_similar );
        recyclerView_similar.setHasFixedSize ( true );
        similar_adapter = new Similar_Adapter ( this , similarList );
        recyclerView_similar.setLayoutManager ( new LinearLayoutManager ( this , RecyclerView.HORIZONTAL , false ) );
        recyclerView_similar.setAdapter ( similar_adapter );

        String url = Link.SIMILAR_DITEL_CATEGORY;
        Response.Listener< String > listener = new Response.Listener< String > ( ) {
            @Override
            public void onResponse ( String response ) {
                Gson gson = new Gson ( );
                Similar[] similars = gson.fromJson ( response.toString ( ) , Similar[].class );
                for ( int i = 0 ; i < similars.length ; i++ ) {
                    similarList.add ( similars[ i ] );
                    similar_adapter.notifyDataSetChanged ( );
                }
            }
        };

        Response.ErrorListener listener_erorr = new Response.ErrorListener ( ) {
            @Override
            public void onErrorResponse ( VolleyError error ) {
                Toast.makeText ( getApplicationContext ( ) , "no" + error.getMessage ( ) , Toast.LENGTH_SHORT ).show ( );

            }
        };

        StringRequest request = new StringRequest ( Request.Method.POST , url , listener , listener_erorr ) {
            @Nullable
            @Override
            protected Map< String, String > getParams ( ) throws AuthFailureError {
                HashMap< String, String > map = new HashMap<> ( );
                map.put ( Key.Id_list , id_list );
                return map;
            }
        };
        requestQueue.add ( request );


    }

    private void getdirction_ditel ( String id ) {
        recyclerView_dickrption = findViewById ( R.id.recyclerview_fani );
        recyclerView_dickrption.setHasFixedSize ( true );
        dicrption_adapter = new dicrption_Adapter ( this , dickrptionList );
        recyclerView_dickrption.setLayoutManager ( new LinearLayoutManager ( this ) );
        recyclerView_dickrption.setAdapter ( dicrption_adapter );

        String url = Link.description_DITEL_CATEGORY;
        Response.Listener< String > listener = new Response.Listener< String > ( ) {
            @Override
            public void onResponse ( String response ) {
                Gson gson = new Gson ( );
                dickrption[] dickrptions = gson.fromJson ( response.toString ( ) , dickrption[].class );
                for ( int i = 0 ; i < dickrptions.length ; i++ ) {
                    dickrptionList.add ( dickrptions[ i ] );
                    dicrption_adapter.notifyDataSetChanged ( );
                }
            }
        };

        Response.ErrorListener listener_erorr = new Response.ErrorListener ( ) {
            @Override
            public void onErrorResponse ( VolleyError error ) {
                Toast.makeText ( getApplicationContext ( ) , "no" + error.getMessage ( ) , Toast.LENGTH_SHORT ).show ( );

            }
        };

        StringRequest request = new StringRequest ( Request.Method.POST , url , listener , listener_erorr ) {
            @Nullable
            @Override
            protected Map< String, String > getParams ( ) throws AuthFailureError {
                HashMap< String, String > map = new HashMap<> ( );
                map.put ( Key.id , id );
                return map;
            }
        };
        requestQueue.add ( request );

    }


    private void getimg_show_ditel ( String id ) {
        imgshow_ditel_adapter = new Imgshow_ditel_Adapter ( this , slider_banderList );

        tabLayout.setupWithViewPager ( viewPager , true );
        viewPager.setRotationY ( 180 );
        viewPager.setAdapter ( imgshow_ditel_adapter );

        String url = Link.IMG_DITEL_CATEGORY;
        Response.Listener< String > listener = new Response.Listener< String > ( ) {
            @Override
            public void onResponse ( String response ) {
                Gson gson = new Gson ( );
                Slider_bander[] slider_banders = gson.fromJson ( response.toString ( ) , Slider_bander[].class );
                for ( int i = 0 ; i < slider_banders.length ; i++ ) {
                    slider_banderList.add ( slider_banders[ i ] );
                    imgshow_ditel_adapter.notifyDataSetChanged ( );
                }
            }
        };

        Response.ErrorListener listener_erorr = new Response.ErrorListener ( ) {
            @Override
            public void onErrorResponse ( VolleyError error ) {
                Toast.makeText ( getApplicationContext ( ) , "no" + error.getMessage ( ) , Toast.LENGTH_SHORT ).show ( );

            }
        };

        StringRequest request = new StringRequest ( Request.Method.POST , url , listener , listener_erorr ) {
            @Nullable
            @Override
            protected Map< String, String > getParams ( ) throws AuthFailureError {
                HashMap< String, String > map = new HashMap<> ( );
                map.put ( Key.id , id );
                return map;
            }
        };
        requestQueue.add ( request );

    }
}