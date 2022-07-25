package com.example.delivery.service;

import com.example.delivery.model.Package;
import com.example.delivery.model.PackageCostReport;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PackageServiceTest {

    private final PackageService packageService = new PackageService(100);

    @Test
    public void shouldCalculateTotalCostForPackage() {
        Package pkg = new Package("PKG1", 15, 5, "OFR001");

        int expected = 275;
        int cost = packageService.calculateCost(pkg);

        assertEquals(expected, cost);
    }

    @Test
    public void shouldCalculateDiscountForPackage() {
        Package pkg = new Package("PKG1", 75, 50, "OFR001");

        int expected = 110;
        int discount = packageService.calculateDiscount(pkg);

        assertEquals(expected, discount);
    }

    @Test
    public void shouldReturnZeroDiscountForInvalidOfferCode() {
        Package pkg = new Package("PKG1", 75, 50, "INVALID");

        int expected = 0;
        int discount = packageService.calculateDiscount(pkg);

        assertEquals(expected, discount);
    }

    @Test
    public void shouldCalculateTotalCostWithDiscount() {
        Package pkg = new Package("PKG1", 75, 50, "OFR001");

        int expected = 990;
        int finalAmount = packageService.finalCost(pkg);

        assertEquals(expected, finalAmount);
    }

    @Test
    public void shouldReturnDetailsAboutDiscountAndPrice() {
        Package pkg = new Package("PKG1", 75, 50, "OFR001");

        PackageCostReport expected = new PackageCostReport("PKG1", 110, 990);
        PackageCostReport finalAmount = packageService.costReport(pkg);

        assertEquals(expected, finalAmount);
    }
}