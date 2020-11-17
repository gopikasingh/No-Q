package com.example.noq;


public class tlist {
    private String date;
    private String desc;
    private String itemid;
    private String pname;
    private float price;
    private String qty;
    private String time;
    private String sn;
    private String itemImage;

    public tlist() {
    }

    public tlist(String date, String desc, String itemid, String pname, float price, String qty, String time, String sn, String itemImage) {
        this.date = date;
        this.desc = desc;
        this.itemid = itemid;
        this.pname = pname;
        this.price = price;
        this.qty = qty;
        this.time = time;
        this.sn = sn;
        this.itemImage = itemImage;
    }


    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getSn() {
        return sn;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }
}