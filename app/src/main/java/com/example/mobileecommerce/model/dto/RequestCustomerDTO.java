package com.example.mobileecommerce.model.dto;

public class RequestCustomerDTO {
    private String userName;

    private String address;

    private String avatar;

    private String fullname;

    private String phonenumber;

    public RequestCustomerDTO(String userName, String address, String avatar, String fullname, String phonenumber) {
        this.userName = userName;
        this.address = address;
        this.avatar = avatar;
        this.fullname = fullname;
        this.phonenumber = phonenumber;
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
}
