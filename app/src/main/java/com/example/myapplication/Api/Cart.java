package com.example.myapplication.Api;

public class Cart {
private  String id_cart,id_pr,img_link,name,price,offprice;

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
