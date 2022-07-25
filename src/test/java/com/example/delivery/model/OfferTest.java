package com.example.delivery.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OfferTest {

    @Test
    void shouldReturnApplicableDiscount() {
        Offer offer = new Offer(pkg -> pkg.weight <= 70 && pkg.distance <= 100, 10);
        Package pkgOne = new Package("PKG1", 5, 10, "OFR001");
        Package pkgTwo = new Package("PKG2", 75, 50, null);

        assertEquals(10, offer.apply(pkgOne));
        assertEquals(0, offer.apply(pkgTwo));
    }
}