package com.example.delivery.model;

import java.util.function.Predicate;

public abstract class Offer <T> {
    final String offerCode;
    final Predicate<T> criteria;
    final int discount;

    public Offer(String offerCode, Predicate<T> criteria, int discount) {
        this.offerCode = offerCode;
        this.criteria = criteria;
        this.discount = discount;
    }

    public String getOfferCode() {
        return offerCode;
    }

    public Predicate<T> getCriteria() {
        return criteria;
    }

    public int getDiscount() {
        return discount;
    }
}

