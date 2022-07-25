package com.example.delivery.model;

import java.util.Objects;

public class PackageCostReport {
    private final String packageId;
    private final int discount;
    private final int cost;

    public PackageCostReport(String packageId, int discount, int cost) {
        this.packageId = packageId;
        this.discount = discount;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return packageId + "\t\t" + discount + "\t\t" + cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PackageCostReport that = (PackageCostReport) o;
        return discount == that.discount && cost == that.cost && packageId.equals(that.packageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(packageId, discount, cost);
    }
}
