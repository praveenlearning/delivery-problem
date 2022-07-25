package com.example.delivery.service;

import com.example.delivery.model.Package;
import com.example.delivery.model.Vehicle;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class VehicleService {
    public Map<Package, Double> deliver(Vehicle vehicle, List<Package> packages) {
        Map<Package, Double> deliveryTimeReport = packages.stream()
                .collect(Collectors.toMap(pkg -> pkg, pkg -> calculateTime(vehicle, pkg)));

        Double maxDeliveryTime = deliveryTimeReport.values().stream()
                .max(Comparator.comparingDouble(value -> value))
                .get();

        deliveryTimeReport.replaceAll((aPackage, aDouble) -> vehicle.getAvailableIn() + aDouble);
        double nextAvailability = vehicle.getAvailableIn() + 2 * maxDeliveryTime;
        vehicle.setAvailableIn(nextAvailability);

        return deliveryTimeReport;
    }

    public double calculateTime(Vehicle vehicle, Package pkg) {
        double value = (double) pkg.getDistance() / vehicle.getMaxSpeed();
        return BigDecimal.valueOf(value)
                .setScale(2, RoundingMode.FLOOR)
                .doubleValue();
    }
}
