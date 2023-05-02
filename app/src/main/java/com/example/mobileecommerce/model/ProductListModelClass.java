package com.example.mobileecommerce.model;

/* loaded from: classes.dex */
public class ProductListModelClass {
    Integer image;
    Integer like;
    String title;

    public ProductListModelClass(Integer num, String str, Integer num2) {
        this.image = num;
        this.title = str;
        this.like = num2;
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

    public Integer getLike() {
        return this.like;
    }

    public void setLike(Integer num) {
        this.like = num;
    }
}
