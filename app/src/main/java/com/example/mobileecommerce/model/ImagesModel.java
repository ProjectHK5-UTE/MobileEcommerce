package com.example.mobileecommerce.model;

public class ImagesModel {
    private int imageId;
    private String path;

    public ImagesModel(int imageId, String path) {
        this.imageId = imageId;
        this.path = path;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
