package com.example.delivery.service;

import com.example.delivery.model.Package;
import com.example.delivery.model.Vehicle;
import com.example.delivery.utils.ListUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class VehicleService {
    public Map<Package, Double> deliver(List<Package> packages, Vehicle vehicle) {
        Map<Package, Double> deliveryTimeReport = packages.stream()
                .collect(Collectors.toMap(pkg -> pkg, pkg -> calculateTime(vehicle, pkg)));

        double maxDeliveryTime = deliveryTimeReport.values().stream()
                .max(Comparator.comparingDouble(value -> value))
                .get();
        double actualDeliveryTime = maxDeliveryTime - vehicle.getAvailableIn();

        double nextAvailability = vehicle.getAvailableIn() + 2 * actualDeliveryTime;
        vehicle.setAvailableIn(nextAvailability);

        return deliveryTimeReport;
    }

    public double calculateTime(Vehicle vehicle, Package pkg) {
        double value = vehicle.getAvailableIn() + (double) pkg.getDistance() / vehicle.getMaxSpeed();
        return BigDecimal.valueOf(value)
                .setScale(2, RoundingMode.FLOOR)
                .doubleValue();
    }

    public List<List<Package>> filterValidPackagesForVehicle(Vehicle vehicle, List<List<Package>> packages) {
        return packages.stream()
                .filter(packagesList -> ListUtils.totalWeightOfPackageList(packagesList) < vehicle.getMaxWeight())
                .collect(Collectors.toList());
    }
}
