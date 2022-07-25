package com.example.delivery;

import com.example.delivery.model.Package;
import com.example.delivery.model.Vehicle;
import com.example.delivery.service.DeliveryService;
import com.example.delivery.service.PackageService;
import com.example.delivery.service.VehicleService;
import com.example.delivery.utils.InputParser;

import java.util.*;

public class DeliveryTimeEstimator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input1 = scanner.nextLine().split(" ");
        int baseCost = Integer.parseInt(input1[0]);
        int packagesCount = Integer.parseInt(input1[1]);

        List<Package> packages = new ArrayList<>();
        for (int i = 0; i < packagesCount; i++) {
            System.out.println("Package-" + (i + 1) + " info");
            String input = scanner.nextLine();
            Package pkg = InputParser.parsePackage(input);
            packages.add(pkg);
        }

        System.out.println("vehicles info");
        List<Vehicle> vehicles = InputParser.parseVehicles(scanner.nextLine());

        PackageService packageService = new PackageService(baseCost);
        VehicleService vehicleService = new VehicleService();
        DeliveryService dispatcher = new DeliveryService(packageService, vehicleService, vehicles);

        System.out.println("Delivery Report");
        Map<Package, Double> report = dispatcher.dispatch(new ArrayList<>(packages));
        packages.forEach(pkg -> System.out.println(packageService.details(pkg) + " \t\t" + report.getOrDefault(pkg, 0.0)));
    }
}
