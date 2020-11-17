package com.example.noq;

public class itemgetter {
    itemgetter() {
    }

    public itemgetter(String name, float price, String desc, String barcode, String itemImage, String itemImagethumb) {
        this.name = name;
        this.price = price;
        this.desc = desc;
        this.barcode = barcode;
        this.itemImage = itemImage;
        this.itemImagethumb=itemImagethumb;
    }

    String name;
    float price;
    String desc;
    String barcode;


    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public String getItemImagethumb() {
        return itemImagethumb;
    }

    public void setItemImagethumb(String itemImagethumb) {
        this.itemImagethumb = itemImagethumb;
    }

    String itemImage;
    String itemImagethumb;


    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public String getDesc() {
        return desc;
    }

    public String getBarcode() {
        return barcode;
    }



    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }



}