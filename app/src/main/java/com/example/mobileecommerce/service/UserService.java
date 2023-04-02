package com.example.mobileecommerce.service;

public class UserService {
    public boolean checkPassword(String password, String re_password){
        if(password.equals(re_password)){
            return true;
        }
        return false;
    }

    public static boolean isGmailAddress(String email) {
        String pattern = "^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$";
        return email.matches(pattern);
    }
}
