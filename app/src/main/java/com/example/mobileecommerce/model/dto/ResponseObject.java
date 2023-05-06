package com.example.mobileecommerce.model.dto;


public class ResponseObject {
    private String status;
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseObject(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
