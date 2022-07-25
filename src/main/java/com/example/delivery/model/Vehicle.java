package com.example.delivery.model;

import java.util.*;

public class Vehicle {
    private final int maxSpeed;
    private final int maxWeight;
    private boolean isAvailable = true;
    private double availableIn = 0;

    public Vehicle(int maxSpeed, int maxWeight) {
        this.maxSpeed = maxSpeed;
        this.maxWeight = maxWeight;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public double getAvailableIn() {
        return availableIn;
    }

    public void setUnavailable() {
        isAvailable = false;
    }

    public void setAvailableIn(double availableIn) {
        this.availableIn = availableIn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return maxSpeed == vehicle.maxSpeed && maxWeight == vehicle.maxWeight && isAvailable == vehicle.isAvailable
                && Double.compare(vehicle.availableIn, availableIn) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(maxSpeed, maxWeight, isAvailable, availableIn);
    }
}
