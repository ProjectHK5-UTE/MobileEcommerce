package com.example.mobileecommerce.model;

import java.io.Serializable;

/* loaded from: classes.dex */
public class BrandsModel implements Serializable {

    private int brandId;
    private String name;
    private String logo;

    public BrandsModel(int brandId, String name, String logo) {
        this.brandId = brandId;
        this.name = name;
        this.logo = logo;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
