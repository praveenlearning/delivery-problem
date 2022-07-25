package com.example.delivery.service;

import com.example.delivery.model.Offer;
import com.example.delivery.model.Package;
import com.example.delivery.model.PackageCostReport;

public class PackageService {
    private final int basePrice;
    OfferService offerService = new OfferService();

    public PackageService(int basePrice) {
        this.basePrice = basePrice;
    }

    public int calculateCost(Package pkg) {
        return basePrice + pkg.getWeight() * 10 + pkg.getDistance() * 5;
    }

    public int calculateDiscount(Package pkg) {
        Offer offer = offerService.getOffer(pkg.getOfferCode());
        try {
            int discount = offerService.apply(offer, pkg);
            int totalCost = calculateCost(pkg);
            return totalCost * discount / 100;
        } catch (NullPointerException ex) {
            return 0;
        }
    }

    public int finalCost(Package pkg) {
        return calculateCost(pkg) - calculateDiscount(pkg);
    }

    public PackageCostReport details(Package pkg) {
        return new PackageCostReport(pkg.getPackageId(), calculateDiscount(pkg), finalCost(pkg));
    }
}
