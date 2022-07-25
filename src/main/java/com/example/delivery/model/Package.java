package com.example.delivery.model;

import java.util.Objects;

public class Package {
    final String packageId;
    final int weight;
    final int distance;
    final String offerCode;

    public Package(String packageId, int weight, int distance, String offerCode) {
        this.packageId = packageId;
        this.distance = distance;
        this.weight = weight;
        this.offerCode = offerCode;
    }

    public String getPackageId() {
        return packageId;
    }

    public int getWeight() {
        return weight;
    }

    public int getDistance() {
        return distance;
    }

    public String getOfferCode() {
        return offerCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Package aPackage = (Package) o;
        return weight == aPackage.weight && distance == aPackage.distance && packageId.equals(aPackage.packageId)
                && Objects.equals(offerCode, aPackage.offerCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(packageId, weight, distance, offerCode);
    }
}
