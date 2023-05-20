package com.example.mobileecommerce.model.dto;

import com.example.mobileecommerce.model.Status;

import java.util.List;

public class RequestOrderDTO {
    private int orderId;
    private double totalPrice;
    private Status status;
    private List<LineitemDTO> lineitems;

    private RequestCustomerDTO customer;

    public RequestOrderDTO(double totalPrice, Status status, List<LineitemDTO> lineitems, RequestCustomerDTO customer) {
        this.totalPrice = totalPrice;
        this.status = status;
        this.lineitems = lineitems;
        this.customer = customer;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<LineitemDTO> getLineitems() {
        return lineitems;
    }

    public void setLineitems(List<LineitemDTO> lineitems) {
        this.lineitems = lineitems;
    }

    public RequestCustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(RequestCustomerDTO customer) {
        this.customer = customer;
    }
}
