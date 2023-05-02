package com.example.mobileecommerce.model;

/* loaded from: classes.dex */
public class MyOrderModelClass {
    String date;
    Integer image;
    String order_no;
    String price;
    String quantity;
    String title;

    public MyOrderModelClass(Integer num, String str, String str2, String str3, String str4, String str5) {
        this.image = num;
        this.title = str;
        this.quantity = str2;
        this.price = str3;
        this.date = str4;
        this.order_no = str5;
    }

    public Integer getImage() {
        return this.image;
    }

    public void setImage(Integer num) {
        this.image = num;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getQuantity() {
        return this.quantity;
    }

    public void setQuantity(String str) {
        this.quantity = str;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String str) {
        this.price = str;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public String getOrder_no() {
        return this.order_no;
    }

    public void setOrder_no(String str) {
        this.order_no = str;
    }
}
