package com.example.noq;

public class shopgetter {

    private String sn;
    private String add;
    String shopImage;


    shopgetter() {
    }

    public shopgetter(String sn, String add, String shopImage) {
        this.sn = sn;
        this.add = add;
        this.shopImage = shopImage;
    }



    public String getSn() {
        return sn;
    }


    public String getAdd() {
        return add;
    }

    public String getShopImage() {
        return shopImage;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public void setShopImage(String shopImage) {
        this.shopImage = shopImage;
    }


}