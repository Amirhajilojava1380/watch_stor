package com.example.myapplication.Model;

public class FrstAmazing {
    String img_link_titel , img_link;

    public String getimg_link_titel( ) {
        return img_link_titel;
    }

    public void setimg_link_titel ( String titel ) {
        this.img_link_titel = titel;
    }

    public String getImg_link ( ) {
        return img_link;
    }

    public void setImg_link ( String img_link ) {
        this.img_link = img_link;
    }

    public FrstAmazing ( String titel , String img_link ) {
        this.img_link_titel = titel;
        this.img_link = img_link;
    }
}
