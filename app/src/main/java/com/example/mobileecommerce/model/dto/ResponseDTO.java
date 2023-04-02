package com.example.mobileecommerce.model.dto;

public class ResponseDTO {
    private String message;
    private String httpcode;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getHttpcode() {
        return httpcode;
    }

    public void setHttpcode(String httpcode) {
        this.httpcode = httpcode;
    }
}
