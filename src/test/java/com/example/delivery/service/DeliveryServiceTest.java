package com.example.delivery.service;

import com.example.delivery.model.Package;
import com.example.delivery.model.Vehicle;
import com.example.delivery.utils.InputParser;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        Map<Package, Double> expected = new HashMap<>() {{
            put(packages.get(0), 3.98);
            put(packages.get(1), 1.78);
            put(packages.get(2), 1.42);
            put(packages.get(3), 0.85);
            put(packages.get(4), 4.19);
        }};
        List<Vehicle> vehicles = InputParser.parseVehicles("2 70 200");
        DeliveryService dispatcher = new DeliveryService(packageService, vehicleService, vehicles);

        Map<Package, Double> report = dispatcher.dispatch(packages);

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
        DeliveryService dispatcher = new DeliveryService(packageService, vehicleService, vehicles);

        Map<Package, Double> report = dispatcher.dispatch(packages);

        assertEquals(0, report.size());
    }
}