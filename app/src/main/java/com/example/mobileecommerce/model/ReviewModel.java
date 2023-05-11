package com.example.mobileecommerce.model;

import java.sql.Timestamp;

public class ReviewModel {
    private Integer reviewId;

    private Integer rate;

    private String content;

    private Timestamp updateAt;

    private CustomerModel customer;

    public ReviewModel(Integer reviewId, Integer rate, String content, Timestamp updateAt, CustomerModel customer) {
        this.reviewId = reviewId;
        this.rate = rate;
        this.content = content;
        this.updateAt = updateAt;
        this.customer = customer;
    }

    public ReviewModel() {
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }

    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }
}
