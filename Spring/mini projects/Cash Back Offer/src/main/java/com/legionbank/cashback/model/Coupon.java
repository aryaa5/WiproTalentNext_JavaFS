package com.legionbank.cashback.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Coupon {
    @Id
    private String couponCode;
    private int offerPercentage;

    // Default constructor (required by JPA)
    public Coupon() {}

    // Getters
    public String getCouponCode() { return couponCode; }
    public int getOfferPercentage() { return offerPercentage; }

    // Setters
    public void setCouponCode(String couponCode) { this.couponCode = couponCode; }
    public void setOfferPercentage(int offerPercentage) { this.offerPercentage = offerPercentage; }
}
