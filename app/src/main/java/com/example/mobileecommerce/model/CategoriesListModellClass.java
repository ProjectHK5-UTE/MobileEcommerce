package com.example.mobileecommerce.model;

/* loaded from: classes.dex */
public class CategoriesListModellClass {
    Integer image;
    String title;

    public CategoriesListModellClass(Integer num, String str) {
        this.image = num;
        this.title = str;
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
}
