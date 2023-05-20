package com.example.mobileecommerce.model;

public enum Status {
    PENDING("Đang chờ duyệt"),
    PICKING("Đang chờ lấy hàng"),
    TRANSIT("Đang vận chuyển"),
    SUCCESSFUL("Đã hoàn tất"),
    CANCELLED("Đã hủy");

    private final String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static Status fromString(String statusStr) {
        for (Status s : Status.values()) {
            if (s.getStatus().equalsIgnoreCase(statusStr)) {
                return s;
            }
        }
        return null;
    }
}
