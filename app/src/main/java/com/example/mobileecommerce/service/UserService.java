package com.example.mobileecommerce.service;

public class UserService {
    public static boolean checkPassword(String password, String re_password){
        if(password.equals(re_password)){
            return true;
        }
        return false;
    }
    public static String isStrongPassword(String password) {
        // Kiểm tra độ dài mật khẩu
        if (password.length() < 6 || password.length() > 8) {
            return "Password Length From 6 To 8";
        }
        // Kiểm tra chữ cái viết hoa, chữ cái viết thường và ký tự đặc biệt
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*()-+={}\\[\\]|;:'\"<>,.?/~`]).+$";
        if (password.matches(regex))
            return "Success";
        return "Password must contain 3 uppercase, lowercase, and special characters";
    }
    public static boolean isGmailAddress(String email) {
        String pattern = "^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$";
        return email.matches(pattern);
    }

    public static String checkFormSignUp(String email, String password, String re_password){
        String isSuccess = null;
        if(isGmailAddress(email)) {
            if (isStrongPassword(password) == "Success") {
                if (checkPassword(password, re_password)){
                    isSuccess = "Success";
                } else {
                    isSuccess = "Password Confirmation Error";
                }
            } else {
                isSuccess = isStrongPassword(password);
            }
        } else {
            isSuccess = "This is not GMAIL";
        }
        return  isSuccess;
    }
}
