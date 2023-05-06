package com.example.mobileecommerce.model;

public class Province {
    private Integer code;
    private String name;

    public Province() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Province(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
