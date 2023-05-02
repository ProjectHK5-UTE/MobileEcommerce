package com.example.mobileecommerce.model;

/* loaded from: classes.dex */
public class HomeViewModelClass {
    Integer id;
    String image;
    boolean isSelected = true;
    Double price;
    String title;

    public HomeViewModelClass() {
    }

    public HomeViewModelClass(Integer id, String title, String image, Double price) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.price = price;
    }

    @Override
    public String toString() {
        return "HomeViewModelClass{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", title='" + title + '\'' +
                '}';
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    public void setSelected(boolean z) {
        this.isSelected = z;
    }
}
