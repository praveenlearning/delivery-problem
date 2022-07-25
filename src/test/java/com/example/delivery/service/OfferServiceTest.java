package com.example.delivery.service;

import com.example.delivery.model.Offer;
import com.example.delivery.model.Package;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OfferServiceTest {

    OfferService offerService = new OfferService();

    @Test
    void shouldReturnApplicableDiscount() {
        Offer<Package> offer = new Offer<Package>("", pkg -> pkg.getWeight() <= 70 && pkg.getDistance() <= 100, 10) {};
        Package pkgOne = new Package("PKG1", 5, 10, "OFR001");
        Package pkgTwo = new Package("PKG2", 75, 50, null);

        assertEquals(10, offerService.apply(offer, pkgOne));
        assertEquals(0, offerService.apply(offer, pkgTwo));
    }
}