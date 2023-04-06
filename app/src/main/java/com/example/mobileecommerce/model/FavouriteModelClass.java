package com.example.mobileecommerce.model;

/* loaded from: classes.dex */
public class FavouriteModelClass {
    Integer image;
    boolean isSelected = true;
    String price;
    String title;

    public FavouriteModelClass(Integer num, String str, String str2) {
        this.image = num;
        this.title = str;
        this.price = str2;
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

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String str) {
        this.price = str;
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    public void setSelected(boolean z) {
        this.isSelected = z;
    }
}
