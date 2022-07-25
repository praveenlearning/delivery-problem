package com.example.delivery.service;

import com.example.delivery.model.Package;
import com.example.delivery.model.PackageDeliveryReport;
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

    public List<Package> findDeliverablePackagesForVehicle(List<Package> packageList, Vehicle vehicle) {
        List<List<Package>> possibleSubsets = ListUtils.findSubsets(packageList);

        for (int packagesCount = packageList.size(); packagesCount > 0; packagesCount--) {
            List<List<Package>> filteredPackages = ListUtils.filterBySize(possibleSubsets, packagesCount);
            List<List<Package>> validPackagesList = vehicleService.filterDeliverablePackages(vehicle, filteredPackages);

            int packagesListsCount = validPackagesList.size();
            if (packagesListsCount >= 1) {
                if (packagesListsCount == 1) {
                    return validPackagesList.get(0);
                } else {
                    List<List<Package>> sortedValidPackagesListByWeight = sortPackagesListsByWeightThenDistance(validPackagesList);
                    return sortedValidPackagesListByWeight.get(0);
                }
            }
        }
        return new ArrayList<>();
    }

    public List<PackageDeliveryReport> dispatch(List<Package> packages) {
        List<Package> packagesCopy = new ArrayList<>(packages);
        Map<Package, Double> deliveryReports = new HashMap<>();
        while (packages.size() != 0 && vehicles.size() > 0) {
            Optional<Vehicle> vehicleOption = filterAvailableVehiclesInOrderOfAvailabilityTime(vehicles);
            if (vehicleOption.isEmpty()) break;
            Vehicle vehicle = vehicleOption.get();

            List<Package> packagesForVehicle = findDeliverablePackagesForVehicle(packages, vehicle);
            if (!packagesForVehicle.isEmpty()) {
                Map<Package, Double> packageDeliveryReport = vehicleService.deliver(packagesForVehicle, vehicle);
                deliveryReports.putAll(packageDeliveryReport);
                packages.removeAll(packagesForVehicle);
            } else {
                vehicle.setUnavailable();
            }
        }
        return packageService.createDeliveryReport(packagesCopy, deliveryReports);
    }

    private List<List<Package>> sortPackagesListsByWeightThenDistance(List<List<Package>> packagesLists) {
        return packagesLists.stream()
                .sorted((packagesList1, packagesList2) -> {
                    int sortCondition = ListUtils.totalWeightOfPackageList(packagesList2) - ListUtils.totalWeightOfPackageList(packagesList1);
                    if (sortCondition == 0) {
                        sortCondition = ListUtils.totalDistanceOfPackageList(packagesList1) - ListUtils.totalDistanceOfPackageList(packagesList2);
                    }
                    return sortCondition;
                }).collect(Collectors.toList());
    }

    private Optional<Vehicle> filterAvailableVehiclesInOrderOfAvailabilityTime(List<Vehicle> vehicles) {
        return vehicles.stream()
                .filter(Vehicle::isAvailable)
                .min(Comparator.comparingDouble(Vehicle::getAvailableIn));
    }
}
