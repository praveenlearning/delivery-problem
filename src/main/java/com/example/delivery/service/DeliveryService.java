package com.example.delivery.service;

import com.example.delivery.model.Package;
import com.example.delivery.model.Vehicle;
import com.example.delivery.utils.ListUtils;

import java.util.*;
import java.util.stream.Collectors;

public class DeliveryService {
    final List<Vehicle> vehicles;
    final VehicleService vehicleService;
    final PackageService packageService;

    public DeliveryService(PackageService packageService, VehicleService vehicleService, List<Vehicle> vehicles) {
        this.packageService = packageService;
        this.vehicleService = vehicleService;
        this.vehicles = vehicles;
    }

    public List<Package> findPackagesForVehicle(List<Package> packageList, Vehicle vehicle) {
        List<List<Package>> possibleSubsets = ListUtils.findSubsets(packageList);

        for (int i = packageList.size(); i > 0; i--) {
            int packagesCount = i;
            List<List<Package>> validPackagesList = possibleSubsets.stream()
                    .filter(subset -> subset.size() == packagesCount)
                    .filter(list -> ListUtils.totalWeightOfPackageList(list) < vehicle.getMaxWeight())
                    .collect(Collectors.toList());

            int validPackagesListCount = validPackagesList.size();
            if (validPackagesListCount >= 1) {
                if (validPackagesListCount == 1) {
                    return validPackagesList.get(0);
                } else {
                    List<List<Package>> sortedValidPackagesListByWeight = validPackagesList.stream()
                            .sorted((l1, l2) -> {
                                int sortCondition = ListUtils.totalWeightOfPackageList(l2) - ListUtils.totalWeightOfPackageList(l1);
                                if (sortCondition == 0) {
                                    sortCondition = ListUtils.totalDistanceOfPackageList(l1) - ListUtils.totalDistanceOfPackageList(l2);
                                }
                                return sortCondition;
                            }).collect(Collectors.toList());
                    return sortedValidPackagesListByWeight.get(0);
                }
            }
        }
        return new ArrayList<>();
    }

    public Map<Package, Double> dispatch(List<Package> packages) {
        Map<Package, Double> deliveredPackagesReport = new HashMap<>();

        while (packages.size() != 0 && vehicles.size() > 0) {
            Optional<Vehicle> vehicleOption = vehicles.stream()
                    .filter(Vehicle::isAvailable)
                    .min(Comparator.comparingDouble(Vehicle::getAvailableIn));

            if (vehicleOption.isEmpty()) break;
            Vehicle vehicle = vehicleOption.get();
            List<Package> packagesForVehicle = findPackagesForVehicle(packages, vehicle);

            if (!packagesForVehicle.isEmpty()) {
                Map<Package, Double> packageDeliveryReport;
                packageDeliveryReport = vehicleService.deliver(packagesForVehicle, vehicle);
                deliveredPackagesReport.putAll(packageDeliveryReport);
            }

            if (packagesForVehicle.size() > 0) packages.removeAll(packagesForVehicle);
            else vehicle.setUnavailable();
        }
        return deliveredPackagesReport;
    }
}
