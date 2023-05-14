package com.example.mobileecommerce.model.dto;

import java.util.List;

public class RequestOrderDTO {
    private int orderId;
    private double totalPrice;
    private List<LineitemDTO> lineitems;

    private RequestCustomerDTO customer;

    public RequestOrderDTO(double totalPrice, List<LineitemDTO> lineitems, RequestCustomerDTO customer) {
        this.totalPrice = totalPrice;
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
