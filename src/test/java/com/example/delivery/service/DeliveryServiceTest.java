package com.example.delivery.service;

import com.example.delivery.model.Package;
import com.example.delivery.model.PackageCostReport;
import com.example.delivery.model.PackageDeliveryReport;
import com.example.delivery.model.Vehicle;
import com.example.delivery.utils.InputParser;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryServiceTest {
    private final PackageService packageService = new PackageService(100);
    private final VehicleService vehicleService = new VehicleService();

    @Test
    public void shouldFindAvailablePackagesForVehicle() {
        List<Package> packages = new ArrayList<>() {{
            add(new Package("PKG1", 50, 30, "OFR001"));
            add(new Package("PKG2", 75, 125, "OFR008"));
            add(new Package("PKG3", 175, 100, "OFR003"));
            add(new Package("PKG4", 110, 60, "OFR002"));
            add(new Package("PKG5", 155, 95, "NA"));
        }};

        List<Package> expected = new ArrayList<>() {{
            add(packages.get(1));
            add(packages.get(3));
        }};
        List<Vehicle> vehicles = InputParser.parseVehicles("2 70 200");
        List<Package> packagesAvailable = new DeliveryService(packageService, vehicleService, vehicles).findDeliverablePackagesForVehicle(packages, vehicles.get(0));

        assertEquals(expected, packagesAvailable);

    }

    @Test
    public void shouldReturnDeliveryReport() {
        List<Package> packages = new ArrayList<>() {{
            add(new Package("PKG1", 50, 30, "OFR001"));
            add(new Package("PKG2", 75, 125, "OFR008"));
            add(new Package("PKG3", 175, 100, "OFR003"));
            add(new Package("PKG4", 110, 60, "OFR002"));
            add(new Package("PKG5", 155, 95, "NA"));
        }};

        Set<PackageDeliveryReport> expected = new HashSet<>(new ArrayList<>() {{
            add(new PackageDeliveryReport(new PackageCostReport("PKG2", 0, 1475), 1.78));
            add(new PackageDeliveryReport(new PackageCostReport("PKG4", 105, 1395), 0.85));
            add(new PackageDeliveryReport(new PackageCostReport("PKG3", 0, 2350), 1.42));
            add(new PackageDeliveryReport(new PackageCostReport("PKG5", 0, 2125), 4.19));
            add(new PackageDeliveryReport(new PackageCostReport("PKG1", 0, 750), 3.98));
        }});
        List<Vehicle> vehicles = InputParser.parseVehicles("2 70 200");
        DeliveryService deliveryService = new DeliveryService(packageService, vehicleService, vehicles);

        Set<PackageDeliveryReport> report = new HashSet<>(deliveryService.dispatch(packages));

        assertEquals(expected, report);
    }

    @Test
    public void shouldShowMaxTimeForPackagesIfNoVehicleIsValid() {
        List<Package> packages = new ArrayList<>() {{
            add(new Package("PKG1", 110, 60, "OFR001"));
            add(new Package("PKG2", 125, 120, "OFR008"));
            add(new Package("PKG3", 175, 180, "OFR003"));
            add(new Package("PKG4", 110, 120, "OFR002"));
            add(new Package("PKG5", 155, 140, "NA"));
        }};

        List<Vehicle> vehicles = InputParser.parseVehicles("2 70 100");
        DeliveryService deliveryService = new DeliveryService(packageService, vehicleService, vehicles);

        List<PackageDeliveryReport> report = deliveryService.dispatch(packages);

        List<PackageDeliveryReport> expected = packageService.createDeliveryReport(packages, Map.of());

        assertEquals(expected, report);
    }
}