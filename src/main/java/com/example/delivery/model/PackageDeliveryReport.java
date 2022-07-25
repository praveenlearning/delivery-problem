package com.example.delivery.model;

import java.util.Objects;

public class PackageDeliveryReport {
    private final PackageCostReport costReport;
    private final double deliveryTime;

    public PackageDeliveryReport(PackageCostReport costReport, double deliveryTime) {
        this.costReport = costReport;
        this.deliveryTime = deliveryTime;
    }

    @Override
    public String toString() {
        return costReport + "\t\t" + deliveryTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PackageDeliveryReport that = (PackageDeliveryReport) o;
        return Double.compare(that.deliveryTime, deliveryTime) == 0 && costReport.equals(that.costReport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(costReport, deliveryTime);
    }
}
