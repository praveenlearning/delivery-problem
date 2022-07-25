package com.example.delivery.model;

import java.util.function.Predicate;

public class PackageOffer extends Offer<Package> {

    public PackageOffer(String offerCode, Predicate<Package> criteria, int discount) {
        super(offerCode, criteria, discount);
    }
}
