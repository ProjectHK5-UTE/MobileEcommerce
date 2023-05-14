package com.example.mobileecommerce.model;

/* loaded from: classes.dex */
public class MyOrderModelClass {
    String image;
    double price;
    Integer quantity;
    Integer order_no;

    public MyOrderModelClass(String image, double price, Integer quantity, Integer order_no) {
        this.image = image;
        this.price = price;
        this.quantity = quantity;
        this.order_no = order_no;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getOrder_no() {
        return order_no;
    }

    public void setOrder_no(Integer order_no) {
        this.order_no = order_no;
    }
}
