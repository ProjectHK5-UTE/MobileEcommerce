package com.example.mobileecommerce.model;

/* loaded from: classes.dex */
public class EShoppingModelClass {
    String title;
    Integer image;

    public EShoppingModelClass(String str, int i) {
        this.title = str;
        this.image = i;
    }

    public String getTitle() {
        return this.title;
    }
    public void setTitle(String str) {
        this.title = str;
    }

    public Integer getImage() { return this.image; }
    public void setImage(Integer image) { this.image = image; }
}
