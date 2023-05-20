package com.example.mobileecommerce.model;

import java.io.Serializable;
import java.security.Timestamp;

public class UserModel implements Serializable {
    private Timestamp createdAt;
    private String email;
    private String password;
    private String role;
    private Timestamp updateAt;

    public UserModel(String userName, String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.role = role;
    }
    public UserModel(String email, String password) {
        this.email = email;
        this.password = password;
    }
    private String userName;
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public Timestamp getUpdateAt() {
        return updateAt;
    }
    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    //private List<Customer> customers;
    @Override
    public String toString() {
        return "UserModel{" +
                "createdAt=" + createdAt +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", updateAt=" + updateAt +
                ", userName='" + userName + '\'' +
                '}';
    }
}
