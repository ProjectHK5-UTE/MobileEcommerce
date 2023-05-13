package com.example.mobileecommerce.model.dto;

import java.util.List;

public class OrderDTO {
    private int orderId;
    private double totalPrice;
    private List<LineitemDTO> lineitems;

    private CustomerDTO customer;

    public OrderDTO(double totalPrice, List<LineitemDTO> lineitems, CustomerDTO customer) {
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

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }
}
