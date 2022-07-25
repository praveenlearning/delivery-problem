package com.example.delivery.service;

import com.example.delivery.model.Package;
import com.example.delivery.model.Vehicle;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class VehicleServiceTest {
    private final VehicleService vehicleService = new VehicleService();

    @Test
    public void calculateTimeToDeliverAPackage() {
        Package pkg = new Package("P1", 50, 30, null);
        Vehicle vehicle = new Vehicle(15, 60);

        var timeToDeliver = vehicleService.calculateTime(vehicle, pkg);

        assertEquals(2, timeToDeliver);
    }

    @Test
    public void shouldReturnDeliveryReportForPackages() {
        List<Package> packages = new ArrayList<>() {{
            add(new Package("P1", 50, 30, null));
            add(new Package("P2", 120, 80, null));
            add(new Package("P3", 90, 150, null));
        }};
        Vehicle vehicle = new Vehicle(60, 300);

        var expected = new HashMap<>() {{
            put(packages.get(0), 0.5);
            put(packages.get(1), 1.33);
            put(packages.get(2), 2.5);
        }};
        Map<Package, Double> deliveryReport = vehicleService.deliver(vehicle, packages);

        assertEquals(expected, deliveryReport);
    }

    @Test
    public void shouldReturnDeliveryReportForPackagesGivenItHadPreviousDelivery() {
        List<Package> packages = new ArrayList<>() {{
            add(new Package("P1", 50, 30, null));
            add(new Package("P2", 120, 80, null));
            add(new Package("P3", 90, 150, null));
        }};

        List<Package> otherPackages = new ArrayList<>() {{
            add(new Package("P1", 45, 75, null));
            add(new Package("P2", 100, 20, null));
            add(new Package("P3", 60, 180, null));
        }};

        Vehicle vehicle = new Vehicle(60, 300);

        var expected = new HashMap<>() {{
            put(otherPackages.get(0), 6.25);
            put(otherPackages.get(1), 5.33);
            put(otherPackages.get(2), 8.0);
        }};
        vehicleService.deliver(vehicle, packages);
        Map<Package, Double> deliveryReport = vehicleService.deliver(vehicle, otherPackages);

        assertEquals(expected, deliveryReport);
    }
}