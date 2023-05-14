package com.example.mobileecommerce.model.dto;

import com.example.mobileecommerce.model.CustomerModel;

import java.util.List;

public class ResponseOrderDTO {
    private int orderId;
    private double totalPrice;
    private List<LineitemDTO> lineitems;
    private CustomerModel customer;

    public ResponseOrderDTO(int orderId, double totalPrice, List<LineitemDTO> lineitems, CustomerModel customer) {
        this.orderId = orderId;
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

    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }
}
