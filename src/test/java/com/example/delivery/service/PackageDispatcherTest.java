package com.example.delivery.service;

import com.example.delivery.model.Package;
import com.example.delivery.model.Vehicle;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PackageDispatcherTest {
    private final PackageDispatcher dispatcher = new PackageDispatcher(100);

    @Test
    public void shouldCalculateTotalCostForPackage() {
        Package pkg = Package.parsePackage("PKG1 15 5 OFR001");

        int expected = 275;
        int cost = dispatcher.calculateCost(pkg);

        assertEquals(expected, cost);
    }

    @Test
    public void shouldCalculateDiscountForPackage() {
        Package pkg = Package.parsePackage("PKG1 75 50 OFR001");

        int expected = 110;
        int discount = dispatcher.calculateDiscount(pkg);

        assertEquals(expected, discount);
    }

    @Test
    public void shouldReturnZeroDiscountForInvalidOfferCode() {
        Package pkg = Package.parsePackage("PKG1 75 50 INVALID");

        int expected = 0;
        int discount = dispatcher.calculateDiscount(pkg);

        assertEquals(expected, discount);
    }

    @Test
    public void shouldCalculateTotalCostWithDiscount() {
        Package pkg = Package.parsePackage("PKG1 75 50 OFR001");

        int expected = 990;
        int finalAmount = dispatcher.finalCost(pkg);

        assertEquals(expected, finalAmount);
    }

    @Test
    public void shouldReturnDetailsAboutDiscountAndPrice() {
        Package pkg = Package.parsePackage("PKG1 75 50 OFR001");

        String expected = "PKG1\t\t110\t\t990";
        String finalAmount = dispatcher.detail(pkg);

        assertEquals(expected, finalAmount);
    }

    @Test
    public void shouldFindAvailablePackagesForVehicle() {
        List<Package> packages = new ArrayList<>() {{
            add(Package.parsePackage("PKG1 50 30 OFR001"));
            add(Package.parsePackage("PKG2 75 125 OFR008"));
            add(Package.parsePackage("PKG3 175 100 OFR003"));
            add(Package.parsePackage("PKG4 110 60 OFR002"));
            add(Package.parsePackage("PKG5 155 95 NA"));
        }};

        List<Package> expected = new ArrayList<>() {{
            add(packages.get(1));
            add(packages.get(3));
        }};
        List<Vehicle> vehicles = Vehicle.parseVehicles("2 70 200");
        List<Package> packagesAvailable = new PackageDispatcher(100, vehicles)
                .findPackagesForVehicle(packages, vehicles.get(0));

        assertEquals(expected, packagesAvailable);

    }

    @Test
    public void shouldReturnDeliveryReport() {
        List<Package> packages = new ArrayList<>() {{
            add(Package.parsePackage("PKG1 50 30 OFR001"));
            add(Package.parsePackage("PKG2 75 125 OFR008"));
            add(Package.parsePackage("PKG3 175 100 OFR003"));
            add(Package.parsePackage("PKG4 110 60 OFR002"));
            add(Package.parsePackage("PKG5 155 95 NA"));
        }};

        Map<Package, Double> expected = new HashMap<>() {{
            put(packages.get(0), 3.98);
            put(packages.get(1), 1.78);
            put(packages.get(2), 1.42);
            put(packages.get(3), 0.85);
            put(packages.get(4), 4.1899999999999995);
        }};
        List<Vehicle> vehicles = Vehicle.parseVehicles("2 70 200");
        PackageDispatcher dispatcher = new PackageDispatcher(100, vehicles);

        Map<Package, Double> report = dispatcher.dispatch(packages);

        assertEquals(expected, report);
    }

    @Test
    public void shouldShowMaxTimeForPackagesIfNoVehicleIsValid() {
        List<Package> packages = new ArrayList<>() {{
            add(Package.parsePackage("PKG1 110 60 OFR001"));
            add(Package.parsePackage("PKG2 125 120 OFR008"));
            add(Package.parsePackage("PKG3 175 180 OFR003"));
            add(Package.parsePackage("PKG4 110 120 OFR002"));
            add(Package.parsePackage("PKG5 155 140 NA"));
        }};

        List<Vehicle> vehicles = Vehicle.parseVehicles("2 70 100");
        PackageDispatcher dispatcher = new PackageDispatcher(100, vehicles);

        Map<Package, Double> report = dispatcher.dispatch(packages);

        assertEquals(0, report.size());
    }
}