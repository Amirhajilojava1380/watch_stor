package com.example.myapplication.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface apicoonect {

    @FormUrlEncoded
    @POST("regster.php")
    Call<User>sentRegster(
                           @Field  ( "phon" ) String phon,
                           @Field  ( "name" ) String name,
                           @Field  ( "user_email" ) String user_email,
                           @Field  ( "password" ) String password
                           );

    @FormUrlEncoded
    @POST("login.php")
    Call<User> sentLogin(
                        @Field ( "phon" ) String phon,
                        @Field ( "password" ) String password
                        );


    @FormUrlEncoded
    @POST("sendComent.php")
    Call<Coment_Messeag>sentComent(
            @Field  ( "id_pr" ) String id_pr,
            @Field  ( "user_email" ) String user_email,
            @Field  ( "decreption" ) String decreption,
            @Field  ( "data" ) String data,
            @Field  ( "rating" ) String rating
    );

    @FormUrlEncoded
    @POST("Sendcart.php")
    Call<Coment_Messeag>sentcart(
            @Field  ( "id_pr" ) String phon,
            @Field  ( "phon" ) String id_pr
    );

    @FormUrlEncoded
    @POST("Sendfaverit.php")
    Call<Coment_Messeag>sendfaverit(
            @Field  ( "id_pr" ) String id_pr,
            @Field  ( "phon" ) String phon

    );

    @GET("deletfaverit.php")
    Call<Coment_Messeag> deletfaverit( @Query  ( "id_pr" ) String id_pr );

    @GET("getFaverite.php")
    Call< List<Cart>>getfaverit( @Query  ( "phon" ) String phon);

    @GET("getCart.php")
    Call< List<Cart>>getcart( @Query  ( "phon" ) String phon);


    @GET("deletCart.php")
    Call<Coment_Messeag>delet(@Query ("id_cart") String id_cart);


    @GET("getPey_off.php")
    Call<List<Cart>>getpeyoff(@Query ("phon") String phon);
}
