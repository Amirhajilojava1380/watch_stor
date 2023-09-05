package com.example.myapplication.Model;

public class Similar {
    public String getId ( ) {
        return id;
    }

    public void setId ( String id ) {
        this.id = id;
    }

    public String getName ( ) {
        return name;
    }

    public void setName ( String name ) {
        this.name = name;
    }

    public String getImg_link ( ) {
        return img_link;
    }

    public void setImg_link ( String img_link ) {
        this.img_link = img_link;
    }

    public String getPrice ( ) {
        return price;
    }

    public void setPrice ( String price ) {
        this.price = price;
    }

    public String getId_list ( ) {
        return id_list;
    }

    public void setId_list ( String id_list ) {
        this.id_list = id_list;
    }

    private  String id,name,img_link,price,id_list;

    public Similar ( ) {
    }
}
