package com.example.delivery.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OfferTest {

    @Test
    void shouldReturnApplicableDiscount() {
        Offer offer = new Offer(pkg -> pkg.weight <= 70 && pkg.distance <= 100, 10);
        Package pkgOne = Package.parsePackage("PKG1 5 10 OFR001");
        Package pkgTwo = Package.parsePackage("PKG2 75 50");

        assertEquals(10, offer.apply(pkgOne));
        assertEquals(0, offer.apply(pkgTwo));
    }
}