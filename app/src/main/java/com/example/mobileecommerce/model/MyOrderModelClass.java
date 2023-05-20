package com.example.mobileecommerce.model;

/* loaded from: classes.dex */
public class MyOrderModelClass {
    String name;
    String image;
    double price;
    Status status;
    Integer quantity;
    Integer order_no;
    Integer productId;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public MyOrderModelClass(String image, double price, Status status, Integer quantity, Integer order_no) {
        this.image = image;
        this.price = price;
        this.status = status;
        this.quantity = quantity;
        this.order_no = order_no;
    }
    public MyOrderModelClass(String name, String image, double price, Integer quantity, Integer order_no, Status status, Integer productId) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
        this.order_no = order_no;
        this.status = status;
        this.productId = productId;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
