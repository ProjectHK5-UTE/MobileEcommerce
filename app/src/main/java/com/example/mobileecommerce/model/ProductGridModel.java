package com.example.mobileecommerce.model;

import java.io.Serializable;
import java.util.List;

/* loaded from: classes.dex */
public class ProductGridModel implements Serializable {
    private int productId;
    private String productName;
    private String cpu;
    private String description;
    private String origin;
    private String os;
    private String battery;
    private String screen;
    private double price;
    private List<OptionModel> options;

    public ProductGridModel(int productId, String productName, String cpu, String description, String origin, String os, String battery, String screen, double price, List<OptionModel> options) {
        this.productId = productId;
        this.productName = productName;
        this.cpu = cpu;
        this.description = description;
        this.origin = origin;
        this.os = os;
        this.battery = battery;
        this.screen = screen;
        this.price = price;
        this.options = options;
    }

    public ProductGridModel(int productId) {
        this.productId = productId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<OptionModel> getOptions() {
        return options;
    }

    public void setOptions(List<OptionModel> options) {
        this.options = options;
    }
}
