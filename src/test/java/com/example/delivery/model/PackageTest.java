package com.example.delivery.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PackageTest {

    @Test
    void parsePackage() {
        Package pkg = new Package("PKG1", 50, 5, "OFR001");

        assertEquals(pkg, Package.parsePackage("PKG1 50 5 OFR001"));
    }

    @Test
    void parsePackageIfOfferCodeNotEntered() {
        Package pkg = new Package("PKG1", 50, 5, null);

        assertEquals(pkg, Package.parsePackage("PKG1 50 5"));
    }
}