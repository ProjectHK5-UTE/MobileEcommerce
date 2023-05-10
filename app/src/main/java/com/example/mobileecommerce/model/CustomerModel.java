package com.example.mobileecommerce.model;

public class CustomerModel {
    private String userName;

    private String address;

    private String avatar;

    private String fullname;

    private String phonenumber;

    private Integer codeProvince;

    private Integer codeDistrict;

    private Integer codeSubDistrict;

    public CustomerModel() {
    }

    public CustomerModel(String userName, String address, String avatar, String fullname, String phonenumber, Integer codeProvince, Integer codeDistrict, Integer codeSubDistrict) {
        this.userName = userName;
        this.address = address;
        this.avatar = avatar;
        this.fullname = fullname;
        this.phonenumber = phonenumber;
        this.codeProvince = codeProvince;
        this.codeDistrict = codeDistrict;
        this.codeSubDistrict = codeSubDistrict;
    }

    public CustomerModel(String string) {
        this.userName = string;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Integer getCodeProvince() {
        return codeProvince;
    }

    public void setCodeProvince(Integer codeProvince) {
        this.codeProvince = codeProvince;
    }

    public Integer getCodeDistrict() {
        return codeDistrict;
    }

    public void setCodeDistrict(Integer codeDistrict) {
        this.codeDistrict = codeDistrict;
    }

    public Integer getCodeSubDistrict() {
        return codeSubDistrict;
    }

    public void setCodeSubDistrict(Integer codeSubDistrict) {
        this.codeSubDistrict = codeSubDistrict;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "userName='" + userName + '\'' +
                ", address='" + address + '\'' +
                ", avatar='" + avatar + '\'' +
                ", fullname='" + fullname + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", codeProvince=" + codeProvince +
                ", codeDistrict=" + codeDistrict +
                ", codeSubDistrict=" + codeSubDistrict +
                '}';
    }
}
