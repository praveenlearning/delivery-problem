package com.example.delivery.service;

import com.example.delivery.model.Offer;
import com.example.delivery.model.PackageOffer;

import java.util.ArrayList;
import java.util.List;

public class OfferService {
    private final Offer zeroDiscountOffer = new Offer("NONE", any -> false, 0) {};
    private final List<Offer> allOffers = new ArrayList<>() {{
            add(new PackageOffer("OFR001", pkg -> pkg.getDistance() < 200 && pkg.getWeight() >= 70 && pkg. getWeight() <= 200, 10));
            add(new PackageOffer("OFR002", pkg -> pkg.getDistance() >= 50 && pkg.getDistance() <= 150 && pkg.getWeight() >= 70 && pkg. getWeight() <= 200, 7));
            add(new PackageOffer("OFR003", pkg -> pkg.getDistance() >= 50 && pkg.getDistance() <= 250 && pkg.getWeight() >= 10 && pkg. getWeight() <= 150, 5));
        }};

    public <T> int apply(Offer<T> offer, T pkg) {
        if (offer.getCriteria().test(pkg))
            return offer.getDiscount();
        else
            return 0;
    }

    public Offer getOffer(String offerCode) {
        return allOffers.stream()
                .filter(offer -> offer.getOfferCode().equals(offerCode))
                .findFirst()
                .orElse(zeroDiscountOffer);
    }
}
