package com.example.delivery;

import com.example.delivery.model.Package;
import com.example.delivery.model.Vehicle;
import com.example.delivery.service.PackageDispatcher;

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
            Package pkg = Package.parsePackage(input);
            packages.add(pkg);
        }

        System.out.println("vehicles info");
        List<Vehicle> vehicles = Vehicle.parseVehicles(scanner.nextLine());
        PackageDispatcher dispatcher = new PackageDispatcher(baseCost, vehicles);

        System.out.println("Delivery Report");
        Map<Package, Double> report = dispatcher.dispatch(new ArrayList<>(packages));
        packages.forEach(pkg -> System.out.println(dispatcher.detail(pkg) + " \t\t" + report.getOrDefault(pkg, 0.0)));
    }
}
