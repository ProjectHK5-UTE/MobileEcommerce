package com.example.mobileecommerce.model;

import com.example.mobileecommerce.model.ImagesModel;

import java.io.Serializable;
import java.util.List;

public class OptionModel implements Serializable {
    private int optionId;
    private String ram;
    private String rom;
    private double price;
    private List<ImagesModel> images;

    public OptionModel(int optionId, String ram, String rom, double price, List<ImagesModel> images) {
        this.optionId = optionId;
        this.ram = ram;
        this.rom = rom;
        this.price = price;
        this.images = images;
    }

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
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

    public List<ImagesModel> getImages() {
        return images;
    }

    public void setImages(List<ImagesModel> images) {
        this.images = images;
    }
}
