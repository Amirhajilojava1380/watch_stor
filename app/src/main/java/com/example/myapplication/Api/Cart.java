package com.example.myapplication.Api;

public class Cart {
private  String id_cart;
    private  String num;

    public String getNum ( ) {
        return num;
    }

    public void setNum ( String num ) {
        this.num = num;
    }

    private String id_pr;
    private String img_link;
    private String name;
    private String price;
    private String offprice;


    public String getId_list ( ) {
        return id_list;
    }

    public void setId_list ( String id_list ) {
        this.id_list = id_list;
    }

    private String id_list;


    public String getId_cart ( ) {
        return id_cart;
    }

    public void setId_cart ( String id_cart ) {
        this.id_cart = id_cart;
    }

    public String getOffprice ( ) {
        return offprice;
    }

    public void setOffprice ( String offprice ) {
        this.offprice = offprice;
    }

    public String getId_pr ( ) {
        return id_pr;
    }

    public void setId_pr ( String id_pr ) {
        this.id_pr = id_pr;
    }



    public String getImg_link ( ) {
        return img_link;
    }

    public void setImg_link ( String img_link ) {
        this.img_link = img_link;
    }

    public String getName ( ) {
        return name;
    }

    public void setName ( String name ) {
        this.name = name;
    }

    public String getPrice ( ) {
        return price;
    }

    public void setPrice ( String price ) {
        this.price = price;
    }

    public Cart ( ) {
    }
}
