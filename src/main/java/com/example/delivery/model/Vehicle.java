package com.example.delivery.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class Vehicle {
    final int maxSpeed;
    final int maxWeight;
    private boolean isAvailable = true;
    private double availableIn = 0;

    public Vehicle(int maxSpeed, int maxWeight) {
        this.maxSpeed = maxSpeed;
        this.maxWeight = maxWeight;
    }

    public Map<Package, Double> deliver(List<Package> packages) {
        Map<Package, Double> deliveryReport = packages.stream().collect(Collectors.toMap(pkg -> pkg, this::calculateTime));
        Double max = deliveryReport.values().stream().max(Comparator.comparingDouble(value -> value)).get();

        deliveryReport.replaceAll((aPackage, aDouble) -> availableIn + aDouble);
        availableIn += 2 * max;

        return deliveryReport;
    }

    public double calculateTime(Package pkg) {
        double value = (double) pkg.distance / maxSpeed;
        return BigDecimal.valueOf(value).setScale(2, RoundingMode.FLOOR).doubleValue();
    }

    public void setUnavailable() {
        isAvailable = false;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public double getAvailableIn() {
        return availableIn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return maxSpeed == vehicle.maxSpeed && maxWeight == vehicle.maxWeight && isAvailable == vehicle.isAvailable && Double.compare(vehicle.availableIn, availableIn) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(maxSpeed, maxWeight, isAvailable, availableIn);
    }
}
