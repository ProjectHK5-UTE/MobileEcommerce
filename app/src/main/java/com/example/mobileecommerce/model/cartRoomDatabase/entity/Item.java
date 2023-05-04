package com.example.mobileecommerce.model.cartRoomDatabase.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "item")
public class Item implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int productId;
    private int optionId;
    private String name;
    private String image;
    private int quantity;
    private String ram;
    private String rom;
    private double price;

    public Item(int productId, int optionId, String name, String image, int quantity, String ram, String rom, double price) {
        this.productId = productId;
        this.optionId = optionId;
        this.name = name;
        this.image = image;
        this.quantity = quantity;
        this.ram = ram;
        this.rom = rom;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getRom() {
        return rom;
    }

    public void setRom(String rom) {
        this.rom = rom;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", productId=" + productId +
                ", optionId=" + optionId +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", quantity=" + quantity +
                ", ram='" + ram + '\'' +
                ", rom='" + rom + '\'' +
                ", price=" + price +
                '}';
    }
}
